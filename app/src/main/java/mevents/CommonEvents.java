package mevents;

/**
 * Created by Jordan on 7/17/2017.
 */

public class CommonEvents {


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
        public UpgradeRequest() {}
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
        // only used for items clicked in lists
        private Object extraInfo;

        public FragmentChangeRequest() { }

        public FragmentChangeRequest setFragmentToLaunchId(String currentFrag) {
            this.fragmentToLaunch = currentFrag;
            return this;
        }

        public FragmentChangeRequest setExtraInfo(Object extraInfo){
            this.extraInfo = extraInfo ;
            return this ;
        }

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
