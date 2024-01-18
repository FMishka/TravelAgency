package frontend;

import javax.swing.*;
import java.util.Objects;

public class AnotherPage {
    private View view;

    private JPanel panel1;
    private JTextField thisIsAnothaPageTextField;
    private JButton homePageButton;
    private JButton anotherPageButton;
    private JButton flightsButton;

    private JLabel label;
    private JPanel navigation;
    private ImageIcon img;


    public JPanel getContent() {
        return panel1;
    }

    public AnotherPage() {
        homePageButton.addActionListener(Controller.navigateHome());
        anotherPageButton.addActionListener(Controller.navigateAnother());
        flightsButton.addActionListener(Controller.navigateFlights());

        try {
            img = new ImageIcon(Objects.requireNonNull(getClass().getResource("planeIcon.png")));
            label.setIcon(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}