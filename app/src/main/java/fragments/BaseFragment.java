package fragments;

import android.support.v4.app.Fragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jordan on 7/28/2017.
 */

public abstract class  BaseFragment extends Fragment  implements Observer{

    // not sure i want to force all extenders of base fragment to have new instance;


    @Override
    public void update(Observable o, Object arg) {}

}
