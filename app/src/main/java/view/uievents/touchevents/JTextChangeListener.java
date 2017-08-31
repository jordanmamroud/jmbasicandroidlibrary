package  view.uievents.touchevents;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Jordan on 5/13/2017.
 */

public class JTextChangeListener implements TextWatcher {

    MOnTextChanged textChanged ;

    public JTextChangeListener(MOnTextChanged textChanged) {
        this.textChanged = textChanged ;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textChanged.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    public interface MOnTextChanged{
        public void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
