package utilities;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jordan on 8/16/2017.
 */

public class RxHelper {

    public static Observable createObservable(ObservableOnSubscribe<Object> runonsubscribe){
        return Observable.create(    runonsubscribe      ).subscribeOn( Schedulers.newThread()   ).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable createObservable(ObservableOnSubscribe<Object> runonsubscribe, Scheduler subscribeOn  ){
        return Observable.create(    runonsubscribe      ).subscribeOn( subscribeOn  ).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable createObservable(ObservableOnSubscribe<Object> runonsubscribe, Scheduler subscribeOn, Scheduler observeOn ){
        return Observable.create(    runonsubscribe      ).subscribeOn( subscribeOn  ).observeOn(observeOn);
    }

    public static void subcribe(Observable<Object> observable , Consumer<Object> onNext , @Nullable Action onComplete){
        if( onComplete == null ) {
            observable.subscribe(onNext) ;
        }else {
            Consumer<Object> onError = JHelper ::  printError   ;
            observable.subscribe(onNext , onError    ,   onComplete) ;
        }
    }

    public static void subcribe(Observable<Object> observable , Consumer<Object> onNext  ){ observable.subscribe(onNext      ) ;    }

    public static void subcribe(Observable<Object> observable , Consumer<Object> onNext , Consumer<Object> onerr ,@NonNull Action onComplete){
        // only setting if null because passing null would throw error if not done.
        if(onerr == null) onerr = (Object err) -> JHelper.printError("error passed was null") ;
        observable.subscribe(onNext , onerr    ,   onComplete) ;
    }

}
