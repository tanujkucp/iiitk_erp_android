package in.ac.iiitkota.iiitk_erp.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ATTENDANCE.db",TABLE_NAME= "BACKUP";
    public static final String COLUMN_ID = "ID",COLUMN_VALUE="MARK";

    public DBHelper(Context con, SQLiteDatabase.CursorFactory factory, int version) {
        super(con, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE BACKUP ( _ID INTEGER PRIMARY KEY , "+COLUMN_ID
                +" TEXT NOT NULL, "+COLUMN_VALUE+" INT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.close();
        onCreate(db);
    }

    public int deleteBackup(){
        SQLiteDatabase db = getWritableDatabase();
        int num=db.delete(TABLE_NAME,null,null);
        db.close();
        return num;
    }

    public long append(String id,int value){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_VALUE,value);
        // Insert the new row, returning the primary key value of the new row
        long key= db.insert(TABLE_NAME, null, values);
        db.close();
        return key;
    }

    public int update(String id,int value){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID, id);
        values.put(COLUMN_VALUE,value);
        // Which row to update, based on the title
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { id };
        int count= db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
        db.close();
        return count;
    }

    public HashMap<String,String> readAll(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection={COLUMN_ID,COLUMN_VALUE};
        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,COLUMN_ID);
        HashMap<String,String> data=new HashMap<>();
        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
            int value = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VALUE));
            data.put(id,""+value);
        }
        cursor.close();
        db.close();
        return data;
    }

    public int getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor.getCount();
    }

}
