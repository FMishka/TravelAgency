package frontend;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class DateInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        try {
            LocalDate date = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            showMessageDialog(null, "Required format: \nyyyy-MM-dd", "Invalid date format", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}