package domain.usecases.datausecases;

/**
 * Created by Jordan on 8/22/2017.
 */

public interface IDataWriter {

    void writeItem(String dblocation, Object item);

    void updateItem(    String dblocation, Object updates   );

}
