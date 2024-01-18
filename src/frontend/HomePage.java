package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

    private JButton homePageButton;
    private JPanel content;
    private JButton anotherPageButton;
    private JButton logout;
    private JPanel navigation;
    private JButton flightsButton;

    public JPanel getContent() {
        return content;
    }

    public HomePage() {
        homePageButton.addActionListener(Controller.navigateHome());
        anotherPageButton.addActionListener(Controller.navigateAnother());
        flightsButton.addActionListener(Controller.navigateFlights());
        logout.addActionListener(Controller.logout());
    }
}
