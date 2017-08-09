package builtinviewextensions.spinner;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by Jordan on 7/25/2017.
 */

public class JSpinner extends AppCompatSpinner {

    AdapterView.OnItemSelectedListener listener;

    public JSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        if (listener != null)
            listener.onItemSelected(this, getSelectedView(), position, 0);
    }

    public void setOnItemSelectedEvenIfUnchangedListener(
            AdapterView.OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public void cancel(){
         clearFocus();
         clearAnimation();
    }

}
