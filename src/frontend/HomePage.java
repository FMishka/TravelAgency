package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private View view;

    private JButton homePageButton;
    private JPanel content;
    private JPanel navigation;
    private JButton anotherPageButton;
    private JTextField helloAllThisIsTextField;

    public JPanel getContent() {
        return content;
    }

    public HomePage(View view) {
        this.view = view;

        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        anotherPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.goToAnotherPage();
            }
        });
    }
}
