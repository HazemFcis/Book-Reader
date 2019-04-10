package com.example.hazem.draduation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class BookDetail extends AppCompatActivity {
    TextView t_title, t_publisher, t_describ, t_year, t_rate;
    String img, ye, y, id, pub, des, tit;
    double rate;
    ImageView imageView;
    Button button;
    FavoriteDbHelper favoriteDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        t_year = (TextView) findViewById(R.id.Tv_year);
        t_rate = (TextView) findViewById(R.id.Tv_rate);
        t_title = (TextView) findViewById(R.id.TV_Title);
        t_publisher = (TextView) findViewById(R.id.TV_publisher);
        t_describ = (TextView) findViewById(R.id.TV_describtion);
        imageView = (ImageView) findViewById(R.id.IM_poster);
        button = (Button) findViewById(R.id.Baddfav);
        final Intent intent = getIntent();
        tit = intent.getStringExtra("title").toString();
        t_title.setText(tit);
        id = intent.getStringExtra("id").toString();
        ye = intent.getStringExtra("date").toString();
        pub = intent.getStringExtra("publisher").toString();
        rate = intent.getDoubleExtra("rate", 0.0);
        t_rate.setText(getString(R.string.rate) + String.valueOf(rate));
        des = intent.getStringExtra("Description").toString();
        y = ye.substring(0, 4);
        t_year.setText(getString(R.string.year) + y);
        t_describ.setText(getString(R.string.description) + des);
        t_publisher.setText(getString(R.string.publisher) + pub);
        img = intent.getStringExtra("img").toString();
        Picasso.with(this).load(String.valueOf(img)).into(imageView);
        favoriteDbHelper = new FavoriteDbHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favoriteDbHelper.Exists(id).getCount() == 0) {
                    favoriteDbHelper.addfav(id, img, tit, rate, ye, des, pub);
                }
            }
        });
    }
}
