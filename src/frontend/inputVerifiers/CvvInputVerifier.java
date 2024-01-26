package frontend.inputVerifiers;

import javax.swing.*;

import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class CvvInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = Arrays.toString(((JPasswordField) input).getPassword());

        try {
            int cvv = Integer.parseInt(text);
            if(cvv < 100 || cvv > 999) throw new Exception();
        } catch (Exception e) {
            showMessageDialog(null, "cvv should be 3-digit number", "Invalid cvv", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}