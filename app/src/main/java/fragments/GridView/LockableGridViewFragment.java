package fragments.GridView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnItemSelected;

import java.util.ArrayList;

public class LockableGridViewFragment extends Fragment {

    private String OUTSTATE_KEY_NUMIMAGES = "numOfAvailableImages" ;

    private RecyclerView imagesGrid ;
    private ArrayList<Integer> images= new ArrayList<>();
    private LockableGridviewAdapter gridViewAdapter ;
    private IGridViewFragment iActivity ;
    private int numOfAvailableImages ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(  R.layout.fragment_grid_view   , container , false   );
        if (savedInstanceState != null){
            numOfAvailableImages = savedInstanceState.getInt(OUTSTATE_KEY_NUMIMAGES);
        }

        iActivity = (IGridViewFragment) getActivity() ;
        images = iActivity.getGridImages();
        instantiateView(v);
        setupCallbacks();
        return v    ;
    }

    public void instantiateView(View v){
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        gridViewAdapter = new LockableGridviewAdapter(getContext(), images , numOfAvailableImages);
        imagesGrid = (RecyclerView) v.findViewById(R.id.imagesGrid);
        imagesGrid.setLayoutManager(manager);
        imagesGrid.setAdapter(gridViewAdapter);
    }

    public void unlockAll(){
        gridViewAdapter.unlockAll();
    }

    public void setupCallbacks() {
        // method is called from inside activity because it opens new fragment
        imagesGrid.addOnItemTouchListener( new MOnItemSelected(getContext() ,
                (View v ,int position)->  iActivity.onGridImageClick(position)));
    } ;

    public void setNumOfAvailableImages(int numOfAvailableImages){
        this.numOfAvailableImages = numOfAvailableImages;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(OUTSTATE_KEY_NUMIMAGES , numOfAvailableImages);
    }

    public interface IGridViewFragment{
        void onGridImageClick(int position);
        ArrayList getGridImages();
    }

}
