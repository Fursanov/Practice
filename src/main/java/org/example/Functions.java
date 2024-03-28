package org.example;

import java.sql.*;
import java.util.Scanner;

public class Functions {
    private final Scanner scanner;
    private final Connection connection;

    private final CRUD crud;

    public Functions(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;
        this.crud = new CRUD(connection, scanner);
    }

    public static Object executeSqlFunction(Connection connection, String functionName, Object... params) throws SQLException {

        // Создание PreparedStatement
        StringBuilder sql = new StringBuilder("SELECT " + functionName + "(");
        for (int i = 0; i < params.length; i++) {
            if (i == 0)
                sql.append("?");
            else
                sql.append(",?");
        }
        sql.append(")");

        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        ResultSet resultSet = statement.executeQuery();

        Object result = null;
        if (resultSet.next()) {
            result = resultSet.getObject(1);
        }

        // Закрытие ResultSet и PreparedStatement
        resultSet.close();
        statement.close();

        return result;
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

        // Закрытие ResultSet, PreparedStatement
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
            e.printStackTrace();
        }
    }
}


