package logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tasosxak
 * @author Thanasis
 * @since 19/1/2017
 * @version 1.0
 * 
 */
public class BetRoundTest {

    public BetRoundTest() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of getBetOptions method, of class BetRound.
     */
    @Test
    public void testGetBetOptions() {

        List<String> bet_options;
        BetRound bRound;

        //4 bets
        bet_options = new ArrayList<>();
        bet_options.add("250");
        bet_options.add("500");
        bet_options.add("750");
        bet_options.add("1000");
        bRound = new BetRound(bet_options);

        assertTrue(bet_options.equals(bRound.getBetOptions()));

        //3 bets
        bet_options = new ArrayList<>();
        bet_options.add("250");
        bet_options.add("750");
        bet_options.add("1000");
        bRound = new BetRound(bet_options);

        assertTrue(bet_options.equals(bRound.getBetOptions()));

        //0 bets
        bet_options = new ArrayList<>();
        bRound = new BetRound(bet_options);

        assertTrue(bet_options.equals(bRound.getBetOptions()));

    }

}
