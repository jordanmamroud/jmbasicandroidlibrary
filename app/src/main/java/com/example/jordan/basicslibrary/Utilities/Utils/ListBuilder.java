package com.example.jordan.basicslibrary.Utilities.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import functionalinterfaces.ITitled;

/**
 * Created by Jordan on 8/3/2017.
 */

public class ListBuilder {

    public static ArrayList getRandomItemsWithOnePreSet(int numOfItems, ArrayList listToGetFrom , Object preSetListItem){
        ArrayList  items = new ArrayList<>();
        items.add(preSetListItem);
        while ( items.size() < numOfItems  ){
            int rand = MHelper.getRandomNum(    listToGetFrom.size()    );
            Object itemToAdd = listToGetFrom.get(rand   ) ;
            items.add(  itemToAdd );
        }
        Collections.shuffle(items);
        return items ;
    }


}
