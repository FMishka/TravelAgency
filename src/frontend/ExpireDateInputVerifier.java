package frontend;

import javax.swing.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class ExpireDateInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        try {
            LocalDate date = LocalDate.parse("01/" + text, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (Exception e) {
            showMessageDialog(null, "Expire date should be in format MM/yy", "Invalid expire date", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}