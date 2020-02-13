import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;

public class Newspaper extends JFrame{
    private JPanel Journalist;
    private JButton Login_BTN;
    private JPasswordField Pswd_TXTFLD;
    private JTextField E_mail_TXTFLD;
    private JCheckBox Show_psw_chkbx;
    private JPanel Main_Window;
    private JPanel Login_Screen;
    private JPanel Editor;
    private JPanel Administrative;
    private JPanel Publisher;
    private JLabel E_mail_Label;
    private JLabel Pswd_Label;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane4;
    private JTextField J_path;
    private JTextArea J_summary;
    private JTextField J_writers;
    private JTextField J_tags;
    private JFormattedTextField J_pages;
    private JComboBox J_category;
    private JButton J_Submit_BTN;
    private JTextField J_Rev_path;
    private JTextArea J_Rev_summary;
    private JComboBox J_Rev_category;
    private JTextField J_Rev_writers;
    private JTextField J_Rev_tags;
    private JTextField J_Rev_pages;
    private JButton J_Rev_Submit_BTN;
    private JTextField E_newCategory;
    private JTextField A_sheet_returned;
    private JTextField A_months;
    private JButton A_Payroll_OKBTN;
    private JTextField J_images;
    private JTextField J_Rev_Images;
    private JTextField E_path;
    private JTextArea E_summary;
    private JComboBox E_category;
    private JTextField E_writers;
    private JTextField E_tags;
    private JTextField E_pages;
    private JTextField E_images;
    private JButton E_Submit_Article_BTN;
    private JButton E_Category_Submit_BTN;
    private JButton A_returned_Submit_BTN;
    private JButton E_logoutBTN;
    private JLabel E_welcome;
    private JButton A_logoutBTN;
    private JButton P_logoutBTN;
    private JButton J_logoutBTN;
    private JLabel J_welcome;
    private JLabel A_welcome;
    private JLabel P_welcome;
    private JRadioButton J_RDBTN;
    private JRadioButton E_RDBTN;
    private JRadioButton P_RDBTN;
    private JRadioButton A_RDBTN;
    private JTextField A_volume_returned;
    private JTable A_Payroll_TBL;
    private JTextField J_Title;
    private JTextField J_Rev_Title;
    private JTextField E_Title;
    private JLabel Total_Payroll;
    private JScrollPane A_ScrollPane;
    private JComboBox<String> E_CatParent;
    private JTextField E_CatDescr;
    private JComboBox<String> J_RevBox;
    private JButton J_RevOK;
    private JComboBox<String> J_ViewBox;
    private JButton J_ViewOK;
    private JLabel J_View_Path;
    private JLabel J_View_Title;
    private JLabel J_View_Summary;
    private JLabel J_View_Category;
    private JLabel J_View_Writers;
    private JLabel J_View_Tags;
    private JLabel J_View_Pages;
    private JLabel J_View_Images;
    private JComboBox<String> comboBox1;
    private JTextArea textArea1;
    private JComboBox<String> comboBox2;
    private JRadioButton ACCEPTEDRadioButton;
    private JRadioButton PENDINGRadioButton;
    private JRadioButton REJECTEDRadioButton;
    private JRadioButton TO_BE_REVISEDRadioButton;
    private JButton submitButton;
    private JTextField E_Approve_Order;
    private JTextField E_Appear_Order;
    private JComboBox<String> E_Order_Select;
    private JButton submitButton1;
    private JComboBox<String> P_NewspaperBox;
    private JTextField P_Edit_Name;
    private JRadioButton P_Daily;
    private JRadioButton P_Monthly;
    private JRadioButton P_Weekly;
    private JComboBox<String> P_Amout_Sheet;
    private JTextField P_Amount_Amount;
    private JButton P_Edit_Submit;
    private JButton P_Amount_Submit;
    private JComboBox<String> P_EditorBox;
    private JButton P_EditorSubmit;
    private JTable P_SalesTBL;

    private void switchPanel(JPanel panel)
    {
        if ( panel == Login_Screen )
        {
            E_mail_TXTFLD.setText("");
            Pswd_TXTFLD.setText("");
            if (Show_psw_chkbx.isSelected())
            {
                Show_psw_chkbx.setSelected(false);
                Pswd_TXTFLD.setEchoChar('*');
            }
        }
        Main_Window.removeAll();
        Main_Window.add(panel);
        Main_Window.repaint();
        Main_Window.revalidate();
        pack();
    }

    private void updateQuery(String query) throws SQLException
    {
        // create a java mysql database connection
        Connection conn = JDBC.getConnection();

        // create the java mysql update preparedstatement
        Statement stmt = conn.createStatement();

        stmt.executeUpdate(query);

        conn.close();
    }

    private void insertQuery(String query) throws SQLException
    {
        String email = E_mail_TXTFLD.getText();

        // create a java mysql database connection
        Connection conn = JDBC.getConnection();

        // create the java mysql update preparedstatement
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        conn.close();
    }

    private void login(String as, String email) throws SQLException
    {
        switch (as)
        {
            case "Journalist":
                J_welcome.setText("Logged in as: " + email);
                switchPanel(Journalist);
                break;
            case "Editor":
                E_welcome.setText("Logged in as: " + email);
                //populate();
                switchPanel(Editor);
                break;
            case "Administrative":
                A_welcome.setText("Logged in as: " + email);
                switchPanel(Administrative);
                break;
            case "Publisher":
                P_welcome.setText("Logged in as: " + email);
                switchPanel(Publisher);
                break;

        }
    }

    private void isDigit(KeyEvent e)
    {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
            e.consume();
    }

    private void payroll(int period) throws SQLException {
        // create a java mysql database connection
        Connection conn = JDBC.getConnection();

        // create the java mysql update preparedstatement
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Name, Surn, Employee.Email, Salary, FLOOR(DATEDIFF(CURDATE(), Recrdate)/30) Months, FLOOR(DATEDIFF(CURDATE(), Recrdate)/30 *Salary) Wage FROM Employee " +
                "INNER JOIN Administrative ON Employee.Paper_Name = Administrative.Paper_Name " +
                "WHERE Administrative.Email = '" + E_mail_TXTFLD.getText() + "' AND (FLOOR(DATEDIFF(CURDATE(), Recrdate)/30)) <= " + period);
        ResultSetTableModel model = new ResultSetTableModel(rs);
        A_Payroll_TBL.setModel(model);
        conn.close();
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public Newspaper() throws SQLException
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setContentPane(Main_Window);
        setTitle("Newspaper");

        Login_BTN.addActionListener(actionEvent ->
        {
            switchPanel(Administrative);
            try {
                populate(P_Amout_Sheet,"SELECT Sheet_Num from Sheet");
                populate(P_EditorBox, "select Email from Employee");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*String email = E_mail_TXTFLD.getText();
            String password = Pswd_TXTFLD.getText();
            boolean flag = false;
            if (email.length()>0 && password.length()>0)
            {
                String as = Objects.requireNonNull(jobRole()).getText();
                if (as != null)
                try {
                    flag = JDBC.logIn(email, password, as, Main_Window);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                else
                    JOptionPane.showMessageDialog(Main_Window, "Please select the appropriate job role!");

                if (flag) //credentials check out!
                    login(as, email);
            }
            else
                JOptionPane.showMessageDialog(Main_Window, "Enter credentials first!");*/
        });



        J_Submit_BTN.addActionListener(actionEvent -> {
            String path = J_path.getText();
            String title = J_Title.getText();
            String summary = J_summary.getText();
            //String category = J_category.getText();
            String writers = J_writers.getText();
            String tags = J_tags.getText();
            int pages = Integer.parseInt(J_pages.getText());
            String images = J_images.getText();

            String query = "INSERT INTO Article VALUES ('Article16', 1,'ARTHRO 1', '3', 'sum 1', 'ntiri@nyt.com',1,'ACCEPTED', 1, 'nyt', '2020-10-10',NULL)";

            try {
                insertQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        J_Rev_Submit_BTN.addActionListener(actionEvent -> {
            String path = J_Rev_path.getText();
            String title = J_Rev_Title.getText();
            String summary = J_Rev_summary.getText();
            //String category = J_Rev_category.getText();
            String writers = J_Rev_writers.getText();
            String tags = J_Rev_tags.getText();
            int pages = Integer.parseInt(J_Rev_pages.getText());
            String images = J_Rev_Images.getText();

            String query = "UPDATE ";
            try {
                updateQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        );

        E_Submit_Article_BTN.addActionListener(actionEvent -> {
            String path = E_path.getText();
            String title = E_Title.getText();
            String summary = E_summary.getText();
            //String category = E_category.getText();
            String writers = E_writers.getText();
            String tags = E_tags.getText();
            int pages = Integer.parseInt(E_pages.getText());
            String images = E_images.getText();

            String query = "INSERT INTO Article VALUES";
            try {
                insertQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        A_Payroll_OKBTN.addActionListener(actionEvent -> {
            int months = Integer.parseInt(A_months.getText());

            try {
                payroll(months);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Show_psw_chkbx.addActionListener(actionEvent ->
        {
            if (Show_psw_chkbx.isSelected())
                Pswd_TXTFLD.setEchoChar((char)0);
            else
                Pswd_TXTFLD.setEchoChar('*');

        });

        J_logoutBTN.addActionListener(actionEvent ->
                switchPanel(Login_Screen));

        E_logoutBTN.addActionListener(actionEvent ->
                switchPanel(Login_Screen));

        A_logoutBTN.addActionListener(actionEvent ->
                switchPanel(Login_Screen));

        P_logoutBTN.addActionListener(actionEvent ->
                switchPanel(Login_Screen));

        J_pages.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){isDigit(e);}
        });

        J_Rev_pages.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        E_pages.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        A_months.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        A_sheet_returned.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        A_volume_returned.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        E_Approve_Order.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        E_Appear_Order.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {isDigit(e);}
        });

        A_returned_Submit_BTN.addActionListener(actionEvent ->
        {
            int sheet = Integer.parseInt(A_sheet_returned.getText());
            int volume = Integer.parseInt(A_volume_returned.getText());
            try {
                updateQuery("update Sheet set Unsold_Copies = " + volume + " where Sheet_Num = " + sheet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        E_Category_Submit_BTN.addActionListener(actionEvent ->
        {
            String category = E_newCategory.getText();
            Object a = E_CatParent.getSelectedItem();
            String parent = null;
            if (a != null)
                parent = E_CatParent.getSelectedItem().toString();
            String description = E_CatDescr.getText();

            try {
                insertQuery("INSERT INTO Category VALUES (DEFAULT"+ ",(SELECT Cat_ID from Category WHERE Cat_Name='"+parent+"'),'" +category + "','" +description+ "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void populate(JComboBox<String> box, String que) throws SQLException
    {
        Connection conn = JDBC.getConnection();

        // create the java mysql update preparedstatement
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(que);

        box.addItem(null);

        while (rs.next())
        {
            box.addItem(rs.getString(1));
        }

        conn.close();
    }

    private JRadioButton jobRole()
    {
        if (J_RDBTN.isSelected())
            return J_RDBTN;
        else if (E_RDBTN.isSelected())
            return E_RDBTN;
        else if (A_RDBTN.isSelected())
            return A_RDBTN;
        else if (P_RDBTN.isSelected())
            return P_RDBTN;
        else return null;
    }
}
