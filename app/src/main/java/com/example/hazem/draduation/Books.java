package com.example.hazem.draduation;

import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Books extends AppCompatActivity {
    private String ID;
    private String Image;
    private String Title;
    private String Year;
    private String Description;
    private String publisher;
    private Double rate;
    ArrayList<Model> modelArrayList = new ArrayList<Model>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private final String KEY_RECYCLER_STATE = "recycler_state";
    String cat;
    private Parcelable savedRecyclerLayoutState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        cat = intent.getStringExtra("catname").toString();
        recyclerView = (RecyclerView) findViewById(R.id.Mainlist);
        if(savedRecyclerLayoutState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        GetJson(cat);
    }

    void GetJson(String q) {
        modelArrayList.clear();// clear list because Redundancy----------------------------

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.googleapis.com/books/v1/").build();
        GetData getData = retrofit.create(GetData.class);
        Call<ResponseBody> call = getData.get(q, "books");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    Log.d("ffff", jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        ID = object.optString("id");
                        Log.d("iddddddddddd", ID);
                        Title = object.getJSONObject("volumeInfo").optString("title");
                        publisher = object.getJSONObject("volumeInfo").optString("publisher");
                        if (publisher == null) {
                            publisher = "";
                        }
                        Year = object.getJSONObject("volumeInfo").optString("publishedDate");
                        Log.d("yeeeer", String.valueOf(Year));
                        Description = object.getJSONObject("volumeInfo").optString("description");
                        rate = object.getJSONObject("volumeInfo").optDouble("averageRating");
                        if (Description == null) {
                            Description = "";
                        }

                        Image = object.getJSONObject("volumeInfo").getJSONObject("imageLinks").optString("smallThumbnail");
                        Log.d("imgggggggggg", Image);
                        modelArrayList.add(new Model(ID, Image, Title, Year, Description, publisher, rate));
                        Log.d("okkkkkkkk", modelArrayList.toString());
                    }
                    //-----set Adapter For Recycleview (MainList) ---------------------------------
                    Adapter adapter = new Adapter(getApplicationContext(), modelArrayList);
                    recyclerView.setAdapter(adapter);
                    //-------------------------------------------------------------------

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface GetData {
        @GET("volumes")
        Call<ResponseBody> get(@Query("q") String q, @Query("printType") String printType);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT,recyclerView.getLayoutManager().onSaveInstanceState());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
    }
}
