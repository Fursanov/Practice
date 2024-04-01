package org.example;

import java.sql.*;
import java.util.Scanner;

@SuppressWarnings("squid:S5786")
public class Main {

    static String url = "jdbc:postgresql://localhost:5432/market";
    static String sqlUser = "postgres";
    static String password = System.getenv("database_password");

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(url, sqlUser, password);

        Menu menu = new Menu(connection, new Scanner(System.in));
        menu.displayMenu();

        connection.close();

    }
}