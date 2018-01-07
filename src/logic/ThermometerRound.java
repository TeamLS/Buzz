
package logic;


/**
 *
 * Αυτή η κλάση υλοποιεί τον τύπο γύρου Θερμόμετρο. Κληρονομεί από την κλάση
 * Round.
 *
 * @author tasosxak
 * @author Thanasis
 * @since 12/1/17
 * @version 1.0
 *
 */
public class ThermometerRound extends Round {

    private int timesToWin; //Το πλήθος των σωστών απαντήσεων που απαιτούνται 
    private Thermometer[] thermometer; //Το θερμόμετρο κάθε χρήστη
   

    public ThermometerRound(int times, int bonus, int timesToWin, int numOfPlayers) {

        super(times, bonus,"thermometerLabel","thermometerDesc");
        
        this.timesToWin = timesToWin;
      

        this.thermometer = new Thermometer[numOfPlayers];
        
        for (int i=0; i < numOfPlayers; i++){
            thermometer[i] = new Thermometer(timesToWin);
        }

    }

    public ThermometerRound(int numOfPlayers) {
        this(1, 5000, 5, numOfPlayers);
    }

    public ThermometerRound() {
        this(1, 5000, 5, 2);
    }
    
    
    @Override
    public String[] getDescInfo() {
        
        return new String[]{String.valueOf(timesToWin),String.valueOf(bonus)};
    }

    /**
     *  Η addWin αυξάνει κατά μια μονάδα το ύψος του υδράργυρου ενός παίχτη.
     * @param player_id  Το id του παίχτη 
     * @return  Επιστρέφει το νέο ύψος του θερμομέτρου του παίχτη(αντιστοιχεί στο πλήθος των σωστών απαντήσεων του)  αν το ID είναι έγκυρο, διαφορετικά  -1
     */
    
    public int addWin(int player_id) {
        if (player_id > 0 && player_id <= thermometer.length) {
            return thermometer[player_id - 1].increase();
        }
        return -1;

    }
    
    /**
     * 
     * @param player_id Το id του παίχτη που θέλουμε να ελέγξουμε πόσες σωστές απαντήσεις έδωσε
     * @return  Αν το id είναι έγκυρο επιστρέφει το πλήθος των σωστών απαντήσεων του παίχτη , διαφορετικά -1
     */
    
    public int getWins(int player_id) {
        if (player_id >0 && player_id <= thermometer.length) {
            return thermometer[player_id - 1].getHeight();
        }
        return -1;
    }

    /**
     * 
     * @param player_id Το id του παίχτη που θέλουμε να ελέγξουμε αν κέρδισε (γέμισε το θερμόμετρο του)
     * @return Επιστρέφει true αν ο παίχτης με το συγκεκριμένο id κέρδισε , διαφορετικά false
     */
    public boolean hasPlayerWon(int player_id) {
            return getWins(player_id) == this.timesToWin;
    }

    public int getMaxHeight(){        
        return this.timesToWin;
    }
 
}
