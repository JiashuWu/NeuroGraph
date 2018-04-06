package com.example.jiashuwu.neurograph;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Jiashu Wu on 6/04/2018.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive (Context context, Intent intent)
    {
        Sharing.stop_showing_process = 1;
        Log.d("BROADCAST___", intent.getStringExtra("stop_showing_process").toString());
        Log.d("BROADCAST__", String.valueOf(Sharing.stop_showing_process));
    }

}
