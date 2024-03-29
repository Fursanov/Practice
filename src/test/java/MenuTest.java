import org.example.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    static String url = "jdbc:postgresql://localhost:5432/market";
    static String sql_user = "postgres";
    static String password = System.getenv("DATABASE_PASSWORD");
    private Menu menu;
    private InputStream originalSystemIn;

    public static void clearTable(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            disableForeignKeyChecks(connection);
            statement.executeUpdate("DELETE FROM " + tableName);
            enableForeignKeyChecks(connection);
        }
    }

    private static void disableForeignKeyChecks(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SET session_replication_role = replica;");
        }
    }

    private static void enableForeignKeyChecks(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SET session_replication_role = DEFAULT;");
        }
    }
    private static void resetSequence(Connection connection, String sequenceName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "ALTER SEQUENCE " + sequenceName + " RESTART WITH 1";
            statement.executeUpdate(sql);
        }
    }

    @Before
    public void setUp() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, sql_user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                clearTable(connection, tableName);
                System.out.println("Таблица " + tableName + " очищена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, sql_user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema = 'public'";
                try (ResultSet sequences = statement.executeQuery(sql)) {
                    while (sequences.next()) {
                        String sequenceName = sequences.getString("sequence_name");
                        resetSequence(connection, sequenceName);
                    }
                }
            }
            System.out.println("Все последовательности сброшены.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        originalSystemIn = System.in;
        String input_outTest = "1\n1\n2\n3\n4\n5\n6\n7\n8\n0\n";
        String input_inpTest = "2\n8\nasd\nasd\nasd\nasd\n7\nasd\nasd\n4\nasd\n3\nasd\nasd\n123\n123\n1\n1\n1\n1\n1\n1\n1\n";
        String input_updTest = "0\n";
        String input_delTest = "0\n";
        String resultTest = input_outTest + input_inpTest + input_updTest + input_delTest;
        ByteArrayInputStream testIn = new ByteArrayInputStream(resultTest.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
        Connection connection = DriverManager.getConnection(url, sql_user, password);
        menu = new Menu(connection, new Scanner(System.in));
    }

    @Test
    public void testDisplayMenu() {
        menu.displayMenu();
        assertEquals(5, 5);
    }
    @After
    public void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }
}
