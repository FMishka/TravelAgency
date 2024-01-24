package frontend;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class CardholderInputVerifier extends InputVerifier {
    public boolean validateCardholder(String str) {
        boolean onFirstWord = true;
        boolean onSecondWord = false;

        for(char ch : str.toCharArray()) {
            if(ch == ' ' && onFirstWord) {
                onFirstWord = false;
                continue;
            }

            if(ch < 'A' || ch > 'Z') return false;
            else if(!onFirstWord) onSecondWord = true;
        }

        return !onFirstWord && onSecondWord;
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        if(validateCardholder(text)) {
            return true;
        } else {
            showMessageDialog(null, "Wrong cardholder name format\nExample: YOSSI COHEN", "Invalid cardholder name", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}