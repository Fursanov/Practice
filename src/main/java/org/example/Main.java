package org.example;

import org.example.controllers.MainController;

import java.sql.*;

public class Main {

    static String url = "jdbc:postgresql://localhost:5432/market";
    static String sql_user = "postgres";
    static String password = "el-psy-kongru";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(url, sql_user, password);

        MainController controller = new MainController(connection);
        controller.displayMenu();

        connection.close();

    }
}