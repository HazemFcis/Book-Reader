package com.example.hazem.draduation;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Hazem on 8/4/2018.
 */

public class ContentProv extends ContentProvider {
    private SQLiteDatabase dbHelper;
    private static final String DBNAME = "favorite.db";
    public static final Uri M_uri  = Uri.parse("content://" + "com.example.hazem.draduation.ContentProv"+ "/FavouriteTable");
    public static final Uri Wed_URI = M_uri.buildUpon().appendPath("FavouriteTable").build();
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI("com.example.hazem.draduation.ContentProv", "FavouriteTable", 0);
    }
    @Override
    public boolean onCreate() {
        dbHelper=new FavoriteDbHelper(getContext()).getWritableDatabase();
        return  true ;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return dbHelper.query("FavouriteTable",strings,s,strings1,null,null,s1,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        dbHelper.insert("FavouriteTable",null,contentValues);
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int r=dbHelper.update("FavouriteTable",contentValues,s,strings);
        if (r>0)getContext().getContentResolver().notifyChange(uri,null);
        return r;
    }
}
