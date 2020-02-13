import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class JDBC
{
    public static Connection getConnection() throws SQLException
    {
        Connection conn;
        String url       = "jdbc:mysql://localhost:3306/Newspaper";
        String user      = "con";
        String password  = "";

        // create a connection to the database
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static boolean logIn(String email, String password, String as, JPanel Main_Window) throws SQLException
    {
        Connection conn = getConnection();
        boolean flag = true;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + as + " WHERE Email = '" + email + "'");

        if (!rs.next())
        {
            JOptionPane.showMessageDialog(Main_Window, "Wrong e-mail and/or selected job title.");
            conn.close();
            return false;
        }

        if (!as.equals("Publisher"))
            as = "Employee";

        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM " + as + " WHERE Email = '" + email + "' and Pass = '" + password + "'");

        if (!rs.next())
        {
            JOptionPane.showMessageDialog(Main_Window, "Wrong e-mail and/or password.");
            flag = false;
        }

        conn.close();
        return flag;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() ->
        {
            try {
                Newspaper frame = new Newspaper();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}