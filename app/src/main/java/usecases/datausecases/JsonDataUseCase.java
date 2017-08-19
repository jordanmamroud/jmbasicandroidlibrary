package usecases.datausecases;

import org.json.JSONArray;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import utilities.RxHelper;

/**
 * Created by Jordan on 8/17/2017.
 */

public  abstract class JsonDataUseCase implements IDataUseCase {

    private JSONArray dataLocation;

    public JsonDataUseCase(JSONArray dataLocation  ) {  this.dataLocation = dataLocation ;  }

    // lazy load
    public Observable readItemsFromPosition(int currentPos, int numOfItems ){
        return RxHelper.createObservable( (ObservableEmitter<Object> e )->  getItemsFromPosition( currentPos, numOfItems, e  ));
    }

    public void getItemsFromPosition(int position, int numOfItemToRead, ObservableEmitter e )throws Exception{
        int startIndex = position - numOfItemToRead <  0  ? 0 : position- numOfItemToRead ;
        int endIndex = position + numOfItemToRead ;
        for (   int i = startIndex ; i < endIndex  ; i++   )  if (  itemExists(dataLocation , i)   )     mapItem(position, dataLocation.get(i) , e    )  ;
        e.onComplete();
    }

    public Observable readItemsInRange(int startIndex, int endIndex   ){
        return RxHelper.createObservable( (ObservableEmitter<Object> e )->{   getItemsInRange(startIndex, endIndex ,  e );    });
    }

    private void getItemsInRange(int startIndex, int endIndex, ObservableEmitter e ) throws Exception{
        for (int i = startIndex ; i < endIndex  ; i++) if (  itemExists(dataLocation , i)   )      mapItem(i,   dataLocation.get(i), e );
        e.onComplete();
    }

    public Observable readAllItems(){   return RxHelper.createObservable( this :: getAllItems );    }

    private void getAllItems(ObservableEmitter e ) throws  Exception{
        for (   int i = 0; i < dataLocation.length()  ; i++  )  if (  itemExists( dataLocation ,  i)   ) mapItem(i , dataLocation.get(i), e  );
        e.onComplete();
    }

    public boolean itemExists(Object list , int pos) throws Exception{  return pos <    dataLocation.length()   &&   pos > -1     ; }

    public abstract void mapItem(int pos, Object obj , ObservableEmitter e)throws Exception ;

 }
