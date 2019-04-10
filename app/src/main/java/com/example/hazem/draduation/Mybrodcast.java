package com.example.hazem.draduation;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hazem on 8/10/2018.
 */

public class Mybrodcast extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {

        // Explicitly specify that which service class will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                MyIntentService.class.getName());
        Log.d("ooooooooo",MyIntentService.class.getName());
        if(isConnected(context)){
            Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Not Connected",Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("isNetworkConnected",isConnected(context));
        context.startService(intent.setComponent(comp));
    }

    public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
