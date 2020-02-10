import javax.swing.*;

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
    private JTextField J_path;
    private JTextArea J_summary;
    private JTextField J_writers;
    private JTextField J_tags;
    private JTextField J_pages;
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
    private JTextField A_fromMonth;
    private JTextField A_toMonth;
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

    private void switchPanel(JPanel panel)
    {
        Main_Window.removeAll();
        Main_Window.add(panel);
        Main_Window.repaint();
        Main_Window.revalidate();
        pack();
    }

    private void submit_article(String path, String summary, String category, String writers, String tags, int pages, String Images)
    {
        // sql submit article ---
    }

    private void payroll(int from, int to)
    {
        // sql view payroll
    }
    public Newspaper(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setContentPane(Main_Window);
        setTitle("Newspaper");

        button1.addActionListener(actionEvent -> {
            String username = Username_TXTFLD.getText();
            String pswd = Pswd_TXTFLD.getText();

            switch (username) {
                case "J":
                    setTitle(getTitle() + " - Journalist");
                    switchPanel(Journalist);
                    break;
                case "E":
                    setTitle(getTitle() + " - Editor in Chief");
                    switchPanel(Editor);
                    break;
                case "A":
                    setTitle(getTitle() + " - Administrator");
                    switchPanel(Admin);
                    break;
                case "P":
                    setTitle(getTitle() + " - Publisher");
                    switchPanel(Publisher);
                    break;
            }
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
            int from = Integer.parseInt(A_fromMonth.getText());
            int to = Integer.parseInt(A_toMonth.getText());

            payroll(from, to);
        });
    }
}
