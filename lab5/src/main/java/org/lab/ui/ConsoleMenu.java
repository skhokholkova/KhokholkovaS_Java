package org.lab.ui;

import org.lab.db.DataAccessException;
import org.lab.model.Student;
import org.lab.service.StudentSearchService;
import org.lab.util.Months;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** Консольний інтерфейс користувача. */
public class ConsoleMenu {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final Scanner scanner;
    private final List<StudentSearchService> services;

    public ConsoleMenu(Scanner scanner, List<StudentSearchService> services) {
        this.scanner = scanner;
        this.services = services;
    }

    public void run() {
        StudentSearchService service = chooseService();
        System.out.println("Обрано: " + service.getDescription());

        while (true) {
            int month = readInt("\nВведіть номер місяця народження (1-12) або 0 для виходу: ");
            if (month == 0) {
                System.out.println("Роботу завершено.");
                return;
            }
            if (!Months.isValid(month)) {
                System.out.println("Помилка: номер місяця має бути від 1 до 12.");
                continue;
            }
            showStudents(month, service);
        }
    }

    private StudentSearchService chooseService() {
        System.out.println("Оберіть спосіб пошуку:");
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + " - " + services.get(i).getDescription());
        }
        while (true) {
            int choice = readInt("Ваш вибір: ");
            if (choice >= 1 && choice <= services.size()) {
                return services.get(choice - 1);
            }
            System.out.println("Помилка: введіть число від 1 до " + services.size() + ".");
        }
    }

    private void showStudents(int month, StudentSearchService service) {
        try {
            List<Student> students = service.findByBirthMonth(month);
            if (students.isEmpty()) {
                System.out.println("Студентів, які народилися у " + Months.inMonth(month) + ", не знайдено.");
                return;
            }
            System.out.println("Студенти, які народилися у " + Months.inMonth(month)
                    + " (" + students.size() + "):");
            System.out.printf("%-4s %-12s %-11s %-15s %-16s %s%n",
                    "ID", "Прізвище", "Ім'я", "По батькові", "Дата народження", "№ залікової");
            for (Student s : students) {
                System.out.printf("%-4d %-12s %-11s %-15s %-16s %s%n",
                        s.getId(), s.getLastName(), s.getFirstName(), s.getMiddleName(),
                        s.getBirthDate().format(DATE_FORMAT), s.getRecordBookNumber());
            }
        } catch (DataAccessException e) {
            System.out.println("Помилка роботи з базою даних: " + e.getMessage());
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Помилка: потрібно ввести ціле число.");
            scanner.next();
        }
    }
}
