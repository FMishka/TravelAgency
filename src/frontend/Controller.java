package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public abstract class Controller {
    public static final int ADMIN = 1;
    public static final int USER = 0;

    public static ActionListener authSubmit(AuthForm authForm) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Model.validateCredentials(authForm.login.getText(), authForm.password.getText())) {
                    case USER:
                        showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                        View.setSize(500, 500);
                        View.goToHomePage();
                        break;
                    case ADMIN:
                        showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                        View.setSize(500, 500);
                        View.goToAnotherPage();
                        break;
                    default:
                        showMessageDialog(null, "Wrong login or password", "Error", JOptionPane.ERROR_MESSAGE);
                        authForm.login.setText("");
                        authForm.password.setText("");
                }
            }
        };
    }

    public static ActionListener navigateHome() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToHomePage();
            }
        };
    }

    public static ActionListener navigateAnother() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToAnotherPage();
            }
        };
    }

    public static ActionListener logout() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToAuthForm();
                View.setSize(400, 350);
            }
        };
    }
}
