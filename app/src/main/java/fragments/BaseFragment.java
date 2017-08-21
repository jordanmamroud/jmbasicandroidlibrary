package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import utilities.ViewHelper;

/**
 * Created by Jordan on 7/28/2017.
 */

public abstract class  BaseFragment extends Fragment  implements Observer{

    // not sure i want to force all extenders of base fragment to have new instance;


    @Override
    public void update(Observable o, Object arg) {}

    public void showMessage(String msg, int duration){
        Toast.makeText(getContext(), msg , duration).show();
    }

    public void showMessage(String msg, int duration, int posGravity){
        ViewHelper.showToastAtCustomPosition(getContext(), msg, duration, posGravity);
    }
}
