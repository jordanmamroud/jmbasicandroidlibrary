package functionalinterfaces;

import android.database.Cursor;
import android.support.annotation.Nullable;

/**
 * Created by Jordan on 8/22/2017.
 */
// used to map items to what we want inside of domain.sql queries, remove having to write vebose code.
public interface SqlItemMapper {
    Object mapItem(  Cursor cursor);
}
