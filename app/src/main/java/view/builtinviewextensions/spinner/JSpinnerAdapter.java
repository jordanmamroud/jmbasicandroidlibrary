package  view.builtinviewextensions.spinner;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jordan.jmbasicandroidlibrary.R;

import functionalinterfaces.ITitled;
import utilities.ListHelper;

import java.util.ArrayList;
import java.util.Collections;

import utilities.StringHelper;
/**
 * Created by Jordan on 7/24/2017.
 */

public class JSpinnerAdapter extends ArrayAdapter {

    private String label = null;
    private ArrayList  allItems = new ArrayList<>();
    private int numOfItems = 5 ;
    private int labelTextSize = 20 ;

    public JSpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.support_simple_spinner_dropdown_item  );
    }

    public JSpinnerAdapter(@NonNull Context context, String label, ArrayList  items) {
        super(context,  R.layout.support_simple_spinner_dropdown_item );

        this.label = label ;
        addItems(items);
    }

    @Override
    public void clear() {
        if( allItems != null && allItems.size() > 0) {
            super.clear();
            allItems.clear();
        }
    }

    public JSpinnerAdapter createRandomOptions (ArrayList itemsToChooseFrom , @Nullable Object presetItem){
        ArrayList optionsToChoose = ListHelper.getRandomItemsFromList(itemsToChooseFrom , numOfItems  );
        optionsToChoose.add(presetItem);
        Collections.shuffle(optionsToChoose);
        addItems(optionsToChoose);
        return this ;
    }

    private void addItems(ArrayList items){
        allItems.addAll(items);
        addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(label != null && allItems.size() > 0 ) {
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

    public ArrayList getItems(){
        return allItems ;
    }

    public void setLabelTextSize(int labelTextSize) {
        this.labelTextSize = labelTextSize;
    }

    public void saveState(Bundle outstate){
        if( allItems.size() > 0 && allItems.get( 0 ) instanceof Parcelable )
            outstate.putParcelableArrayList("spinnerItems", allItems);
    }

    public void restoreState(Bundle savedInstance){
        if(savedInstance.getParcelableArrayList("spinnerItems") != null)
            addItems( savedInstance.getParcelableArrayList("spinnerItems"));
    }

}
