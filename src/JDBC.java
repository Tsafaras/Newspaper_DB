import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC
{
    public static Connection getConnection() throws SQLException {
    Connection conn = null;
    String url       = "jdbc:mysql://localhost:3306/test";
    String user      = "con";
    //String password  = "";

    // create a connection to the database
    conn = DriverManager.getConnection(url, user, "");

    return conn;
    }
}