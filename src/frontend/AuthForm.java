package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        submitButton.addActionListener(Controller.authSubmitButton(this));
        login.addKeyListener(Controller.authSubmitKey(this));
        password.addKeyListener(Controller.authSubmitKey(this));
    }
}