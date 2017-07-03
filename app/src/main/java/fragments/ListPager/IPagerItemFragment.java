package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jordan on 5/25/2017.
 */

public interface IPagerItemFragment {

    View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    Fragment newInstance(Object object, int position);
    void setItem(Object object, int position);

}
