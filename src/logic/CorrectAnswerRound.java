package logic;

/**
 *
 * Αυτή η κλάση υλοποιεί τον τύπο γύρου Σωστή Απάντηση. Κληρονομεί από την κλάση
 * Round.
 *
 * @author tasosxak
 * @author Thanasis
 * @since 8/11/16
 * @version 1.0
 *
 */
public class CorrectAnswerRound extends Round {

    public CorrectAnswerRound(int times, int bonus) {

        super(times, bonus, "correctanswerLabel", "correctanswerDesc");

    }

    public CorrectAnswerRound(int times) {
        this(times, 1000);
    }

    public CorrectAnswerRound() {
        this(5, 1000);
    }

    @Override
    public String[] getDescInfo() {
        return new String[]{String.valueOf(bonus)};
    }

}
