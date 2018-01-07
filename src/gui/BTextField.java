
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Η κλάση BTextField υλοποιεί τα χαρακτηριστικά του πλαίσιου κειμένου της εφαρμογής. Κληρονομεί
 * από την JTextField.
 *
 *
 * @author Thanasis
 * @author tasosxak
 * @version 1.0
 * @since 5/1/17
 *
 */
public class BTextField extends JTextField {

    private final String placeHolder; //Αρχικό default κείμενο
    boolean hasBeenClicked;

    public BTextField(final String placeHolder) {
        this.placeHolder = placeHolder;
        hasBeenClicked = false;

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        setSize(200, 40);
        setText(placeHolder); 
        setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); //Γραμματοσειρά 
        setBorder(new EmptyBorder(5, 15, 5, 15)); //padding και κλίση γωνιώνν
        setForeground(Color.GRAY); //χρώμα γραμματοσειράς

        //Αν έχει πατηθεί το textfield
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getDisplayedText().equals(placeHolder) && !hasBeenClicked) {
                    setText(""); //Σβήνεται το περιεχόμενο σε περίπτωση που ήταν το default κείμενο 
                    setForeground(Color.BLACK); 
                    hasBeenClicked = true;
                }
            }

            //Αν έχει φύγει η εστίαση από το text field
            @Override
            public void focusLost(FocusEvent e) {
                if (getDisplayedText().equals("")) { //Αν είναι κενό το πλαίσιο κειμένου ξανά μπαίνει το placeholder
                    setText(placeHolder);
                    setForeground(Color.GRAY);
                    hasBeenClicked = false;
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon(getClass().getResource("/images/buzz_images/BTF.png")).getImage(), 0, 0, null); //Σχεδιάση background στο textfield
        super.paintComponent(g);
    }

    @Override
    public String getText() {
        if (isEmpty()) {
            return "";
        }
        return super.getText();
    }

    private String getDisplayedText() {
        return super.getText();
    }

    /**
     * 
     * @return Αν είναι κενό το textField επστρέφει true,διαφορετικά false
     */
    public boolean isEmpty() {
        return getForeground().equals(Color.GRAY);
    }
    
    /**
     * Η refresh ανανεώνει το textfield και θέτει το πλαίσιο κειμένου στην αρχική του κατάσταση, σαν να μην τροποποιήθηκε,
     */
    public void refresh() {
        setText(placeHolder);
        setForeground(Color.GRAY);
        hasBeenClicked = false;
    }

}
