package org.example;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@SuppressWarnings("squid:S5786")
public class Functions {
    private final Scanner scanner;
    private final Connection connection;
    private final CRUD crud;
    private static final Logger logger = Logger.getLogger(CRUD.class.getName());

    public Functions(Scanner scanner, Connection connection) throws SQLException {
        this.scanner = scanner;
        this.connection = connection;
        this.crud = new CRUD(connection, scanner);
    }

    public static List<String> searchTableName(Connection connection, int tableNumber, ResultSet tables) throws SQLException {
        int currentTableNumber = 1;
        String tableName = "tableName";
        String idName = "idName";
        while (tables.next()) {
            if (currentTableNumber == tableNumber) {
                tableName = tables.getString(3);
                String sql = "SELECT * FROM " + tableName;
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                idName = resultSet.getMetaData().getColumnName(1);
                break;
            }
            currentTableNumber++;
        }
        tables.close();
        return List.of(tableName, idName);
    }

    public static void printTable(Connection connection, String tableName) throws SQLException {

        String sql = "SELECT * FROM " + tableName;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnName(i + 1);
                System.out.println(columnName + ": " + resultSet.getString(columnName));
            }
            System.out.println();
        }

        resultSet.close();
        statement.close();
    }

    void displayDBObjects(int mode) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet tables = null;
            ResultSet functions = null;
            if(mode == 1) {
                tables = metaData.getTables(null, "public", "%", new String[]{"TABLE", "VIEW"});
            } else if (mode == 2) {
                functions = metaData.getFunctions(null, "public", "add%");
            }else {
                tables = metaData.getTables(null, "public", "%", new String[]{"TABLE"});
            }
            System.out.println("Выберите объект для выполнения действия:");
            int tableNumber = 1;
            if(tables != null) {
                while (tables.next()) {
                    String tableName = tables.getString(3);
                    System.out.println(tableNumber + ". " + tableName);
                    tableNumber++;
                }
                tables.close();
            }
            if(functions != null) {
                while (functions.next()) {
                    String functionName = functions.getString("FUNCTION_NAME");
                    System.out.println(tableNumber + ". " + functionName);
                    tableNumber++;
                }
                functions.close();
            }

            int selectedTable = scanner.nextInt();
            if(mode == 1) {
                crud.displayTable(selectedTable);
            } else if(mode == 2){
                crud.addRecord(selectedTable);
            } else if(mode == 3){
                crud.updateRecord(selectedTable);
            } else if(mode == 4){
                crud.deleteRecord(selectedTable);
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
        }
    }
}


