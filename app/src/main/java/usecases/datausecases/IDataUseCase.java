package usecases.datausecases;

import io.reactivex.Observable;

/**
 * Created by Jordan on 8/17/2017.
 */

public interface IDataUseCase {

    Observable readItemsFromPosition(int currentPos, int numOfItems ) ;

    Observable readItemsInRange(int startIndex, int endIndex  ) ;

    Observable readAllItems( ) ;

}
