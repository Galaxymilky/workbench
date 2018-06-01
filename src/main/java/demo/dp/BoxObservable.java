package demo.dp;

import java.util.Observable;

/**
 * Created by dynamicniu on 2018/5/30.
 */
public class BoxObservable extends Observable {
    public void notifyObservers(Object b) {
        // Otherwise it won't propagate changes
        setChanged();
        super.notifyObservers(b);
    }
}


