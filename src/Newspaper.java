import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JTextField J_category;
    private JButton J_Submit_BTN;
    private JTextField J_Rev_path;
    private JTextArea J_Rev_summary;
    private JTextField J_Rev_category;
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
    private JTextField E_category;
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
    private JTable table1;
    private JTextField J_Title;
    private JTextField J_Rev_Title;
    private JTextField E_Title;

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

    private void login(String as, String email)
    {
        switch (as)
        {
            case "Journalist":
                J_welcome.setText("Logged in as: " + email);
                switchPanel(Journalist);
                break;
            case "Editor":
                E_welcome.setText("Logged in as: " + email);
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

    private void submit_article(String path, String summary, String category, String writers, String tags, int pages, String Images)
    {
        // sql submit article ---
    }

    private void payroll(int from)
    {
        // sql view payroll
    }

    public Newspaper(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setContentPane(Main_Window);
        setTitle("Newspaper");

        Login_BTN.addActionListener(actionEvent ->
        {
            switchPanel(Journalist);
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
            String summary = J_summary.getText();
            String category = J_category.getText();
            String writers = J_writers.getText();
            String tags = J_tags.getText();
            int pages = Integer.parseInt(J_pages.getText());
            String images = J_images.getText();

            submit_article(path, summary, category, writers, tags, pages, images);
        });

        J_Rev_Submit_BTN.addActionListener(actionEvent -> {
            String path = J_Rev_path.getText();
            String summary = J_Rev_summary.getText();
            String category = J_Rev_category.getText();
            String writers = J_Rev_writers.getText();
            String tags = J_Rev_tags.getText();
            int pages = Integer.parseInt(J_Rev_pages.getText());
            String images = J_Rev_Images.getText();

            submit_article(path, summary, category, writers, tags, pages, images);
        }
        );

        E_Submit_Article_BTN.addActionListener(actionEvent -> {
            String path = E_path.getText();
            String summary = E_summary.getText();
            String category = E_category.getText();
            String writers = E_writers.getText();
            String tags = E_tags.getText();
            int pages = Integer.parseInt(E_pages.getText());
            String images = E_images.getText();

            submit_article(path, summary, category, writers, tags, pages, images);
        });

        A_Payroll_OKBTN.addActionListener(actionEvent -> {
            int from = Integer.parseInt(A_months.getText());

            payroll(from);
        });

        Show_psw_chkbx.addActionListener(actionEvent ->
        {
            if (Show_psw_chkbx.isSelected())
                Pswd_TXTFLD.setEchoChar((char)0);
            else
                Pswd_TXTFLD.setEchoChar('*');

        });

        J_logoutBTN.addActionListener(actionEvent ->
        {
            switchPanel(Login_Screen);
        });

        E_logoutBTN.addActionListener(actionEvent ->
        {
            switchPanel(Login_Screen);
        });

        A_logoutBTN.addActionListener(actionEvent ->
        {
            switchPanel(Login_Screen);
        });

        P_logoutBTN.addActionListener(actionEvent ->
        {
            switchPanel(Login_Screen);
        });

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

        A_returned_Submit_BTN.addActionListener(actionEvent ->
        {
            int sheet = Integer.parseInt(A_sheet_returned.getText());
            int volume = Integer.parseInt(A_volume_returned.getText());
            //
        });
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
