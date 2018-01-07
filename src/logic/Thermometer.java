package logic;

/**
 * Αυτή η κλάση υλοποιεί τη λειτουργία ενός θερμομέτρου. Χρησιμοποιείται σε
 * γύρους που χρειάζονται έναν αριθμό συνδυασμών σωστών ή και λανθασμένων
 * απαντήσεων για να τερματίσουν.
 *
 *
 * @author tasosxak
 * @author thanasis
 * @since 12/1/17
 * @version 1.0
 */
public class Thermometer {

    private int maxheight;
    private int height;

    public Thermometer() {

        this(5);

    }

    public Thermometer(int height) {

        this.maxheight = height;
        this.height = 0;

    }

    /**
     * Αυξάνει κατά μία μονάδα , το ύψος του υδράργυρου του θερμομέτρου αν είναι
     * μικρότερο από το ύψος του θερμομέτρου.
     *
     * @return Επιστρέφει το ύψος του υδραργύρου
     */
    public int increase() {

        if (height < maxheight) {
            height++;
        }

        return height;

    }

    /**
     * Μειώνει κατά μια μονάδα , το ύψος του υδράργυρου του θερμομέτρου αν είναι
     * μεγαλύτερο του μηδενός.
     *
     * @return Επιστρέφει το ύψος του υδραργύρου
     */
    public int decrease() {

        if (height > 0) {
            height--;
        }

        return height;
    }

    public int getHeight() {
        return height;
    }

}
