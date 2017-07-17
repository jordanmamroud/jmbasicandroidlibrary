package Events;

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

    public static class FragmentChange{
        private String currentFrag ;
        // only used for items clicked in lists
        private int clickedPosition;

        public FragmentChange(String currentFrag) {
            this.currentFrag = currentFrag;
        }

        public FragmentChange(String currentFrag, int clickedPosition) {
            this.currentFrag = currentFrag;
            this.clickedPosition = clickedPosition;
        }

        public String getCurrentFrag(){
            return currentFrag;
        }

        public int getClickedPosition(){
            return clickedPosition;
        }
    }

}
