package org.lab;

import org.lab.dao.JdbcStudentDao;
import org.lab.dao.StudentDao;
import org.lab.db.ConnectionProvider;
import org.lab.db.DatabaseInitializer;
import org.lab.db.DbConfig;
import org.lab.service.DatabaseSearchService;
import org.lab.service.JavaSearchService;
import org.lab.service.StudentSearchService;
import org.lab.ui.ConsoleMenu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConnectionProvider connectionProvider =
                new ConnectionProvider(DbConfig.fromResource("db.properties"));
        new DatabaseInitializer(connectionProvider).initializeIfNeeded();

        StudentDao studentDao = new JdbcStudentDao(connectionProvider);
        List<StudentSearchService> services = List.of(
                new DatabaseSearchService(studentDao),
                new JavaSearchService(studentDao));

        new ConsoleMenu(new Scanner(System.in), services).run();
    }
}
