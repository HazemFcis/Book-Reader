package com.example.hazem.draduation;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Hazem on 8/10/2018.
 */

public class MyIntentService extends IntentService {

    SharedPreferences sharedPreferences;
    boolean is = true;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("myfile", MODE_PRIVATE);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            assert extras != null;
            boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");

            Log.d("uuuuuuuuuuuu", String.valueOf(isNetworkConnected));

        }

    }


}
