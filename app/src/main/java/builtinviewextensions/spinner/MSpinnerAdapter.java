package builtinviewextensions.spinner;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.Utils.MHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jordan on 7/24/2017.
 */

public class MSpinnerAdapter extends ArrayAdapter {

    String label = null;
    ArrayList<String> allItems = new ArrayList<>();
    Context context ;

    int labelTextSize = 20 ;

    public MSpinnerAdapter(@NonNull Context context,  ArrayList<String> items) {
        super(context, R.layout.support_simple_spinner_dropdown_item  );
        this.context = context ;
        addItems(items);
    }

    public MSpinnerAdapter(@NonNull Context context, @LayoutRes int textViewResource  , String label,  ArrayList<String> items) {
        super(context, textViewResource);
        this.context = context;
        this.label = label ;
        addItems(items);
    }

     @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(label != null) {
            TextView textView = (TextView) super.getView(position, convertView, parent);
            textView.setText(MHelper.capitalize(label));
            textView.setTextSize(labelTextSize);
            return   textView ;
        }
        return super.getView(position, convertView, parent);
    }

    public void addItems(ArrayList<String> items){
         for(String item : items){
            allItems.add(item);
            add(item);
        }
    }

    public String getItem(int position){    return  allItems.get(position)  ;   }

    public String getLabel() {
        return label;
    }

    public MSpinnerAdapter setLabel(String label) {
        this.label = label;
        return this ;
    }

    public void setLabelTextSize(int labelTextSize) {
        this.labelTextSize = labelTextSize;
    }


}
