package logic;

import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tasosxak
 * @author thanasis
 * @since 18/1/17
 * @version 1.0
 */
public class QuestionFileTest {

    QuestionFile file;

    @Before
    public void setUp() throws IOException {

        file = new QuestionFile("questions_gr_GR.txt");
    }

    /**
     * Test of getNextLine method, of class QuestionFile.
     */
    @Test
    public void testGetNextLine() {

        System.out.println("GetNextLine()");
        int numOfLines = file.getNumOfLines();

        for (int i = 0; i < numOfLines; i++) {

            assertTrue(file.getNextLine() != null);

        }

        assertTrue(file.getNextLine() != null);

    }

}
