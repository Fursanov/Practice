package org.example;

import org.example.controllers.MainController;

import java.sql.*;
import static org.example.Config.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                url,
                username,
                password
        );

        MainController controller = new MainController(connection);
        controller.displayMenu();

        connection.close();

    }
}