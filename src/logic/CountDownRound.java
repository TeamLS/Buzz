package logic;

/**
 * Αυτή η κλάση υλοποιεί τον τύπο γύρου Αντίστροφη Μέτρηση. Κληρονομεί από την
 * κλάση Round.
 *
 * @author tasosxak
 * @author Thanasis
 * @since 8/1/17
 * @version 1.0
 */
public class CountDownRound extends Round {

    private int seconds;

    public CountDownRound(int times, int seconds) {

        super(times, "countdownLabel", "countdownDesc");

        this.seconds = seconds;

    }

    @Override
    public String[] getDescInfo() {
        return null;
    }

    public CountDownRound(int times) {

        this(times, 5);

    }

    public CountDownRound() {

        this(5, 5);

    }

    public int getSeconds() {

        return seconds;

    }

    @Override
    public void setBonus(int time) {
        super.setBonus((int) (time * 0.2)); 
    }

}
