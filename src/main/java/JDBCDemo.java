import java.sql.*;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "Dev@4733";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database.");
//            insertStudent(connection, "Tony", "tony@gmail.com");
            selectStudents(connection);
//            updateStudent(connection, 1, "Tony", "tony@gmail.com");
            deleteStudent(connection, 1);
            selectStudents(connection);
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

    private static void selectStudents(Connection connection) {
        String sql = "SELECT * FROM student";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Student List : ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(id + " " + name + " " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateStudent(Connection connection, int id, String name, String email) {
        String sql = "UPDATE student SET name = '" + name + "', email = '" + email + "' WHERE id=" + id;
        try (Statement statement = connection.createStatement()){
            int rows = statement.executeUpdate(sql);
            System.out.println("UPDATED: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection connection, int id) {
        String sql = "DELETE FROM student WHERE id = " + id;
        try (Statement statement = connection.createStatement()){
            int rows = statement.executeUpdate(sql);
            System.out.println("DELETED: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
