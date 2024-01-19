package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class View {
    static JFrame mainFrame;

    static final AuthForm authForm = new AuthForm();
    static final HomePage homePage = new HomePage();
    static final AnotherPage anotherPage = new AnotherPage();
    static final FlightsPage flightsPage = new FlightsPage();
    static final FlightInfo flightInfo = new FlightInfo();

    static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void setSize(int width, int height) {
        mainFrame.setSize(width, height);
        mainFrame.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    public static void goToHomePage() {
        setSize(800, 600);
        mainFrame.setContentPane(homePage.getContent());
        mainFrame.revalidate();
    }

    public static void goToAnotherPage() {
        setSize(800, 600);
        mainFrame.setContentPane(anotherPage.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightsPage() {
        setSize(800, 600);
        mainFrame.setContentPane(flightsPage.getContent());
        mainFrame.revalidate();
    }

    public static void goToAuthForm() {
        setSize(400, 350);
        mainFrame.setContentPane(authForm.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightInfo(String[] data) {
        setSize(600, 700);
        flightInfo.setData(data);
        mainFrame.setContentPane(flightInfo.getContent());
        mainFrame.revalidate();
    }

    public static void init() {
        mainFrame = new JFrame("Page1");
        //mainFrame.setContentPane(homePage.getContent());
        mainFrame.setContentPane(authForm.getContent());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        mainFrame.setIconImage(new ImageIcon(Objects.requireNonNull(View.class.getResource("planeIcon.png"))).getImage());
        setSize(400, 350);

        mainFrame.setTitle("Travel Agency");
    }
}
