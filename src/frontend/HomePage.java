package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HomePage {

    private JButton homePageButton;
    private JPanel content;
    private JButton myTicketsPageButton;
    private JButton logout;
    private JPanel navigation;
    private JButton flightsButton;
    private JPanel cats;
    private JPanel mainPanel;
    private JLabel label;

    public JPanel getContent() {
        return content;
    }

    public HomePage() {
        homePageButton.addActionListener(Controller.navigateHome());
        myTicketsPageButton.addActionListener(Controller.navigateMyTickets());
        flightsButton.addActionListener(Controller.navigateFlights());
        logout.addActionListener(Controller.logout());

        cats.setLayout(new GridLayout(1, 2));

        for(int i = 0; i < 2; i++) {
            JLabel label = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/cat.gif"))));
            cats.add(label);
        }


    }
}
