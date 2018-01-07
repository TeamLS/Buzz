package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Αυτή η κλάση διαχειρίζεται την διεπαφή με τον χρήστη. Περιέχει μεθόδους για
 * την εμφάνιση μηνυμάτων και ερωτήσεων και για την ανάγνωση από το χρήστη.
 *
 * @authors tasosxak
 * @authors thanasis
 * @since 12/11/16
 * @version 0.2
 */
public final class Parser {

    private static final Scanner SCAN = new Scanner(System.in, "ISO-8859-7");

    private Parser() {

    }

    /**
     *
     * @param message Το μύνημα που θέλουμε να εμφανιστεί.
     */
    public static void show(String message) {
        System.out.println(message);
    }

    /**
     *
     * @param number Ο ακαίρεος αριθμός που θέλουμε να εμφανιστεί.
     */
    public static void show(Integer number) {
        System.out.println(number);
    }

    /**
     *
     * @return Επιστρέφει το String που δέχτηκε από το χρήστη.
     */
    public static String read() {
        return Parser.SCAN.nextLine();
    }

    /**
     *
     * Η μέθοδος askQuestion εμφανίζει τα στοιχεία μίας ερώτησης την οποία
     * πάιρνει σαν παράμετρο (question). Αρχικά εμφανίζει την κατηγορία στην
     * οποία ανήκει η ερώτηση, στην συνέχεια την εκφώνηση της ερώτησης και τέλος
     * τις πιθανές απαντήσεις.
     *
     * @param question Αντικείμενο της Question που περιέχει τα στοιχεία της
     * ερώτησης προς εμφάνιση.
     * @return Επιστρέφει εναν πίνακα που δηλώνει τη σειρά με την οποία
     * εμφανίστηκαν οι πιθανές απαντήσεις (πχ αν οι πιθανές απαντήσεις μετά το
     * ανακάτεμα εμφανίστηκαν όλες ανάποδα, ο πίνακας θα έχει τις τιμές
     * 3,2,1,0).
     */
    public static ArrayList<Integer> askQuestion(Question question) {

        ArrayList<Integer> randAnswerIds = new ArrayList<>();

        // Δημιουργία πίνακα ακεραίων με τους αριθμούς 0-3 και ανακάτεμα αυτών.
        for (int i = 0; i < 4; i++) {
            randAnswerIds.add(i);
        }
        long seed = System.nanoTime();
        Collections.shuffle(randAnswerIds, new Random(seed));

        //Εμφάνιση της κατηγορίας, της ερώτησης και των πιθανών απαντήσεων.
        System.out.println("Κατηγορία: " + question.getCategory());
        System.out.println(question.getQuestion());
        System.out.println("1. " + question.getAllAnswers()[randAnswerIds.get(0)]);
        System.out.println("2. " + question.getAllAnswers()[randAnswerIds.get(1)]);
        System.out.println("3. " + question.getAllAnswers()[randAnswerIds.get(2)]);
        System.out.println("4. " + question.getAllAnswers()[randAnswerIds.get(3)]);

        return randAnswerIds;

    }

    /**
     * Η μέθοδος readWithOptions δέχεται μία λίστα από επιτρεπόμενες απαντήσεις,
     * ζητάει από το χρήστη μία απάντηση και σε περίπτωση που η απάντηση δεν
     * είναι από τις επιτρεπόμενες εμφανίζει στον χρήστη ποιες είναι οι
     * απαντήσεις που μπορεί να δώσει και στη συνέχεια ξαναζητάει απάντηση από
     * το χρήστη, μέχρι να δωθεί μία επιτρεπόμενη απάντηση, την οποία τελικά
     * επιστρέφει η μέθοδος.
     *
     * @param allowedAnswers Η λίστα με τις απαντήσεις που θεωρούνται αποδεκτές
     * από το χρήστη.
     * @return Επιστρέφει το τελικό String που δέχτηκε από το χρήστη.
     */
    public static String readWithOptions(List<String> allowedAnswers) {

        String ans;
        StringBuilder errorMsg; // Μήνυμα που εμφανίζεται σε περίπτωση που ο χρήστης εισάγει κάτι διαφορετικό από τις επιλογές (options).
        errorMsg = new StringBuilder("Επιτρέπονται μόνο οι τιμές ");

        for (String allowed : allowedAnswers) {
            errorMsg.append(allowed).append(", ");
        }

        // Μετατροπή του μηνύματος από πχ  '...750,1000, ' σε '...750,1000.'.
        errorMsg.setLength(errorMsg.length() - 1);
        errorMsg.setCharAt(errorMsg.length() - 1, '.');

        // Ανάγνωση από το χρήστη της επιλογής του για ποντάρισμα και έλεγχος αν αυτή είναι δεκτή.
        ans = Parser.read();

        while (!allowedAnswers.contains(ans)) {
            Parser.show(errorMsg.toString());
            ans = Parser.read();
        }

        return ans;
    }

    /**
     *
     * Αυτή η μέθοδος μετατρέπει μία συμβολοσειρά σε ArrayList που περιέχει
     * ξεχωριστά τους χαρακτήρες της συμβολοσειράς ως συμβολοσειρές.
     *
     * @param myString Η συμβολοσειρά που θέλουμε να μετατραπεί σε ArrayList.
     * @return Επιστρέφει μία ArrayList που περιέχει ξεχωριστά τους χαρακτήρες
     * της myString.
     */
    public static ArrayList<String> stringToArrayList(String myString) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < myString.length(); i++) {
            list.add(Character.toString(myString.charAt(i)));
        }
        return list;
    }

}
