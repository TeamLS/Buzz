package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * Αυτή η κλάση χειρίζεται το σύνολο των παιχτών που συμμετέχουν στο παιχνίδι.
 * Περιέχει μία λίστα από αντικείμενα τύπου Player και μεθόδους που επιτρέπουν
 * την προσθήκη νέου παίχτη, την αλλαγή του σκορ του, καθώς και μεθόδους
 * πρόσβασης στα στοιχεία του όπως το όνομα, τα πλήκτρα και το σκορ.
 *
 * Επιπλέον η game υποστηρίζει παραπάνω από 2 παίχτες, με μια αλλαγή στην
 * μεταβλητή maxPlayers και την προσθήκη των πληκτρών στην λίστα keys.
 *
 * @author tasosxak
 * @author thanasis
 * @since 10/11/16
 * @version 1.0
 */
public class Game {

    private PlayerList playerlist;
    private QuestionList questionlist;
    private RoundList roundlist;
    private QuestionFile questionfile;
    private List<String> keys;
    private Question curQuestion;
    private Round curRound;
    private int playersLeftToAdd;
    private int maxPlayers;
    private int lastBonus;
    private int lastPlayerID;
    private boolean lastAnswerCorrect;
    private ArrayList<Integer> idsPlayed;
    private HighScores highScores;
    private String filelang;

    public Game() {

        idsPlayed = new ArrayList<>(); //Δημιουργία λίστας με τα id των παιχτών που θα παίξουν
        this.keys = new ArrayList<>(); //Δημιουργία λίστας με τα σύνολα κλειδιών (πλήκτρα) που θα έχει ο κάθε παίχτης
        highScores = new HighScores(); //Φόρτωση των παιχτών (serialization)
        maxPlayers = 2; //Μέγιστο πληθος επιτρεπτών παιχτών , εύκολα αλλάζει σε 3 , 4 ή και παραπάνω παίχτες....

        setLocale(new Locale("gr", "GR")); //By default η γλώσσα είναι στα ελληνικά και άρα φορτώνει το ελληνικό αρχείο ερωτήσεων 

    }

    public void setLocale(Locale locale) {
        this.filelang = locale.getLanguage() + "_" + locale.getCountry();
    }

    public String getLocale() {

        return this.filelang;
    }

    public int getLastBonus() {
        return this.lastBonus;
    }

    public boolean isLastCorrect() {
        return this.lastAnswerCorrect;
    }

    public int getNumOfPlayers() {
        return this.playerlist.getNumOfPlayers();
    }

    private void loadQuestions(int nQuestions) {

        for (int i = 0; i < nQuestions; i++) {

            String[] currentQuestionLine = this.questionfile.getNextLine().split("-"); //Διαχωρίζει την γραμμή του αρχείου με βάση το "-"  
            switch (currentQuestionLine[0]) { //Ελέγχει το πρώτο στοιχείο του πίνακα  το οποίο υποδηλώνει τι είδους ερώτηση είναι
                case "TEXT":
                    //Δημιουργία αντικειμένου Question και προσθήκη στην QuestionList
                    this.questionlist.addQuestion(new Question(
                            currentQuestionLine[1],
                            currentQuestionLine[2],
                            currentQuestionLine[3],
                            new String[]{currentQuestionLine[4], currentQuestionLine[5], currentQuestionLine[6]}));
                    break;
                case "IMAGE":
                    //Δημιουργία αντικειμένου ImageQuestion και προσθήκη στην QuestionList
                    this.questionlist.addQuestion(new ImageQuestion(
                            currentQuestionLine[1],
                            currentQuestionLine[2],
                            currentQuestionLine[3],
                            new String[]{currentQuestionLine[4], currentQuestionLine[5], currentQuestionLine[6]}, currentQuestionLine[7]));
                    break;
            }

        }

    }

    /**
     *
     * Αυτη η μέθοδος στήνει όλο το παιχνίδι. Αρχικοποιεί τα κατάλληλα πεδία
     * φορτώνει τις ερωτήσεις και θέτει τα πλήκτρα των παιχτών. Πρέπει να
     * καλείται πάντα πριν αρχίσει ένα παιχνίδι.
     *
     * @throws java.io.IOException
     */
    public void initialize() throws IOException {

        this.playerlist = new PlayerList(); //Δημιουργία της λίστας των παιχτών
        this.questionlist = new QuestionList(); //Δημιουργία της λίστας ερωτήσεων

        this.questionfile = new QuestionFile("questions_" + filelang + ".txt");  //Δημιουργία ενός αντικειμένου QuestionFile το οποίο φορτώνει το κατάλληλο αρχείο ερωτήσεων

        this.loadQuestions(20); //Αρχικά φτιάχνει 20 αντικείμενα ερωτήσεων από όλες τις ερωτήσεις που φορτώθηκαν από το αρχείο

        this.keys.clear(); //Καθαρίζει ότι πλήκτρο απέμεινε, έχει νόημα όταν παίζεται  πάνω από ένα παιχνίδι

        //Προσθήκη πληκτρών 
        this.keys.add("asdf");
        this.keys.add("hjkl");
        // this.keys.add("zxcv");
        //  this.keys.add("uiop");

        this.lastPlayerID = 1;    //Αρχικοποιείται η μεταβλητή που υποδηλώνει ποιος παίχτης έπαιξε τελευταίος (είναι χρήσιμο για κάποια rounds)
        playersLeftToAdd = maxPlayers; // Αρχικοποιείται η μεταβλητή που υποδηλώνει πόσοι παίχτες επιτρέπονται ακόμα 

    }

    /**
     * Η start() πρέπει να καλείται όταν έχει ήδη οριστεί το πλήθος των παιχτών
     * που θα παίξουν (δηλαδή μετά από την initialize()) και με βάση αυτό
     * φορτώνει τους κατάλληλους γύρους.
     *
     */
    public void start() {
        this.roundlist = new RoundList(this.getNumOfPlayers());
    }

    /**
     * Αυτή η μέθοδος προσθέτει έναν παίχτη στο παιχνίδι αφού πρώτα ελέγξει ότι
     * μπορεί να προστεθεί νέος παίχτης, ότι δεν υπάρχει ήδη άλλος παίχτης με το
     * ίδιο όνομα και ότι το όνομα που δόθηκε ως παράμετρος (name) περιέχει έστω
     * έναν αλφαριθμιτικό χαρακτήρα. .
     *
     * @param name Το όνομα του παίχτη που θα προστεθεί στο παιχνίδι.
     * @return Επιστρέφει τα πλήκτρα του παίχτη ως συμβολοσειρά (πχ "asdf") αν η
     * εισαγωγή του παίχτη ολοκληρώθηκε με επιτυχία, αλλιώς null.
     */
    public String addPlayer(String name) {

        //Ελέγχει αν επιτρέπονται νέοι παίχτες , αν το όνομα περιέχει τουλάχιστον έναν χαρακτήρα, αν υπάρχουν διαθέσιμα πλήκτρα και αν δεν υπάρχει άλλος παίχτης με το ίδιο όνομα
        if (this.playersLeftToAdd > 0 && name.matches(".*([a-zA-Z]|[α-ωΑ-Ω]).*") && !(this.keys.isEmpty()) && this.playerlist.addPlayer(name.trim(), this.keys.get(0))) {
            this.playersLeftToAdd--; //Μειώνεται ο αριθμός των επιτρεπτών παιχτών
            return this.keys.remove(0); // Αφαιρούνται τα πλήκτρα από τη λίστα διαθέσιμων πλήκτρων και επιστρέφονται
        } else {
            return null;
        }

    }

    /**
     *
     * Αυτή η μέθοδος θέτει τον τρέχοντα γύρο του παιχνιδιού παίρνοντας μέσω της
     * roundlist τον επόμενο διαθέσιμο γύρο και επιστρέφει true. Αν δεν υπάρχει
     * διαθέσιμος γύρος επιστρέφει false.
     *
     * @return Επιστρέφει true αν υπάρχει και άλλος γύρος για να εκτελεστεί,
     * αλλιώς false.
     */
    public boolean canContinue() {

        if (this.roundlist.hasNext()) {
            this.curRound = roundlist.getNextRound();
            return true;
        }

        return false;
    }

    public Round getCurRound() {
        return this.curRound;
    }

    /**
     *
     * Αυτή η μέθοδος θέτει την τρέχουσα ερώτηση του παιχνιδιού, και επιστρέφει
     * το αντικείμενο το οποίο αντιστοιχεί σε αυτήν. Επίσης αναλαμβάνει να μην
     * αδειάσει η λίστα με τις ερωτήσεις (questionlist) φορτώνοντας 15 νέες
     * ερωτήσεις κάθε φορά που το πλήθος των ερωτήσεων που υπάρχουν στην λίστα
     * είναι μικρότερο του 5.
     *
     * @return Επιστρέφει ένα αντίγραφο του αντικειμένου Question που
     * αντιστοιχεί στην τρέχουσα ερώτηση του παιχνιδιού.
     */
    public Question getCurQuestion() {

        //Γέμισμα της questionlist
        if (this.questionlist.numOfQuestions() < 5) {
            this.loadQuestions(15);
        }

        this.curQuestion = this.questionlist.nextQuestion();

        if (curQuestion instanceof ImageQuestion) {
            return new ImageQuestion((ImageQuestion) this.curQuestion); // Καλείται ο copy constructor της ImageQuestion.
        } else {// if (curQuestion instanceof ImageQuestion)* 
            return new Question(this.curQuestion); // Καλείται ο copy constructor της Question.            
        }

    }

    /**
     *
     * @return Επιστρέφει true αν ο τρέχων γύρος δεν έχει τελειώσει, αλλιώς
     * false.
     */
    public boolean inSameRound() {
        return this.curRound.getExecuteTimes() > 0;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο το
     * οποίο θέλουμε να ελέγξουμε αν χρησιμοποιείται στο παιχνίδι.
     * @return Επιστρέφει true αν το πλήκτρο (key) αντιστοιχεί σε κάποιον
     * παίχτη, αλλιώς false.
     */
    public boolean isValidKey(String key) {
        return playerlist.whoseKeyIs(key) > 0;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο που
     * θέλουμε να δούμε σε ποιόν παίχτη αντιστοιχεί.
     * @return Επιστρέφει το id του παίχτη στον οποίο αντιστοιχεί το πλήκτρο
     * (key) αν υπάρχει, αλλιώς επιστρέφει -1.
     */
    public int getIdOfPlayerByKey(String key) {
        return playerlist.whoseKeyIs(key);
    }

    /**
     *
     * @param answer Συμβολοσειρά με έναν χαρακτήρα ο οποίος αντιστοιχεί στο
     * πλήκτρο του παίχτη που απάντησε.
     * @param randoms Ο πίνακας που περιέχει τη σειρά που εμφανίστηκαν οι
     * ερωτήσεις.
     * @return Επιστρέφει true αν η απάντηση του παίχτη ήταν σωστή, αλλιώς
     * false.
     */
    public boolean isCorrectByAnswer(String answer, List<Integer> randoms) {

        return curQuestion.isCorrect(curQuestion.getAllAnswers()[randoms.get(playerlist.getKeyIdOfPlayer(answer, getIdOfPlayerByKey(answer)))]);
    }

    /**
     *
     * @param answer Συμβολοσειρά ενός χαρακτήρα ο οποίος αντιστοιχεί στο
     * πλήκτρο ενός παίχτη.
     * @return Επιστρέφει το όνομα του παίχτη στον οποίο ανήκει το πλήκτρο
     * (answer).
     */
    public String getNameOfPlayerByAnswer(String answer) {
        return playerlist.getNameOfPlayer(playerlist.whoseKeyIs(answer));

    }

    /**
     *
     * Αυτή η μέθοδος αναλαμβάνει να θέσει τα μπόνους (ή τις ποινές) που πρέπει
     * να πάρει ο πάιχτης που απάντησε ανάλογα με τον τύπο του γύρου και με το
     * αν η απάντηση του παίχτη ήταν σωστή ή όχι.
     *
     * @param answer Συμβολοσειρά ενός χαρακτήρα που αντιστοιχή στο πλήκτρο του
     * παίχτη που απάντησε.
     * @param randoms Ο πίνακας που περιέχει τη σειρά που εμφανίστηκαν οι
     * ερωτήσεις.
     * @return Επιστρέφει true αν υπήρχε παίχτης με πλήκτρο answer και
     * εκτελέστηκε ο γύρος, αλλιώς false.
     */
    public boolean executeRound(String answer, List<Integer> randoms) {

        int player_id;

        // Εύρεση παίχτη που απάντησε.
        player_id = playerlist.whoseKeyIs(answer);

        if (player_id > 0) {

            // Έλεγχος αν η απάντηση που δόθηκε είναι η σωστή ή όχι.
            boolean correct = curQuestion.isCorrect(curQuestion.getAllAnswers()[randoms.get(playerlist.getKeyIdOfPlayer(answer, player_id))]);

            int curRoundBonus = curRound.getBonus();

            if (curRound instanceof CorrectAnswerRound) {
                if (correct) {
                    this.win(player_id, curRoundBonus);
                } else {
                    this.lose(player_id, 0);
                }

            } else if (curRound instanceof BetRound) {
                if (correct) {
                    this.win(player_id, curRoundBonus);
                } else {
                    this.lose(player_id, -curRoundBonus);
                }

            } else if (curRound instanceof CountDownRound) {
                if (correct) {
                    this.win(player_id, curRoundBonus);
                } else {
                    this.lose(player_id, 0);
                }

            } else if (curRound instanceof FastAnswerRound) {
                if (correct) {
                    curRoundBonus = ((FastAnswerRound) curRound).getNextBonus();
                    this.win(player_id, curRoundBonus);
                } else {
                    this.lose(player_id, 0);
                }

            } else if (curRound instanceof ThermometerRound) {

                if (correct) {

                    ((ThermometerRound) curRound).addWin(player_id);
                    if (((ThermometerRound) curRound).hasPlayerWon(player_id)) {
                        this.win(player_id, curRoundBonus);
                    } else {
                        this.win(player_id, 0);
                    }

                } else {
                    this.lose(player_id, 0);
                }

            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private void lose(int id, int bonus) {
        this.lastAnswerCorrect = false;
        this.lastBonus = bonus;
        if (bonus != 0) {
            this.playerlist.addPointsToPlayer(bonus, id);
        }

    }

    private void win(int id, int bonus) {
        this.lastAnswerCorrect = true;
        this.lastBonus = bonus;
        if (bonus != 0) {
            this.playerlist.addPointsToPlayer(bonus, id);
        }

    }

    /**
     *
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2 κτλπ) του
     * οποίου το σκορ ζητείται.
     * @return Επιστρέφει το σκορ του παίκτη αν ο παίκτης υπάρχει, αλλιώς null.
     */
    public Integer getScoreByPlayerId(int player_id) {
        return playerlist.getScoreOfPlayer(player_id);

    }

    /**
     *
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2 κτλπ) του
     * οποίου το όνομα ζητείται.
     * @return Επιστρέφει το όνομα του παίκτη αν ο παίκτης υπάρχει, αλλιώς null
     */
    public String getNameByPlayerId(int player_id) {
        return this.playerlist.getNameOfPlayer(player_id);
    }

    public void exit() {

        System.exit(0);
    }

    public int getPlayersLeftToAdd() {
        return playersLeftToAdd;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     *
     * @param key Συμβολοσειρά με έναν χαρακτήρα ο οποίος είναι το πλήκτρο που
     * θέλουμε να δούμε αν ανήκει στον παίκτη με id ίσο με player_id.
     * @param player_id Το id του παίκτη (Ποιος παίκτης είναι: 1 ή 2 κτλπ) για
     * τον οποίο ζητούμε το id του πλήκτρου.
     * @return Επιστρέφει true αν το πλήκτρο (key) ανήκει στον παίκτη με id ίσο
     * με player_id.
     */
    public boolean isKeyOfPlayer(String key, int player_id) {
        return playerlist.isKeyOfPlayer(key, player_id);
    }

    /**
     *
     * @return Επιστρέφει το όνομα και το σκορ από κάθε παίχτη στην μορφή
     * "Παίχτης1: Σκορ1 Παίχτης2: Σκορ2 κτλπ"
     */
    public String getScores() {
        return playerlist.toString();
    }

    /**
     * Η getNextID() χρησιμοποιείται σε γυρούς οι οποίοι απαιτούν κάθε παίχτης
     * να παίζει μια φορά και με τη σειρά. Αρχικά δείχνει στον πρώτο παίχτη και
     * κάθε φορά που καλείται με παράμετρο true δείχνει στον επόμενο παίχτη
     * μέχρι να δείξει και στον τελευταίο, ενώ από εκείνο το σημείο και μετά
     * δείχνει ξανά στον πρώτο παίχτη.
     *
     * @param change Boolean ο οποίος αν είναι true δείχνει στον επόμενο παίχτη,
     * διαφορετικά όχι.
     * @return Το id του παίχτη που έχει σειρά
     */
    public int getNextID(boolean change) {

        if (change) {
            if (lastPlayerID == this.getNumOfPlayers()) {
                lastPlayerID = 1;
                return lastPlayerID;
            }

            return ++lastPlayerID;

        } else {
            return this.lastPlayerID;
        }

    }

    /**
     * Η canPlay() χρησιμοποιείται σε γυρούς οι οποίοι απαιτούν κάθε παίχτης να
     * παίζει μια φορά όχι απαραίτητα με τη σειρά.
     *
     * @param id Το ID του παίχτη που θέλουμε να δούμε αν μπορεί να παίξει
     * @return Επιστρέφει true αν ο παίχτης μπορεί να παίξει(δηλαδή δεν έχει
     * παίξει μέχρι στιγμής) , διαφορετικά false
     */
    public boolean canPlay(int id) {
        if (id > 0 && id <= getNumOfPlayers()) {
            if (idsPlayed.contains(id)) {
                return false;
            }
            idsPlayed.add(id);
            return true;
        }
        return false;
    }

    /**
     *
     * @return Επιστρέφει true αν έχουν παίξει όλοι οι παίχτες , διαφορετικά
     * false
     */
    public boolean allHavePlayed() {
        if (idsPlayed.size() == getNumOfPlayers()) {
            idsPlayed.clear();
            return true;
        }
        return false;
    }

    public String getWinnersName() {

        return getNameByPlayerId(playerlist.getWinnersId());

    }

    /**
     * Η gameOver() καλείτε στο τέλος του παιχνιδιού και αποθηκεύει τους παίχτες
     * με τα σκορ τους.
     *
     * @throws java.io.IOException
     */
    public void gameOver() throws IOException {
        highScores.writeFile(playerlist); //Αποθήκευση των παιχτών με τα σκορ τους (serialization)
    }

    /**
     * H getNamesForWins χρησιμοποιείται όταν θέλουμε να πάρουμε τα όνοματα των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά με βάση τις νίκες τους.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με τις
     * περισσότερες νικές (num = 5)
     * @return Πίνακα με τα ονόματα των παιχτών ταξινομημένα με βάση τις νίκες
     * τους.
     */
    public String[] getNamesForWins(int num) {
        return highScores.getNamesForWins(num);
    }

    /**
     * H getTopWins χρησιμοποιείται όταν θέλουμε να πάρουμε τις νίκες των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με τις
     * περισσότερες νικές (num = 5)
     * @return Ταξινομημένο πίνακα με τις νίκες των παιχτών.
     */
    public Integer[] getTopWins(int num) {
        return highScores.getTopWins(num);
    }

    /**
     * H getNamesForHighScores χρησιμοποιείται όταν θέλουμε να πάρουμε τα
     * όνοματα των παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά
     * με βάση το σκορ τους .
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με το υψηλότερο
     * σκορ (num = 5).
     * @return Πίνακα με τα ονόματα των παιχτών ταξινομημένα με βάση το σκορ
     * τους.
     */
    public String[] getNamesForHighScores(int num) {
        return highScores.getNamesForHighScores(num);
    }

    /**
     * H getHighScores χρησιμοποιείται όταν θέλουμε να πάρουμε τα σκορ των
     * παιχτών από το αρχείο των σκορ κατά φθίνουσα σειρά.
     *
     * @param num Το πλήθος των παιχτών . Π.χ. τους 5 παίχτες με το υψηλότερο
     * σκορ (num = 5)
     * @return Ταξινομημένο πίνακα με τα σκορ των παιχτών.
     */
    public Integer[] getHighScores(int num) {
        return highScores.getHighScores(num);
    }

    /**
     * @return Επιστρέφει έναν ταξινομημένο κατά φθήνουσα σειρά πίνακα με τα
     * σκορ των παιχτών από το παρόν παιχνίδι
     */
    public int[] getScoreTableScores() {
        return playerlist.getScoreTableScores();
    }

    /**
     * @return Επιστρέφει έναν ταξινομημένο κατά φθήνουσα σειρά (ως προς τα σκορ
     * των παιχτών) πίνακα με τα ονόματα των παιχτών από το παρόν παιχνίδι.
     */
    public String[] getScoreTableNames() {
        return playerlist.getScoreTableNames();
    }

    /**
     *
     * @return Επιστρέφει true αν το παιχνίδι βγήκε ισόπαλο αλλιώς false
     */
    public boolean tie() {

        int[] scores = getScoreTableScores(); //Λαμβάνει τα σκορ των παιχτών που έπαιξαν
        return scores[0] == scores[1];

    }

    public int getNumOfRounds() {
        return roundlist.getNumOfRounds();
    }

}
