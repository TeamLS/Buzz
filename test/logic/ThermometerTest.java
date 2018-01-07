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
public class ThermometerTest {

    Thermometer ther;

    @Before
    public void setUp() {

        ther = new Thermometer(5);
    }

    /**
     * Test of increase method, of class Thermometer.
     */
    @Test
    public void testIncrease() {

        for (int i = 0; i < ther.getHeight(); i++) {
            assertTrue(ther.increase() == i + 1);
        }

        assertTrue(ther.increase() == ther.getHeight());
    }

    /**
     * Test of decrease method, of class Thermometer.
     */
    @Test
    public void testDecrease() {

        assertTrue(ther.decrease() == 0);

        for (int i = 0; i < ther.getHeight(); i++) {
            ther.increase();
        }

        for (int i = 0; i < ther.getHeight(); i++) {
            assertTrue(ther.decrease() == ther.getHeight() - i);
        }

    }

}
