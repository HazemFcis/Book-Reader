package com.example.hazem.draduation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_ID;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_Image;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_Overview;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_Rate;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_Title;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_Year;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Book_publisher;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.TableName;
import static com.example.hazem.draduation.FavoriteContract.FavoriteEntry.Table_ID;

/**
 * Created by Hazem on 8/3/2018.
 */

public class FavoriteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase mSQLiteDatabase;
    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         final String SQL_CREATE_FAVORITE_TABLE = "create table " + TableName +
                "( " + Table_ID + " integer primary key autoincrement ," +
                Book_ID + " text ," + Book_Image + " text , " +
                Book_Title + " text , " +  Book_Rate + " text , " +
                Book_Year + " text , " + Book_publisher + " text , " + Book_Overview + " text ) ;";
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TableName);
        onCreate(sqLiteDatabase);
    }
    public void addfav(String _Book_ID, String _Book_Image, String _Book_Title, Double _Book_Rate, String _Book_Year, String _Book_Overview, String Publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Book_ID, _Book_ID);
        contentValues.put(Book_Image, _Book_Image);
        contentValues.put(Book_Title, _Book_Title);
        contentValues.put(Book_Rate, _Book_Rate);
        contentValues.put(Book_Year, _Book_Year);
        contentValues.put(Book_publisher, Publisher);
        contentValues.put(Book_Overview, _Book_Overview);
        //context.getContentResolver().insert(ContentProv.M_uri, contentValues);
        db.insert(TableName, null, contentValues);
        db.close();
    }
    public Cursor Exists(String s) {
        mSQLiteDatabase = getReadableDatabase();
        return mSQLiteDatabase.rawQuery("select * from " + TableName + " where " + Book_ID + " = " + "'" + s + "'", null);
    }
}
