package org.example;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


import static org.example.Functions.printTable;
import static org.example.Functions.searchTableName;

@SuppressWarnings("squid:S5786")
public class CRUD {
    private final Connection connection;
    private final Scanner scanner;
    private final DatabaseMetaData metaData;
    private final ResultSet tables;
    private static final Logger logger = Logger.getLogger(CRUD.class.getName());

    public CRUD(Connection connection, Scanner scanner) throws SQLException {
        this.connection = connection;
        this.scanner = scanner;
        this.metaData = connection.getMetaData();
        this.tables = metaData.getTables(null, "public", "%", new String[]{"TABLE"});
    }

    void displayTable(int tableNumber) {
        try {
            int currentTableNumber = 1;
            while (tables.next()) {
                if (currentTableNumber == tableNumber) {
                    String tableName = tables.getString(3);
                    System.out.println("Таблица: " + tableName);
                    printTable(connection, tableName);
                    break;
                }
                currentTableNumber++;
            }
            tables.close();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
        }
    }

    void addRecord(int tableNumber) {
        try {
            ResultSet functions = metaData.getFunctions(null, "public", "add%");

            int currentTableNumber = 1;
            String functionName = null;
            while (functions.next()) {
                if (currentTableNumber == tableNumber) {
                    functionName = functions.getString("FUNCTION_NAME");
                    break;
                }
                currentTableNumber++;
            }
            functions.close();

            if (functionName != null) {
                ResultSet columns = metaData.getFunctionColumns(null, "public", functionName, null);

                StringBuilder values = new StringBuilder("SELECT " + functionName + " (");
                columns.next();
                boolean isFirstColumn = true;
                while (columns.next()) {

                    if (!isFirstColumn) {
                        values.append(", ");
                    } else {
                        isFirstColumn = false;
                    }

                    values.append("?");
                }

                values.append(")");

                PreparedStatement preparedStatement = connection.prepareStatement(values.toString());

                columns.beforeFirst();
                int index = 1;
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    if(columnName.equals("returnValue")) {
                        continue;
                    }
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("Введите значение для столбца " + columnName + " (тип " + columnType + "):");
                    String columnValue = scanner.next();
                    if (columnType.equalsIgnoreCase("int4")) {
                        preparedStatement.setInt(index, Integer.parseInt(columnValue));
                    } else if (columnType.equalsIgnoreCase("varchar") || (columnType.equalsIgnoreCase("text"))) {
                        preparedStatement.setString(index, columnValue);
                    } else if (columnType.equalsIgnoreCase("numeric")) {
                        preparedStatement.setBigDecimal(index, new BigDecimal(columnValue));
                    } else if (columnType.equalsIgnoreCase("date")) {
                        preparedStatement.setDate(index, java.sql.Date.valueOf(columnValue));
                    } else if (columnType.equalsIgnoreCase("timestamp")) {
                        preparedStatement.setTimestamp(index, java.sql.Timestamp.valueOf(columnValue));
                    } else if (columnType.equalsIgnoreCase("boolean")) {
                        preparedStatement.setBoolean(index, Boolean.parseBoolean(columnValue));
                    }

                    index++;
                }

                preparedStatement.executeQuery();
                preparedStatement.close();
                columns.close();
                System.out.println("Запись успешно добавлена в таблицу");
            } else {
                System.out.println("Таблица не найдена.");
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
        }
    }

    void updateRecord(int tableNumber) {
        try {
            List<String> Names = searchTableName(connection, tableNumber, tables);
            String tableName = Names.get(0);
            String idName = Names.get(1);

            if (tableName != null) {
                System.out.println("Введите идентификатор записи для обновления:");
                int id = scanner.nextInt();

                String selectQuery = "SELECT * FROM " + tableName + " WHERE " + idName + " = ?";
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                selectStatement.setInt(1, id);
                ResultSet resultSet = selectStatement.executeQuery();

                ResultSetMetaData resultMetaSet = resultSet.getMetaData();
                int columnCount = resultMetaSet.getColumnCount();

                if (resultSet.next()) {
                    System.out.println("Текущие значения записи:");
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println(resultMetaSet.getColumnName(i) + ": " + resultSet.getString(i));
                    }

                    System.out.println("Введите новые значения:");
                    StringBuilder updateQuery = new StringBuilder("UPDATE " + tableName + " SET ");
                    for (int i = 2; i <= columnCount; i++) {
                        if (i > 2) {
                            updateQuery.append(", ");
                        }
                        updateQuery.append(resultMetaSet.getColumnName(i)).append(" = ?");
                    }
                    updateQuery.append(" WHERE ").append(idName).append("= ?");
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery.toString());

                    for (int i = 2; i <= columnCount; i++) {
                        System.out.print(resultMetaSet.getColumnName(i) + ": ");
                        String newValue = scanner.next();
                        updateStatement.setString(i - 1, newValue);
                    }
                    updateStatement.setInt(columnCount, id);

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Запись успешно обновлена.");
                    } else {
                        System.out.println("Не удалось обновить запись.");
                    }
                } else {
                    System.out.println("Запись с указанным идентификатором не найдена.");
                }

                selectStatement.close();
                resultSet.close();
            } else {
                System.out.println("Таблица не найдена.");
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
        }
    }

    void deleteRecord(int tableNumber) {
        try {
            List<String> Names = searchTableName(connection, tableNumber, tables);
            String tableName = Names.get(0);
            String idName = Names.get(1);

            if (tableName != null) {
                System.out.println("Введите идентификатор записи для удаления:");
                int id = scanner.nextInt();

                String deleteQuery = "DELETE FROM " + tableName + " WHERE " + idName + " = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, id);

                int rowsAffected = deleteStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Запись успешно удалена.");
                } else {
                    System.out.println("Не удалось удалить запись. Возможно, запись с указанным идентификатором не существует.");
                }

                deleteStatement.close();
            } else {
                System.out.println("Таблица не найдена.");
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
        }
    }

}
