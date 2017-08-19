package utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Jordan on 8/3/2017.
 */

public class ListHelper {

    public static ArrayList getRandomItemsFromList(ArrayList<String> list , int numOfItemsToGet){
        ArrayList<String> items = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();

        while (items.size() <  numOfItemsToGet){
            int rand = MathHelper.getRandomNum(list.size() );
            nums.add(rand);
            if(nums.indexOf(rand) == -1){
                items.add(list.get(rand));
            }
        }
        return items;
    }

    public static ArrayList getRandomItemsWithOnePreSet(int numOfItems, ArrayList listToGetFrom , Object preSetListItem){
        ArrayList  items = new ArrayList<>();
        items.add(preSetListItem);
        while ( items.size() < numOfItems  ){
            int rand = MathHelper.getRandomNum(    listToGetFrom.size()    );
            Object itemToAdd = listToGetFrom.get(rand   ) ;
            items.add(  itemToAdd );
        }
        Collections.shuffle(items);
        return items ;
    }


}
