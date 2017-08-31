package utilities;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
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

    public static Disposable subcribe(Observable<Object> observable , Consumer<Object> onNext , @Nullable Action onComplete){
        if( onComplete == null ) {
            return observable.subscribe(onNext) ;
        }else {
            Consumer<Object> onError = JHelper ::  printError   ;
            return observable.subscribe(onNext , onError    ,   onComplete);
        }
    }

    public static Disposable subscribe(Observable observable){
        return observable.subscribeOn( Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe() ;
    }

    public static Disposable subcribe(Observable<Object> observable , Consumer<Object> onNext  ){
        return observable.subscribe(onNext      ) ;
    }

    public static void subcribe(Observable<Object> observable , Consumer<Object> onNext , Consumer<Object> onerr ,@NonNull Action onComplete){
        // only setting if null because passing null would throw error if not done.
        if(onerr == null)
            onerr = (Object err) -> JHelper.printError("error passed was null") ;
        observable.subscribe(onNext , onerr    ,   onComplete) ;
    }



    public static void subscribe(Observable<Object> observable, Action oncomplete){
        observable.subscribe(new Observer<Object>() {
            public void onSubscribe(@NonNull Disposable d) {}
            public void onNext(@NonNull Object object) {}
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {
                try{
                    oncomplete.run();
                }catch (Exception e){ JHelper.printError(e); }
            }
        });
        observable.subscribeOn( Schedulers.newThread()).observeOn( AndroidSchedulers.mainThread());
    }
}
