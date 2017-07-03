package fragments.GridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.jordan.basicslibrary.R;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/1/2017.
 */

public class MGridViewAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<String> imageTitles ;
        private ArrayList<Integer> images;


        public MGridViewAdapter(Context c , ArrayList<Integer> images ) {
            mContext = c;
            this.images = images ;
        }

        public int getCount() {
            return images.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.item_grid_image, parent , false);

            ImageView imageView = (ImageView)grid.findViewById(R.id.thumbnail);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images.get(position));


            return grid;
        }
}

