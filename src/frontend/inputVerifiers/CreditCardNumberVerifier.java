package frontend.inputVerifiers;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class CreditCardNumberVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        if(text.length() == 16 && text.matches("-?\\d+(\\.\\d+)?")) {
            return true;
        } else {
            showMessageDialog(null, "Credit card number should be a 16-digit number", "Invalid credit card number", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}