package frontend;

import frontend.inputVerifiers.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public abstract class Utility {
    public static final String DEFAULT_DATETIME = "2000-01-01 00:00";

    public static void setDateTimeFormat(JFormattedTextField field, String defaultValue) {
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-## ##:##");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            field.setInputVerifier(new DateTimeInputVerifier());

            if(defaultValue != null) field.setText(defaultValue);
        } catch (Exception ignored) {}
    }

    public static void setDateFormat(JFormattedTextField field, String defaultValue) {
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-##");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            field.setInputVerifier(new DateInputVerifier());

            if(defaultValue != null) field.setText(defaultValue);
        } catch (Exception ignored) {}
    }

    public static void setPassportFormat(JFormattedTextField field) {
        try {
            MaskFormatter formatter = new MaskFormatter("########");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            field.setInputVerifier(new PassportInputVerifier());
        } catch (Exception ignored) {}
    }

    public static void setNameFormat(JTextField field) {
        field.setDocument(new NameDocument());
    }

    public static void setCardholderFormat(JTextField field) {
        field.setDocument(new CardholderDocument());
        field.setInputVerifier(new CardholderInputVerifier());
    }

    public static void setCreditCardFormat(JFormattedTextField field) {
        try {
            MaskFormatter formatter = new MaskFormatter("#### #### #### ####");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            field.setInputVerifier(new CreditCardNumberVerifier());
        } catch (Exception ignored) {}
    }

    public static void setExpireDateFormat(JFormattedTextField field) {
        try {
            MaskFormatter formatter = new MaskFormatter("##/##");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            field.setInputVerifier(new ExpireDateInputVerifier());
        } catch (Exception ignored) {}
    }
    
    public static void setCvvFormat(JPasswordField field) {
        field.setDocument(new CvvDocument());
        field.setInputVerifier(new CvvInputVerifier());
    }

    public static void setFlightNameFormat(JFormattedTextField field) {
        try {
            MaskFormatter formatter = new MaskFormatter("UU-###");
            formatter.setPlaceholderCharacter('_');
            field.setFormatterFactory(new DefaultFormatterFactory(formatter));

            //field.setInputVerifier(new ExpireDateInputVerifier());
        } catch (Exception ignored) {}
    }


    public static void setPlaceholder(JTextField jTextField, String placeholder) {
        jTextField.setForeground(Color.GRAY);
        jTextField.setText(placeholder);
        jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField.getText().equals(placeholder)) {
                    jTextField.setText("");
                    jTextField.setForeground(new Color(0,0,0));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField.getText().isEmpty()) {
                    jTextField.setForeground(Color.GRAY);
                    jTextField.setText(placeholder);
                }
            }
        });
    }
}
