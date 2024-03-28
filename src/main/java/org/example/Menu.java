package org.example;

import java.sql.*;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    Functions func;

    public Menu(Connection connection) {
        this.scanner = new Scanner(System.in);
        this.func = new Functions(scanner, connection);
    }

    public void displayMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть таблицы");
            System.out.println("2. Добавить запись");
            System.out.println("3. Изменить запись");
            System.out.println("4. Удалить запись");
            System.out.println("0. Выйти");

            try {
                if (choice < 5) {
                    choice = scanner.nextInt();
                    func.displayDBObjects(choice);
                }

            } catch (NumberFormatException e) {
                System.out.println("ошибка");
                displayMenu();
            }
        }
    }
}


