package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AuthForm {
    JPanel content;
    JButton submitButton;
    JTextField login;
    JPasswordField password;

    public JPanel getContent() {
        return content;
    }

    public AuthForm() {
        submitButton.addActionListener(Controller.authSubmit(this));
    }
}