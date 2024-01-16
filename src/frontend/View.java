package frontend;

import javax.swing.*;
import java.awt.*;

public abstract class View {
    static JFrame mainFrame;

    static final AuthForm authForm = new AuthForm();
    static final HomePage homePage = new HomePage();
    static final AnotherPage anotherPage = new AnotherPage();

    static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void setSize(int width, int height) {
        mainFrame.setSize(width, height);
        mainFrame.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    public static void goToHomePage() {
        mainFrame.setContentPane(homePage.getContent());
        mainFrame.revalidate();
    }

    public static void goToAnotherPage() {
        mainFrame.setContentPane(anotherPage.getContent());
        mainFrame.revalidate();
    }

    public static void init() {
        mainFrame = new JFrame("Page1");
        //mainFrame.setContentPane(homePage.getContent());
        mainFrame.setContentPane(authForm.getContent());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.setIconImage(new ImageIcon(View.class.getResource("logo.png")).getImage());
        setSize(400, 350);

        mainFrame.setTitle("Travel Agency");
    }
}
