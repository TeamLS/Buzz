package gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Η κλάση BPanel υλοποιεί τα χαρακτηριστικά του πανελ της εφαρμογής. Κληρονομεί
 * από την JPanel.
 *
 *
 * @author Thanasis
 * @author tasosxak
 * @version 1.0
 * @since 5/1/17
 *
 */
public class BPanel extends JPanel {

    private ImageIcon background;

    public BPanel() {
        this(true, "", 0, 0, 1000, 563); //Default μέγεθος του πανελ 
    }

    public BPanel(String imageName) {
        this(true, imageName, 0, 0, 1000, 563); //Πανελ με background φωτογραφία
    }

    public BPanel(boolean visible) {
        this(visible, "", 0, 0, 1000, 563); //Ορατό ή μη πάνελ
    }

    public BPanel(boolean visible, String imageName) {
        this(visible, imageName, 0, 0, 1000, 563); //Ορατό ή μη πάνελ με background φωτογραφία
    }

    public BPanel(String imageName, int x, int y, int width, int height) {
        this(true, imageName, x, y, width, height); //Πανελ με διαστάσεις , θέση και background φωτογραφία
    }

    public BPanel(int x, int y, int width, int height) {
        this(true, "", x, y, width, height); //Πάνελ με διαστάσεις και θέση
    }

    public BPanel(boolean visible, String imageName, int x, int y, int width, int height) {
        setOpaque(false);
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(visible);

        if (!imageName.equals("")) {
            background = new ImageIcon(getClass().getResource("/images/" + imageName)); //Φόρτωση αρχείου εικόνας 
        }

        /*
        try {
                background = ImageIO.read(new File("src/images/" + imageName));
            } catch (IOException ex) {
                Logger.getLogger(BPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
         */
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null); //Σχεδίαση της φωτογραφίας στο panel
        }
    }

    /**
     * Η setImage επιτρέπει την προσθήκη background image στο πάνελ
     *
     * @param imageName Το όνομα του αρχείου εικόνας μαζί με τον τύπο της
     */
    public void setImage(String imageName) {

        background = new ImageIcon(getClass().getResource("/images/" + imageName));

        /*
        try {
            background = ImageIO.read(new File("src/images/" + imageName));
        } catch (IOException ex) {
            Logger.getLogger(BPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
         */
    }

    /**
     * Η setFocusOnClick μας επιτρέπει να εστιάζουμε στο πανελ όταν γίνεται κλικ
     * και σε αυτό και όχι σε άλλα components που πιθανό είχαμε την έστιαση
     * αρχικά σε ένα από αυτά.
     *
     */
    public void setFocusOnClick() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                requestFocus();
            }
        });

    }

}
