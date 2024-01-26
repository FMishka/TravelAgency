package frontend.inputVerifiers;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static javax.swing.JOptionPane.showMessageDialog;

public class DateTimeInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                    .ofPattern("uuuu-MM-dd HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDateTime dateTime = LocalDateTime.parse(text, dateTimeFormatter);
        } catch (Exception e) {
            showMessageDialog(null, "Required format: \nyyyy-MM-dd HH:mm", "Invalid datetime format", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}