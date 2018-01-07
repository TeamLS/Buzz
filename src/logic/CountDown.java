package logic;

import java.util.ArrayList;

/**
 * Αυτή η κλάση υλοποιεί έναν αντίστροφο μετρητή. Χρησιμοποιείται σε γύρους που
 * χρειάζονται μετρητή (ρολόι). Δίνει τη δυνατότητα να προσθέσεις
 * CountDownListener. Κάθε φορά που περνάει ένα δευτερόλεπτο καλείτε η tick()
 * και μόλις μηδενίσει το αντικείμενο CountDown καλείτε η finished().
 *
 * @author tasosxak
 * @author Thanasis
 * @since 1/1/2017
 * @version 1.0
 */
public class CountDown implements Runnable {

    private int currentMilliSecond;
    private int startSeconds;
    private ArrayList<CountDownListener> listeners;

    public CountDown(int seconds) {
        listeners = new ArrayList<>();
        startSeconds = seconds;
    }

    @Override
    public void run() {

        int startMilliSeconds = startSeconds * 1000; //Μετατροπή σε milliSeconds
        for (int i = startMilliSeconds; i >= 0; i--) {

            try {

                currentMilliSecond = i;
                Thread.sleep(1); //Αναμονή για  κάθε millisecond 

                if (currentMilliSecond % 1000 == 0) {
                    for (final CountDownListener listener : listeners) {
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                listener.tick(); //Εκτελείτε η μέθοδος του listener tick() 
                            }
                        }).start();
                    }
                }

            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        for (CountDownListener listener : listeners) {
            listener.finished(); //΄Μόλις ο μετρητής μηδενίσει , καλείται η μέθοδος του listener finished()
        }

    }

    /**
     * Η addCountDownListener δίνει τη δυνατότητα να προσθέσεις έναν ΄listener
     * στον μετρητή σου.
     *
     * @param listener Το ανώνυμο αντικείμενο CountDownListener που προσθέτεις
     * στον μετρητή
     */
    public void addCountDownListener(CountDownListener listener) {
        listeners.add(listener);
    }

    /**
     * Η removeCountDownListener δίνει τη δυνατότητα να αφαιρέσεις έναν
     * ΄listener από τον μετρητή σου.
     *
     * @param listener Το ανώνυμο αντικείμενο CountDownListener που αφαιρείς από
     * τον μετρητή
     */
    public void removeCountDownListener(CountDownListener listener) {
        listeners.remove(listener);
    }

    public int getSeconds() {
        return (int) Math.ceil(currentMilliSecond / 1000.0);
    }

    public int getMilliSeconds() {
        return currentMilliSecond;
    }

}
