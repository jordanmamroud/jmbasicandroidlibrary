package domain.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import functionalinterfaces.SqlItemMapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import utilities.RxHelper;


/**
 * Created by Jordan on 8/22/2017.
 */

public  class SqlTable {

    private SQLiteOpenHelper sql;
    private String TABLE_NAME ;
    private SqlItemMapper itemMapper;

    public SqlTable(String table , SQLiteOpenHelper sql ) {
        this.sql = sql ;
        this.TABLE_NAME = table ;
    }

    public SqlTable(String table, SQLiteOpenHelper sql, SqlItemMapper itemMapper) {
        this.sql = sql ;
        this.TABLE_NAME = table ;
        this.itemMapper = itemMapper ;
    }

    public ArrayList queryItems(   Cursor cursor ){
        if(itemMapper == null)
            throw new IllegalStateException("SqlTable item mapper must be initialized to run queries");

        ArrayList items = new ArrayList<>();
        SQLiteDatabase db = sql.getReadableDatabase();
        if(cursor.moveToFirst()) {
            do {
                Object item = itemMapper.mapItem(  cursor);
                items.add( item );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items  ;
    }

    public Observable queryItemsAsync( Cursor cursor ){
        SQLiteDatabase db = sql.getReadableDatabase();
        return RxHelper.createObservable( (ObservableEmitter<Object> e)->{
            if(cursor.moveToFirst()) {
                do {
                    Object item = itemMapper.mapItem(  cursor);
                    e.onNext(item);
                } while (cursor.moveToNext());

                e.onComplete();
                cursor.close();
                db.close();
            }
        });
    };

    public void addItem( ContentValues contentValues){
        SQLiteDatabase db = sql.getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateItem(ContentValues contentValues, String column, String[] whereargs ){
        SQLiteDatabase db = sql.getWritableDatabase();
        db.update(TABLE_NAME, contentValues, column + " = ? ", whereargs );
        db.close();
    }

    public boolean hasItem(String column , String key) {
        SQLiteDatabase db = sql.getReadableDatabase();
        String checkQuery = "SELECT " + column + " FROM " + TABLE_NAME + " WHERE " + column + "= '" + key + "'";
        Cursor cursor  = db.rawQuery(checkQuery, null);
        boolean exists = (  cursor.getCount() > 0 );
        cursor.close();
        db.close();
        return exists;
    }

    public ArrayList getAllItemsInTable(){
        Cursor cursor = sql.getReadableDatabase().rawQuery( "SELECT * FROM " + TABLE_NAME , null );
        return queryItems( cursor);
    }

    public Observable readAllItems(){
        Cursor cursor = sql.getReadableDatabase().rawQuery( "SELECT * FROM " + TABLE_NAME , null );
        return queryItemsAsync(cursor);
    }

    public ArrayList querySingleColumn(  String column , String[] columns, String search){
        String WHERE =  column + "="     + "'"  + search +"'" ;
        Cursor cursor = sql.getReadableDatabase().query( TABLE_NAME,  columns, WHERE, null, null, null, null);
        return queryItems(  cursor);
    }

    public void setItemMapper(SqlItemMapper itemMapper){
        this.itemMapper = itemMapper;
    }

}
