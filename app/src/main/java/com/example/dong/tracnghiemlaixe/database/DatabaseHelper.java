package com.example.dong.tracnghiemlaixe.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dong.tracnghiemlaixe.model.Items;

import java.util.ArrayList;

/**
 * Created by DONG on 23-Feb-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME="lythuyetxemay150.sqlite";
    public static final String DBLOCATION="data/data/com.example.dong.tracnghiemlaixe/databases/";
    private Context mcontext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null,1);
        this.mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbPath=mcontext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase!=null)
            mDatabase.close();
    }

    public ArrayList<Items> getListItems(){
        Items items=null;
        ArrayList<Items> itemsList=new ArrayList<>();
        openDatabase();
        Cursor cursor=mDatabase.rawQuery("SELECT *FROM ITEMS",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            items=new Items(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)
                    ,cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
            itemsList.add(items);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return itemsList;
    }
}
