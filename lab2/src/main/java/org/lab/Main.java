package org.lab;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int MAX_SIZE = 20;
    private static final int RANDOM_MIN = 1;
    private static final int RANDOM_MAX = 100;

    private static final int MODE_MANUAL = 1;
    private static final int MODE_RANDOM = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int height = readIntInRange(scanner,
                "Введіть висоту матриці (1-" + MAX_SIZE + "): ", 1, MAX_SIZE);
        int width = readIntInRange(scanner,
                "Введіть ширину матриці (1-" + MAX_SIZE + "): ", 1, MAX_SIZE);
        int mode = readIntInRange(scanner,
                "Спосіб створення (" + MODE_MANUAL + " - вручну, "
                        + MODE_RANDOM + " - випадково): ", MODE_MANUAL, MODE_RANDOM);

        int[][] matrix;
        if (mode == MODE_MANUAL) {
            matrix = createManualMatrix(scanner, height, width);
        } else {
            matrix = createRandomMatrix(height, width);
        }

        printMatrix(matrix);

        System.out.println("Мінімальний елемент: " + findMin(matrix));
        System.out.println("Максимальний елемент: " + findMax(matrix));
        System.out.printf("Середнє арифметичне: %.4f%n", findAverage(matrix));

        double geometricMean = findGeometricMean(matrix);
        if (Double.isNaN(geometricMean)) {
            System.out.println("Середнє геометричне: не можна обчислити (є елементи <= 0)");
        } else {
            System.out.printf("Середнє геометричне: %.4f%n", geometricMean);
        }
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Помилка: потрібно ввести ціле число.");
            scanner.next();
        }
    }

    private static int readIntInRange(Scanner scanner, String message, int min, int max) {
        while (true) {
            int value = readInt(scanner, message);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Помилка: число має бути від " + min + " до " + max + ".");
        }
    }

    private static int[][] createManualMatrix(Scanner scanner, int height, int width) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = readInt(scanner, "Елемент [" + i + "][" + j + "]: ");
            }
        }
        return matrix;
    }

    private static int[][] createRandomMatrix(int height, int width) {
        Random random = new Random();
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = RANDOM_MIN + random.nextInt(RANDOM_MAX - RANDOM_MIN + 1);
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);
            }
            System.out.println();
        }
    }

    private static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    private static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    private static double findAverage(int[][] matrix) {
        long sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
                count++;
            }
        }
        return (double) sum / count;
    }

    private static double findGeometricMean(int[][] matrix) {
        double logSum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value <= 0) {
                    return Double.NaN;
                }
                logSum += Math.log(value);
                count++;
            }
        }
        return Math.exp(logSum / count);
    }
}