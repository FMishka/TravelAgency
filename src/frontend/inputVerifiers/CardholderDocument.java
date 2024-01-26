package frontend.inputVerifiers;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class CardholderDocument extends PlainDocument {
    private String text = "";

    @Override
    public void insertString(int offset, String txt, AttributeSet a) {
        try {
            text = getText(0, getLength());

            if((text + txt).matches("^([a-zA-Z]{0,18})$")) {
                super.insertString(offset, txt.toUpperCase(), a);
            }

            if((text + txt).matches("^([a-zA-Z]{0,18})(\\s)([a-zA-Z]{0,18})$")) {
                super.insertString(offset, txt.toUpperCase(), a);
            }
        } catch (Exception ignored) {}
    }
}