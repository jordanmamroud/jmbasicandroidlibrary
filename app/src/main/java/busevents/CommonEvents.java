package busevents;

import android.os.Bundle;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by Jordan on 7/17/2017.
 */

public class CommonEvents {

    public static class ListItemAdded{
        Object addedItem ;

        public ListItemAdded(Object object){this.addedItem = object;}

        public Object getAddedItem() { return addedItem; }

    }

    public static class OnListComplete {
        public OnListComplete() {
        }
    }

    public static class UpgradeStatus {
        private boolean isUpgraded;

        public UpgradeStatus(boolean isUpgraded) {
            this.isUpgraded = isUpgraded;
        }

        public boolean isUpgraded(){
            return isUpgraded;
        } ;
    }

    // ie user presses button to upgrade to premium
    public static class UpgradeRequest{
        boolean test;
        public UpgradeRequest() {}
        public UpgradeRequest(boolean test) {this.test = test ;}

        public boolean getTest(){return test ; };
    }

    public static class ItemAdded{
        Object addedItem ;

        public ItemAdded(Object addedItem) {
            this.addedItem = addedItem;
        }

        public Object getAddedItem(){return addedItem;}
    }

    public static class AddItems {
        ArrayList listToAddTo;
        int numOfItemsToAdd ;
        Observer observer ;

        public AddItems(ArrayList listToAddTo, int numOfItemsToAdd, Observer observer) {
            this.listToAddTo = listToAddTo;
            this.numOfItemsToAdd = numOfItemsToAdd;
            this.observer = observer;
        }

        public ArrayList getListToAddTo() { return listToAddTo; }

        public int getNumOfItemsToAdd() {   return numOfItemsToAdd; }

        public Observer getObserver() { return observer;    }
    }

    // ie viewpager position gets changed
    public static class PositionChange{
        int position ;

        public PositionChange(int position) {
            this.position = position;
        }

        public int getPosition(){
            return  position;
        }
    }

    public static class FragmentChangeRequest {
        private String fragmentToLaunch ;
        private Bundle extras ;
        // only used for items clicked in lists
        private Object extraInfo;

        public FragmentChangeRequest() { }

        public FragmentChangeRequest(String launchId , Bundle extras ) {
            this.fragmentToLaunch = launchId ;
            this.extras = extras ;
        }

        public FragmentChangeRequest setFragmentToLaunchId(String currentFrag) {
            this.fragmentToLaunch = currentFrag;
            return this;
        }

        public FragmentChangeRequest setExtraInfo(Object extraInfo){
            this.extraInfo = extraInfo ;
            return this ;
        }

        public Bundle getExtras() { return extras;}

        public void setExtras(Bundle extras) {  this.extras = extras;   }

        public String getFragmentToLaunchId(){
            return fragmentToLaunch;
        }

        public Object getExtraInfo(){
            return extraInfo;
        }
    }

    public static class FragmentOpened {
        private String newFragId ;
        private Object info ;

        public FragmentOpened(String newFragId, Object info) {
            this.newFragId = newFragId;
            this.info = info;
        }

        public String getNewFragId() {return newFragId;}

        public void setNewFragId(String newFragId) {this.newFragId = newFragId;}

        public Object getInfo() { return info; }

        public void setInfo(Object info) { this.info = info;}
    }

    public static class UpdateAppbar {
        String mainTxt ;
        String fragId ;

        public UpdateAppbar(String fragId) {
            this.fragId = fragId;
        }

        public String getFragId() {
            return fragId;
        }

        public void setFragId(String fragId) {
            this.fragId = fragId;
        }

        public UpdateAppbar(String fragId , String mainTxt) {
            this.mainTxt = mainTxt;
        }

        public String getMainTxt() {
            return mainTxt;
        }

        public void setMainTxt(String mainTxt) {
            this.mainTxt = mainTxt;
        }
    }

}
