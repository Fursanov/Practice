package org.example;

import java.util.Scanner;

public class HelpFunc {
    public static int safeIntInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите положительное целое число: ");
                int number = Integer.parseInt(scanner.nextLine());
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Ошибка: введите положительное целое число.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
            scanner.nextLine();
        }
    }

    public static double safeDoubleInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите положительное число: ");
                double number = Double.parseDouble(scanner.nextLine());
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Ошибка: введите положительное число.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите вещественное число.");
            }
            scanner.nextLine();
        }
    }
}
