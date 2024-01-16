package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

    private JButton homePageButton;
    private JPanel content;
    private JPanel navigation;
    private JButton anotherPageButton;
    private JTextField helloAllThisIsTextField;

    public JPanel getContent() {
        return content;
    }

    public HomePage() {
        homePageButton.addActionListener(Controller.navigateHome());
        anotherPageButton.addActionListener(Controller.navigateAnother());
    }
}
