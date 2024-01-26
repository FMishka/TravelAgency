package frontend.inputVerifiers;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class PassportInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        if(text.length() == 8 && text.matches("\\d+")) {
            return true;
        } else {
            showMessageDialog(null, "Passport should be a 8-digit number", "Invalid passport", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}