package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/**
 *
 * Αυτή η κλάση χειρίζεται το αρχείο με τις ερωτήσεις. Προσωρινά έχει έτοιμες
 * τις ερωτήσεις σε έναν πίνακα (αργότερα θα τις φορτώνει από το αρχείο).
 * Παρέχει μεθόδους για διάβασμα των γραμμών και χρησιμοποιείται από την Game
 * για να γεμίσει την λίστα με τις ερωτήσεις (questionlist).
 *
 * @author thanasis
 * @author tasosxak
 * @since 30/11/16
 * @version 1.0
 */
public class QuestionFile {

    private final String[] questionFile;
    private int lines;
    private int curLine;

    public QuestionFile(String filename) throws IOException {

        //InputStream input = getClass().getResourceAsStream("/questions/aa.txt");
        URL url = getClass().getResource("/questions/" + filename);

        compNumOfLines(url); //Υπολογισμός του πλήθους των γραμμών του αρχείου
        
        url = getClass().getResource("/questions/" + filename);
        
        //Σε περίπτωση που το αρχείο δεν υπάρχει στο συγκεκριμένο path 
        if (url == null) {
            throw new FileNotFoundException();
        }

        InputStream input = url.openStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "ISO8859_7"));
        questionFile = new String[lines];  

        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            this.questionFile[i++] = line;
            
        }

        reader.close();
        curLine = 0; //Αρχική θέση του δείκτη 

    }

    private void compNumOfLines(URL url) throws IOException {

        if (url == null) {
            throw new FileNotFoundException();
        }

        InputStream input = url.openStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "ISO8859_7"));
        while (reader.readLine() != null) {
            lines++;
        }
        
        reader.close();
    }

    /**
     *
     * @return Επιστρέφει κάθε φορά την επόμενη γραμμή του πίνακα όπου είναι
     * αποθηκευμένες οι ερωτήσεις (questionFile). Μόλις τελειώσουν οι γραμμές,
     * ξεκινάει πάλυ από την αρχή του πίνακα και απιστρέφει τις ερωτήσεις.
     */
    public String getNextLine() {
        if (curLine == questionFile.length) {
            curLine = 0;
        }

        return questionFile[curLine++];
    }

    /**
     *
     * @return Επιστρέφει το πλήθος των γραμμών που υπάρχουν στο αρχείο με τις
     * ερωτήσεις (questionFile).
     */
    public int getNumOfLines() {
        return questionFile.length;
    }

}
