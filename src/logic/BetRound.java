package logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Αυτή η κλάση υλοποιεί τον τύπο γύρου Ποντάρισμα. Κληρονομεί από την κλάση
 * Round.
 *
 * @author Thanasis
 * @author tasosxak
 * @since 10/11/16
 * @version 1.0
 *
 */
public class BetRound extends Round {

    private final List<String> bet_options;

    public BetRound(List<String> bet_options, int times) {
        super(times, "betroundLabel", "betroundDesc");
        this.bet_options = new ArrayList<>(bet_options);

    }

    public BetRound(List<String> bet_options) {
        this(bet_options, 5);
    }

    @Override
    public String[] getDescInfo() {
        return null;
    }

    public List<String> getBetOptions() {
        return this.bet_options;
    }

}
