package org.example;

import java.sql.*;
import java.util.Scanner;

@SuppressWarnings("squid:S5786")
public class Menu {
    private final Scanner scanner;

    Functions func;

    public Menu(Connection connection) throws SQLException {
        this.scanner = new Scanner(System.in);
        this.func = new Functions(scanner, connection);
    }

    public void displayMenu() {
        int choice = -1;

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть таблицы");
            System.out.println("2. Добавить запись");
            System.out.println("3. Изменить запись");
            System.out.println("4. Удалить запись");
            System.out.println("0. Выйти");

            try {
                choice = scanner.nextInt();
                if (choice > 5 || choice < 1) {
                    break;
                }
                func.displayDBObjects(choice);
            } catch (NumberFormatException e) {
                System.out.println("ошибка");
                displayMenu();
            }
        }
    }
}


