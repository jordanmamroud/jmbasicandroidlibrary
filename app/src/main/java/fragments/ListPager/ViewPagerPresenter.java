package fragments.ListPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import busevents.CommonEvents;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jordan on 8/16/2017.
 */

public class ViewPagerPresenter {


    private ArrayList allItems = new ArrayList()    ;


    public ViewPagerPresenter getItems(int numOfItems){
        EventBus.getDefault().post( new CommonEvents.AddItems(  allItems    ,    numOfItems , getObserver() ) );
        return this ;
    }

    public ArrayList getList(){return allItems; }

    public void onPageChanged(int position){
        if(isNewItemsNeeded(position))  EventBus.getDefault().post( new CommonEvents.AddItems(  allItems, 10 , getObserver() ) );
    }

    private boolean isNewItemsNeeded(int position){
        int posToGetRequestMore = allItems.size() - 2  ;
        return position == posToGetRequestMore ;
    }


    private Observer getObserver(){
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object object) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return observer ;
    }
}
