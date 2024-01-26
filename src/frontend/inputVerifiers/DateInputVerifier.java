package frontend.inputVerifiers;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static javax.swing.JOptionPane.showMessageDialog;

public class DateInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                    .ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate date = LocalDate.parse(text, dateTimeFormatter);
        } catch (Exception e) {
            showMessageDialog(null, "Required format: \nyyyy-MM-dd", "Invalid date format", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}