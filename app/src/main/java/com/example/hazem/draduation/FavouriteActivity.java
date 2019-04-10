package com.example.hazem.draduation;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ArrayList<Model> modelArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    FavoriteDbHelper favoriteDbHelper;
    CursorLoader cursorLoader;
    LoaderManager loadermanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        favoriteDbHelper = new FavoriteDbHelper(this);
        loadermanager = this.getLoaderManager();
        loadermanager.initLoader(1, null, this);
        recyclerView = (RecyclerView) findViewById(R.id.Favourlist);
        modelArrayList = new ArrayList<Model>();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        cursorLoader = new CursorLoader(this, ContentProv.M_uri, new String[]{"idd", "imageposter", "title", "rate", "year", "publisher", "overview"}, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cu) {
        while (cu.moveToNext()) {

            modelArrayList.add(new Model(cu.getString(0), cu.getString(1), cu.getString(2), cu.getString(4), cu.getString(6), cu.getString(5), cu.getDouble(3)));
        }
        cu.close();
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new Adapter(this, modelArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
