package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;

public abstract class Controller {
    public static final int ADMIN = 1;
    public static final int USER = 0;

    public static void authSubmit(AuthForm authForm) {
        switch (Model.validateCredentials(authForm.login.getText(), authForm.password.getText())) {
            case USER:
                showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                View.goToHomePage();
                break;
            case ADMIN:
                showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                View.goToFlightsPage();
                break;
            default:
                showMessageDialog(null, "Wrong login or password", "Error", JOptionPane.ERROR_MESSAGE);
                authForm.login.setText("");
                authForm.password.setText("");
        }
    }

    public static ActionListener authSubmitButton(AuthForm authForm) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authSubmit(authForm);
            }
        };
    }

    public static KeyListener authSubmitKey(AuthForm authForm) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == '\n') authSubmit(authForm);
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

    public static ActionListener navigateFlights() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightsPage();
            }
        };
    }

    public static ActionListener logout() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToAuthForm();
            }
        };
    }
}
