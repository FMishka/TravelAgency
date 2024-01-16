package frontend;

import javax.swing.*;
import java.awt.*;

public class View {
    private static JFrame mainFrame;

    private final HomePage homePage;
    private final AnotherPage anotherPage;

    public void goToHomePage() {
        mainFrame.setContentPane(homePage.getContent());
        mainFrame.revalidate();
    }

    public void goToAnotherPage() {
        mainFrame.setContentPane(anotherPage.getContent());
        mainFrame.revalidate();
    }

    public View() {
        homePage = new HomePage(this);
        anotherPage = new AnotherPage(this);

        mainFrame = new JFrame("Page1");
        mainFrame.setContentPane(homePage.getContent());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        mainFrame.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        mainFrame.setSize(500, 500);
        mainFrame.setLocation(dimension.width / 2 - 250, dimension.height / 2 - 250);
        mainFrame.setTitle("Самолеты");
    }
}
