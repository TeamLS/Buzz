package logic;

/**
 *
 * Η κλάση Round είναι μια αφαιρετική κλάση η οποία περιέχει τη γενική μορφή που
 * έχει ένας τύπος γύρου. Όλοι οι τύποι γύρων όπως η CorrectAnswerRound και η
 * BetRound κληρονομούν από αυτή γενικά χαρακτηριστικά που χαρακτηρίζουν κάτι ως
 * Round.
 *
 * @author tasosxak
 * @author thanasis
 * @since 9/11/16
 * @version 1.0
 */
public abstract class Round {

    protected String typeName;
    protected String description;
    protected int executeTimes;
    protected int bonus;

    protected Round(int times) {
        this.executeTimes = times;
    }

    protected Round(int times, String title, String desc) {
        this.executeTimes = times;
        this.typeName = title;
        this.description = desc;
    }

    protected Round(int times, int bonus, String title, String desc) {
        this(times, title, desc);
        this.bonus = bonus;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getDesc() {
        return this.description;
    }

    /**
     * Η getDescInfo χρησιμοποιείται για να πάρουμε πληροφορίες για έναν γύρο.
     * Αυτό χρησιμεύει όταν θέλουμε να τυπώσουμε αυτές τις πληροφορίες. Επίσης
     * υλοποιείται διαφορετικά ανάλογα τον γύρο και τα χαρακτηριστικά αυτού.
     *
     * @return Επιστρέφει έναν πίνακα συμβολοσειρών με τις πληροφορίες και τα
     * χαρακτηριστικά κάθε γύρου (π.χ. βαθμοί, επιτρεπτά πονταρίσματα κτλπ)
     */
    public abstract String[] getDescInfo();

    public int getExecuteTimes() {
        return this.executeTimes;
    }

    public int getBonus() {
        return this.bonus;
    }

    public void setBonus(int newBonus) {
        this.bonus = newBonus;
    }

    /**
     * Πρέπει να καλείτε όταν ο γύρος έχει εκτελεστεί μια φορά. Η executed
     * μειώνει κατά ένα τις επιτρεπτές εκτελέσεις του γύρου.
     */
    public void executed() {
        if (executeTimes > 0) {
            this.executeTimes--;
        }
    }

}
