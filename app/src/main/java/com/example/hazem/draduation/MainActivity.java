package com.example.hazem.draduation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btngo;
    String category="aaaa";
     AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Mybrodcast.class);
        sendBroadcast(intent);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_frist_act);
        setSupportActionBar(toolbar);
        adView = (AdView) findViewById(R.id.adMob);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        btngo=(Button)findViewById(R.id.BTgo);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,Books.class);
                intent.putExtra("catname",category);
                startActivity(intent);
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_life:
                if (checked)
                 category="Life";
                    break;
            case R.id.radio_love:
                if (checked)
                    category="Love";
                    break;
            case R.id.radio_sport:
                if (checked)
                    category="Sport";
                break;
            case R.id.radio_time:
                if (checked)
                    category="Time";
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.main,menu);
        return  super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.ff){
            Intent intent=new Intent(MainActivity.this,FavouriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
