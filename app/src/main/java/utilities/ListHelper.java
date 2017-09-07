package utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import functionalinterfaces.ITitled;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Jordan on 8/3/2017.
 */

public class ListHelper {

    public static ArrayList getRandomItemsFromList(ArrayList  list , int numOfItemsToGet){
        ArrayList items = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();

        while (items.size() <  numOfItemsToGet){
            int rand = MathHelper.getRandomNum(list.size() );
            if(nums.indexOf(rand) == -1){
                items.add(list.get(rand));
                nums.add(rand);
            }
        }
        return items;
    }

    public static ArrayList getRandomItemsWithOnePreSet(int numOfItems, ArrayList<ITitled> listToGetFrom , Object preSetListItem){
        ArrayList  items = new ArrayList<>();
        ArrayList<String> addedIndexes = new ArrayList<>();
        items.add(preSetListItem);
        String presetKey = preSetListItem instanceof String ? ((String) preSetListItem).toLowerCase().trim() : ((ITitled) preSetListItem).getTitle() ;
        addedIndexes.add( presetKey );
        while ( items.size() < numOfItems  ){

            int rand = MathHelper.getRandomNum(    listToGetFrom.size()    );
            Object itemToAdd = listToGetFrom.get(   rand   ) ;
            String key = ((ITitled) itemToAdd).getTitle().toLowerCase().trim() ;
            if(addedIndexes.indexOf ((key))  == -1  ) {
                items.add(itemToAdd);
                addedIndexes.add( ((ITitled) itemToAdd ).getTitle()  );
            }
        }
        return items ;
    }


}
