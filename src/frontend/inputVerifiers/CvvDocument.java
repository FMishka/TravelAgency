package frontend.inputVerifiers;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class CvvDocument extends PlainDocument {
    private String text = "";

    @Override
    public void insertString(int offset, String txt, AttributeSet a) {
        try {
            text = getText(0, getLength());

            if((text + txt).matches("^[0-9]{0,3}$")) {
                super.insertString(offset, txt, a);
            }
        } catch (Exception ignored) {}
    }
}