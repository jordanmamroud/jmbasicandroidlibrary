package functionalinterfaces;

/**
 * Created by Jordan on 8/17/2017.
 */

public interface RunInLoop {
    void nextLoop(int pos, Object object);
    void onComplete();
}
