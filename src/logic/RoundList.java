package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;

/**
 *
 * Αυτή η κλάση χειρίζεται το σύνολο των γύρων. Περιέχει μία λίστα από
 * αντικείμενα τύπου Round και μεθόδους ώστα να φορτώνει με τυχαίο τρόπο όσους
 * γύρους της ζητηθεί και στην συνέχεια να τους εκτελεί.
 *
 * @author thanasis
 * @author tasosxak
 * @since 9/11/16
 * @version 1.0
 */
public class RoundList {

    private List<String> roundTypes;
    private List<Round> rounds;
    private int numRounds;
    private int numOfPlayers;
    private int currentRoundId = 0;
    private final int numRoundTypes;

    /**
     *
     * Ο κενός κατασκευαστής
     */
    public RoundList(int numOfPlayers) {

        this(6, numOfPlayers);
    }

    /**
     *
     * Αυτός ο κατασκευαστής ορίζει τους τύπους των γύρων του παιχνιδιού και
     * γεμίζει τη λίστα με τους γύρους.
     *
     * @param numRounds Το πλήθος των γύρων που θέλουμε σε ένα παιχνίδι
     * @param numOfPlayers
     */
    public RoundList(int numRounds, int numOfPlayers) {

        if (numOfPlayers <= 0 || numRounds <= 0) {
            numRounds = 6;
            numOfPlayers = 1;
        }
        
        rounds = new ArrayList<>();

        roundTypes = new ArrayList<>();
        roundTypes.add("CorrectAnswerRound");
        roundTypes.add("CountDownRound");
        roundTypes.add("BetRound");

        if (numOfPlayers > 1) {
            roundTypes.add("FastAnswerRound");
            roundTypes.add("ThermometerRound");
        }

        numRoundTypes = roundTypes.size();

        this.numRounds = numRounds;
        this.numOfPlayers = numOfPlayers;

        loadRounds();

    }

    /**
     *
     * Αυτή η μέθοδος φορτώνει όσους γύρους απαιτούνται για το παιχνίδι
     * (numRounds). Εξασφαλίζει να μην ξαναεμφανιστεί ο ίδιος τύπος γύρου αν δεν
     * έχουν παιχτεί όλοι οι τύποι γύρων.
     */
    private void loadRounds() {

        List<Integer> randomRoundTypeIds = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int roundsLeft = numRounds;
        long seed;

        /* Δημιουργία του πίνακα randomRoundTypeIds που έχει τυχαίους αριθμόυς (id)
         που αντιστοιχούν σε τύπους γύρων. Το μέγεθός του είναι όσο και το πλήθος
         των γύρων που έχει δοθεί στον κατασκευαστή της RoundList (numRounds).
         Ο πίνακας αυτός δημιουργείται ώστε να μην εμφανίζεται ένας τύπος γύρου
         δύο φορές χωρίς να έχουν εμφανιστεί όλοι οι υπόλιποι τύποι γύρων.  */
        while (roundsLeft != 0) {
            for (int i = 0; i < numRoundTypes; i++) {
                temp.add(i);
            }
            seed = System.nanoTime();
            Collections.shuffle(temp, new Random(seed));
            int limit = Math.min(roundsLeft, numRoundTypes);
            for (int i = 0; i < limit; i++) {
                randomRoundTypeIds.add(temp.get(i));
            }
            temp.clear();
            roundsLeft -= limit;
        }

        // Φόρτωση των γύρων με τη σειρά που ορίζεται από τον πίνακα randomRoundTypeIds
        for (Integer rand : randomRoundTypeIds) {
            loadOneRound(rand);
        }

    }

    /**
     *
     * Αυτός ο κατασκευαστής ορίζει τους τύπους των γύρων του παιχνιδιού και
     * γεμίζει τη λίστα με τους γύρους.
     *
     * @param myRoundTypeId Το id του τύπου γύρου που θέλουμε να φορτώσουμε στην
     * λίστα (πχ 0 -> CorrectAnswerRound, 1 -> BetRound).
     */
    private void loadOneRound(int myRoundTypeId) {

        String myRoundType = roundTypes.get(myRoundTypeId);

        switch (myRoundType) {

            case "CorrectAnswerRound":
                CorrectAnswerRound caRound = new CorrectAnswerRound();
                rounds.add(caRound);
                break;
            case "BetRound":
                List<String> bet_options = new ArrayList<>();
                bet_options.add("250");
                bet_options.add("500");
                bet_options.add("750");
                bet_options.add("1000");
                BetRound bRound = new BetRound(bet_options);
                rounds.add(bRound);
                break;
            case "CountDownRound":
                CountDownRound cdRound = new CountDownRound();
                rounds.add(cdRound);
                break;
            case "FastAnswerRound":
                List<Integer> bonuses = new ArrayList<>();
                bonuses.add(1000);
                bonuses.add(500);
                FastAnswerRound faRound = new FastAnswerRound(bonuses);
                rounds.add(faRound);
                break;
            case "ThermometerRound":
                ThermometerRound thRound = new ThermometerRound(numOfPlayers);
                rounds.add(thRound);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @return Επιστρέφει true αν υπάρχει και άλλος γύρος για να παιχτεί, αλλιώς
     * false.
     */
    public boolean hasNext() {
        return currentRoundId < numRounds;
    }

    /**
     *
     * @return Επιστρέφει το αντικείμενο Round που αντιστοιχεί στον επόμενο
     * γύρο.
     */
    public Round getNextRound() {
        if (this.hasNext()) {
            return rounds.get(currentRoundId++);
        } else {
            return null;
        }
    }

    public int getNumOfRounds() {
        return numRounds;
    }

}
