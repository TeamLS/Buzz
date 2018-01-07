package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author thanasis
 * @author tasosxak
 * @since 30/11/16
 * @version 1.0
 */
public class RoundListTest {


    public RoundListTest() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of hasNext method, of class RoundList.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");

        RoundList nowrongroundlist = new RoundList(-1);
        boolean hasNext = nowrongroundlist.hasNext();
        assertEquals(true, hasNext);

        RoundList noemptyroundlist = new RoundList(0);
        hasNext = noemptyroundlist.hasNext();
        assertEquals(true, hasNext);
        
        
        RoundList roundlist;
        roundlist = new RoundList(2, 1);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(true, hasNext);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(true, hasNext);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(false, hasNext);
        
        roundlist = new RoundList(2, 2);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(true, hasNext);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(true, hasNext);

        hasNext = roundlist.hasNext();
        roundlist.getNextRound();
        assertEquals(false, hasNext);
        

    }

    /**
     * Test of getNextRound method, of class RoundList.
     */
    @Test
    public void testGetNextRound() {
        System.out.println("getNextRound");
        
        RoundList noemptyroundlist = new RoundList(0);
        Round curRound = noemptyroundlist.getNextRound();
        assertTrue(curRound != null);

         // Εκτέλεση 2 γύρων με 1 παίχτη, άρα στον 3ο γύρο πρέπει να επιστραφεί null
        RoundList roundlist = new RoundList(2,1);
       
        curRound = roundlist.getNextRound();
        assertTrue(curRound instanceof CorrectAnswerRound || curRound instanceof BetRound || curRound instanceof CountDownRound);
        
        curRound = roundlist.getNextRound(); 
        assertTrue(curRound instanceof CorrectAnswerRound || curRound instanceof BetRound || curRound instanceof CountDownRound);
        
        curRound = roundlist.getNextRound();
        assertEquals(null, curRound);
        curRound = roundlist.getNextRound();
        assertEquals(null, curRound); 

        // Εκτέλεση 2 γύρων με 2 παίχτες, άρα στον 3ο γύρο πρέπει να επιστραφεί null
        roundlist = new RoundList(2,2);
       
        curRound = roundlist.getNextRound();
        assertTrue(curRound != null);
        
        curRound = roundlist.getNextRound(); 
        assertTrue(curRound != null);
        
        curRound = roundlist.getNextRound();
        assertEquals(null, curRound);
        curRound = roundlist.getNextRound();
        assertEquals(null, curRound);
        
    }

}
