import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "Dev@4733";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database.");
            insertStudent(connection, "Tony", "tony@gmail.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection connection, String name, String email) {
        String sql = "INSERT INTO student (name, email) VALUES ('" + name + "','" + email + "')";
        try (Statement statement = connection.createStatement()){
            int rows = statement.executeUpdate(sql);
            System.out.println("INSERTED: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
