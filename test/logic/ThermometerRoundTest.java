package logic;

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
public class ThermometerRoundTest {

    ThermometerRound therm;

    @Before
    public void setUp() {

        therm = new ThermometerRound(2);

    }

    /**
     * Test of addWin method, of class ThermometerRound.
     */
    @Test
    public void testAddWin() {
        
        System.out.println("addWin");

        assertTrue(therm.addWin(-1) == -1);
        assertTrue(therm.addWin(0) == -1);
        assertTrue(therm.addWin(5) == -1);

        for (int i = 0; i < therm.getMaxHeight(); i++) {
            assertTrue(therm.addWin(2) == therm.getWins(2));
        }

        assertTrue(therm.addWin(2) == 5);
        assertTrue(therm.addWin(2) == 5);
        assertTrue(therm.addWin(2) == 5);
        assertTrue(therm.addWin(2) == 5);
        assertTrue(therm.addWin(1) == 1);
    }

    /**
     * Test of getWins method, of class ThermometerRound.
     */
    @Test
    public void testGetWins() {
        
         System.out.println("getWins");
         
        assertTrue(therm.getWins(-1) == -1);
        assertTrue(therm.getWins(0) == -1);
        assertTrue(therm.getWins(5) == -1);

        for (int i = 0; i < therm.getMaxHeight(); i++) {
            assertTrue(therm.addWin(2) == therm.getWins(2));
        }
        assertTrue(therm.getWins(2) == therm.getMaxHeight());
        therm.addWin(2);
        assertTrue(therm.getWins(2) == therm.getMaxHeight());

        assertTrue(therm.getWins(1) == 0);

    }

    /**
     * Test of hasPlayerWon method, of class ThermometerRound.
     */
    @Test
    public void testHasPlayerWon() {
        
         System.out.println("hasPlayerWon");

        assertTrue(!therm.hasPlayerWon(-1));
        assertTrue(!therm.hasPlayerWon(0));
        assertTrue(!therm.hasPlayerWon(5));

        for (int i = 0; i < therm.getMaxHeight() - 1; i++) {
            therm.addWin(2);
            assertTrue(!therm.hasPlayerWon(2));
        }

        therm.addWin(2);
        assertTrue(therm.hasPlayerWon(2));

        for (int i = 0; i < therm.getMaxHeight(); i++) {
            therm.addWin(1);
        }

        assertTrue(therm.hasPlayerWon(1));

        assertTrue(therm.hasPlayerWon(1) && therm.hasPlayerWon(2));

    }

}
