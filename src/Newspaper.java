import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Newspaper extends JFrame{
    private JPanel Journalist;
    private JButton button1;
    private JPasswordField Pswd_TXTFLD;
    private JTextField Username_TXTFLD;
    private JCheckBox Show_psw_chkbx;
    private JPanel Main_Window;
    private JPanel Login_Screen;
    private JPanel Editor;
    private JPanel Admin;
    private JPanel Publisher;
    private JLabel Username_Label;
    private JLabel Pswd_Label;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane4;
    private JTextField cUsersArticlesSubmittedTextField;
    private JTextArea Summary;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton submitButton;
    private JTextField J_Revise_Path;
    private JTextArea J_Revise_Summary;
    private JTextField J_Revise_Category;
    private JTextField J_Revise_Writers;
    private JTextField J_Revise_Tags;
    private JFormattedTextField J_Revise_Pages;
    private JButton J_Revise_Submit;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton OKButton;
    //private CardLayout c1 = (CardLayout)Main_Window.getLayout();

    public void switchPanel(JPanel panel) {
        Main_Window.removeAll();
        Main_Window.add(panel);
        Main_Window.repaint();
        Main_Window.revalidate();
    }

    public Newspaper(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setContentPane(Main_Window);
        setTitle("Newspaper");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String username = Username_TXTFLD.getText();
                String pswd = Pswd_TXTFLD.getText();

                if (username.equals("J"))
                {
                    setTitle(getTitle() + " - Journalist");
                    switchPanel(Journalist);
                }
                else if (username.equals("E"))
                {
                    setTitle(getTitle() + " - Editor in Chief");
                    switchPanel(Editor);
                }
                else if (username.equals("A"))
                {
                    setTitle(getTitle() + " - Administrator");
                    switchPanel(Admin);
                }
                else if (username.equals("P"))
                {
                    setTitle(getTitle() + " - Publisher");
                    switchPanel(Publisher);
                }
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        try (Connection conn = JDBC.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Newspaper frame = new Newspaper();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
