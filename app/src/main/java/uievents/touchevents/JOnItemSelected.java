package uievents.touchevents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Jordan on 5/4/2017.
 */

public class JOnItemSelected implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    private OnItemLongClickListener longClickListener ;

    GestureDetector mGestureDetector;

    public JOnItemSelected(Context context , OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    public JOnItemSelected(Context context , final RecyclerView recyclerView , final OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
//                super.onLongPress(e);
                View child = recyclerView.findChildViewUnder(e.getX() , e.getY());
                longClickListener.onItemLongClick(recyclerView.getChildAdapterPosition(child));

            }
        });
    }

    public static AdapterView.OnItemSelectedListener mOnItemSelected(    OnItemClickListener onItemClickListener ) {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                onItemClickListener.onItemClick(view, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        };
    };

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        if(childView != null && longClickListener != null && mGestureDetector.onTouchEvent(e)){
            longClickListener.onItemLongClick(view.getChildAdapterPosition(childView));
        }
        return false;
    }


    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }


}
