package frontend.inputVerifiers;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class NameDocument extends PlainDocument {
    private String text = "";

    @Override
    public void insertString(int offset, String txt, AttributeSet a) {
        try {
            text = getText(0, getLength());

            if((text + txt).matches("^[a-zA-Z]{0,18}$")) {
                if(txt.length() > 1) {
                    if(text.isEmpty()) super.insertString(offset, String.valueOf(txt.charAt(0)).toUpperCase() + txt.substring(1).toLowerCase(), a);
                    else super.insertString(offset, txt.toLowerCase(), a);
                } else {
                    if(text.isEmpty()) super.insertString(offset, String.valueOf(txt.charAt(0)).toUpperCase(), a);
                    else super.insertString(offset, String.valueOf(txt.charAt(0)).toLowerCase(), a);
                }
            }
        } catch (Exception ignored) {}
    }
}