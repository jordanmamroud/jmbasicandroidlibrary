package fragments.GridView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnItemSelected;

import java.util.ArrayList;

import fragments.Lists.IAdapterDelegates;

public class GridViewFragment extends Fragment {

    private String OUTSTATE_KEY_NUMIMAGES = "numOfAvailableImages" ;
    private RecyclerView imagesGrid ;
    private ArrayList<Integer> images = new ArrayList<>();
    private GridViewAdapter gridViewAdapter ;
    private IGridViewFragment iActivity ;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(  R.layout.fragment_grid_view   , container , false   );

        iActivity = (IGridViewFragment) getActivity() ;
        images = iActivity.getGridImages();
        instantiateView(v);
        setupCallbacks();
        return v    ;
    }

    public void instantiateView(View v){
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        gridViewAdapter = new GridViewAdapter(getContext(), images  ,   iActivity.getViewDelegate()    );
        imagesGrid = (RecyclerView) v.findViewById(R.id.imagesGrid);
        imagesGrid.setLayoutManager(manager);
        imagesGrid.setAdapter(gridViewAdapter);
    }

    public void update(){
        gridViewAdapter.updateDelegate(     6    );
    }

    public void setupCallbacks() {
        // method is called from inside activity because it opens new fragment
        imagesGrid.addOnItemTouchListener( new MOnItemSelected(getContext() ,
                (View v ,int position)->  iActivity.onGridImageClick(position)));
    } ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public interface IGridViewFragment{
        void onGridImageClick(int position);
        ArrayList getGridImages();
        IAdapterDelegates getViewDelegate();
    }

}