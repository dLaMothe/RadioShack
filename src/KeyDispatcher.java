import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;


public class KeyDispatcher implements KeyEventDispatcher {
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_TYPED) {
            System.out.println( "typed" + e.getKeyCode() );
        }
        //Allow the event to be redispatched
        return false;
    }
}
