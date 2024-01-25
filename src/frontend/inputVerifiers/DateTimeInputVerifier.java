package frontend.inputVerifiers;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class DateTimeInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            showMessageDialog(null, "Required format: \nyyyy-MM-dd HH:mm", "Invalid datetime format", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}