package logic;

import java.util.EventListener;

/**
 *
 * Η κλάση αυτή υλοποιεί έναν listener για κλάσεις που υλοποιούν αντίστροφους
 * μετρητές. Χρησιμοποιείτε στην CountDown.
 *
 * @author Thanasis
 * @author tasosxak
 * @since 10/1/2017
 * @version 1.0
 */
public interface CountDownListener extends EventListener {

    public void tick();

    public void finished();

}
