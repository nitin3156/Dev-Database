package com.example.nitintonger.devdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Nitin Tonger on 23-08-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="sheet.db";
    public static final String TABLE_NAME="cost";
    public static final String COL_ID="ID";
    public static final String COL_FROM="TRAVELF";
    public static final String COL_TO="TRAVELT";
    public static final String COL_FARE="FARE";
    public static final String COL_DATE="DATE";
    public static final String COL_NO="VECNO";
    public static final String COL_DRIVER="DRIVER";
    public static final String COL_DIESEL="DIESEL";
    public static final String COL_PAYMENT="PAYMENT";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cost"+"( ID INTEGER PRIMARY KEY AUTOINCREMENT,TRAVELF TEXT,TRAVELT TEXT,FARE TEXT,DATE TEXT,VECNO TEXT,DRIVER TEXT,DIESEL TEXT,PAYMENT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String tfrom,String tto,String tfare,String tdate,String tvec,String tdriver,String tdiesel,String tpayment)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_FROM,tfrom);
        values.put(COL_TO,tto);
        values.put(COL_FARE,tfare);
        values.put(COL_DATE,tdate);
        values.put(COL_NO,tvec);
        values.put(COL_DRIVER,tdriver);
        values.put(COL_DIESEL,tdiesel);
        values.put(COL_PAYMENT,tpayment);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,values);
        if (result== -1)
            return false;
        else
            return true;
    }
    public Cursor getdata()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_NAME ;
        Cursor res=sqLiteDatabase.rawQuery(query,null);

        return res;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
    public boolean updateData(String id ,String tfrom,String tto,String  tfare,String tdate,String tvec,String tdriver,String tdiesel,String tpayment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_FROM,tfrom);
        values.put(COL_TO,tto);
        values.put(COL_FARE,tfare);

        values.put(COL_DATE,tdate);
        values.put(COL_NO,tvec);
        values.put(COL_DRIVER,tdriver);
        values.put(COL_DIESEL,tdiesel);
        values.put(COL_PAYMENT,tpayment);
        db.update(TABLE_NAME, values, "ID = ?",new String[] {id});
        return true;
    }
   /* public Cursor total()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM (FARE) FROM COST", null);

        return cursor;
    }
*/
}