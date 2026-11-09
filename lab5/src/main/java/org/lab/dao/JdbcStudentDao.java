package org.lab.dao;

import org.lab.db.ConnectionProvider;
import org.lab.db.DataAccessException;
import org.lab.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Реалізація StudentDao через JDBC. */
public class JdbcStudentDao implements StudentDao {
    private static final String COLUMNS =
            "id, last_name, first_name, middle_name, birth_date, record_book_number";

    private static final String SELECT_ALL =
            "SELECT " + COLUMNS + " FROM students ORDER BY last_name, first_name";

    private static final String SELECT_BY_MONTH =
            "SELECT " + COLUMNS + " FROM students "
                    + "WHERE EXTRACT(MONTH FROM birth_date) = ? "
                    + "ORDER BY EXTRACT(DAY FROM birth_date), last_name";

    private final ConnectionProvider connectionProvider;

    public JdbcStudentDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Student> findAll() {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            return readStudents(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException("Не вдалося отримати список студентів", e);
        }
    }

    @Override
    public List<Student> findByBirthMonth(int month) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_MONTH)) {
            statement.setInt(1, month);
            try (ResultSet resultSet = statement.executeQuery()) {
                return readStudents(resultSet);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Не вдалося отримати студентів за місяцем " + month, e);
        }
    }

    private List<Student> readStudents(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("middle_name"),
                    resultSet.getDate("birth_date").toLocalDate(),
                    resultSet.getString("record_book_number")));
        }
        return students;
    }
}
