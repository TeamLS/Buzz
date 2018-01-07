package gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 * Η κλάση BFrame υλοποιεί τα χαρακτηριστικά του frame της εφαρμογής. Κληρονομεί
 * από την JFrame.
 *
 * @author tasosxak
 * @author Thanasis
 * @version 1.0
 * @since 7/1/17
 *
 */
public class BFrame extends JFrame {

    JLabel basiclabel = new JLabel();

    JWindow window = new JWindow();

    public BFrame(String name) {

        loadImage();  //SplashScreen (φόρτωση αρχικής εισαγωγικής εικόνας)

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(name);
        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(1000, 563));
        pack();
        pack();
        setLocationRelativeTo(null);

    }

    private void loadImage() {

        //Δημιουργία παραθύρου με την φωτογραφία
        window.getContentPane().add(
                new JLabel("", new ImageIcon(getClass().getResource("/images/buzz_images/1.jpg")), SwingConstants.CENTER));

        window.setSize(580, 278);
        window.setLocationRelativeTo(null); //στοίχηση στο κέντρο

        window.setVisible(true);
        try {
            Thread.sleep(2000); //Εμφάνιση του παραθύρου αυτού για 2 δευτερόλεπτα
        } catch (InterruptedException e) {
            e.getMessage();
        }

        window.dispose(); //Εξαφάνιση του παραθύρου αυτού

    }

}
