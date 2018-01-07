package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Η κλάση BLabel υλοποιεί τα χαρακτηριστικά του κειμένου της εφαρμογής.
 * Κληρονομεί από την JLabel.
 *
 *
 * @author Thanasis
 * @author tasosxak
 * @version 1.0
 * @since 7/1/17
 *
 */
public class BLabel extends JLabel {

    boolean multiLine;
    boolean center;

    public BLabel() {
        this("");
    }

    public BLabel(String text) {
        this(text, 20, false, SwingConstants.CENTER);
    }

    public BLabel(String text, boolean multiLine, int alignment) {
        this(text, 20, true, alignment);
    }

    public BLabel(String text, int fontSize) {
        this(text, fontSize, false, SwingConstants.CENTER);
    }

    public BLabel(String text, int fontSize, boolean multiLine, int alignment) {
        setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize)); //Ορισμός γραμματοσειράς
        setForeground(Color.white); //Ορισμός χρώματος γραμματοσειράς

        center = false;
        if (alignment == SwingConstants.CENTER) {
            center = true;
        }
        this.multiLine = multiLine;

        //Στοίχηση κειμένου
        setHorizontalAlignment(alignment);
        setVerticalAlignment(SwingConstants.CENTER);
        setText(text);
    }

    @Override
    public void setText(String text) {
        if (multiLine) {
            if (center) {
                text = "<html><center>" + text + "</center></html>"; //Στοίχηση στο κέντρο και αν είναι παραπάνω από μια γραμμή τοποθέτηση από κάτω
            } else {
                text = "<html>" + text + "</html>";
            }
        }
        super.setText(text);
    }

}
