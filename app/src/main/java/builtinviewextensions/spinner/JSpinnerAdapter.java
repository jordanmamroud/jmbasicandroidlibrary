package builtinviewextensions.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jordan.jmbasicandroidlibrary.R;
import utilities.ListHelper;

import java.util.ArrayList;

import functionalinterfaces.ITitled;
import utilities.StringHelper;

/**
 * Created by Jordan on 7/24/2017.
 */

public class JSpinnerAdapter extends ArrayAdapter {

    String label = null;
    ArrayList  allItems = new ArrayList<>();
    Context context ;

    boolean createRandomOptions ;
    int numOfItems = 5 ;
    int labelTextSize = 20 ;

    public JSpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.support_simple_spinner_dropdown_item  );
        this.context = context ;
    }

    public JSpinnerAdapter(@NonNull Context context, String label, ArrayList  items) {
        super(context,  R.layout.support_simple_spinner_dropdown_item );
        this.context = context;
        this.label = label ;
        addItems(items);
    }

    public JSpinnerAdapter createRandomOptions (ArrayList  itemsToChooseFrom , @Nullable Object presetItem){
        ArrayList optionsToChoose = ListHelper.getRandomItemsWithOnePreSet(numOfItems , itemsToChooseFrom , presetItem);
        addItems(optionsToChoose);
        return this ;
    }

    private void addItems(ArrayList items){
        allItems.addAll(items);
        addAll(items);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(label != null) {
            TextView labelTextView = (TextView) super.getView(position, convertView, parent);
            labelTextView.setText(   StringHelper.capitalize(label)  );
            labelTextView.setTextSize(labelTextSize);
            return   labelTextView ;
        }
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textViewInDropdown =(TextView) super.getDropDownView(position, convertView, parent) ;
        String title = StringHelper.capitalize(  getItemTitle(position )   );
        textViewInDropdown.setText(title);
        return textViewInDropdown   ;
    }

    public String getItemTitle(int pos){
        Object listItem = allItems.get(pos);
        String title =  null;
        if( listItem    instanceof String ) title = (String) listItem ;
        if(listItem instanceof ITitled) title =  (  (ITitled) listItem    ).getTitle() ;
        return title ;
    }

    public Object getItem(int position){    return  allItems.get(position)  ;   }

    public String getLabel() {
        return label;
    }

    public JSpinnerAdapter setLabel(String label) {
        this.label = label;
        return this ;
    }

    public void setLabelTextSize(int labelTextSize) {
        this.labelTextSize = labelTextSize;
    }


}
