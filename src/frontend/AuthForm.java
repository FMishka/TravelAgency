package frontend;

import javax.swing.*;

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