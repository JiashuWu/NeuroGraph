package com.example.jiashuwu.neurograph;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class DisplayCalculatingActivity extends AppCompatActivity {

    private int user_id;

    private ProgressDialog progressDialog;

    private MyReceiver myReceiver;

    private int frequency_per_second = Sharing.frequency_per_second;

    public void init_theme ()
    {
        switch (Sharing.colour)
        {
            case "blue": setTheme(R.style.AppTheme); break;
            case "light_blue": setTheme(R.style.AppThemeLightBlue); break;
            case "green": setTheme(R.style.AppThemeGreen); break;
            case "purple": setTheme(R.style.AppThemePurple); break;
            case "pink": setTheme(R.style.AppThemePink); break;
            case "orange": setTheme(R.style.AppThemeOrange); break;
            default:setTheme(R.style.AppTheme); break;
        }
    }

    public void initLocaleLanguage ()
    {
        Resources resource = getApplicationContext().getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        Locale DUTCH = new Locale("nl", "NL");
        Locale PORTUGAL = new Locale("pt", "PT");
        Locale RUSSIA = new Locale("ru", "RU");
        Locale SPAIN = new Locale("es", "ES");
        switch (Sharing.language)
        {
            case "English": configuration.locale = Locale.UK;break;
            case "Simplified Chinese": configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese": configuration.locale = Locale.TAIWAN;break;
            case "Dutch": configuration.locale = DUTCH;break;
            case "French": configuration.locale = Locale.FRANCE;break;
            case "German": configuration.locale = Locale.GERMANY;break;
            case "Italian": configuration.locale = Locale.ITALY;break;
            case "Portuguese": configuration.locale = PORTUGAL;break;
            case "Russian": configuration.locale = RUSSIA;break;
            case "Spanish": configuration.locale = SPAIN;break;
            default: configuration.locale = Locale.UK;break;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(DisplayCalculatingActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DisplayCalculatingActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calculating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        DisplayCalculatingActivity.this.registerReceiver(myReceiver, intentFilter);


        Sharing.stop_showing_process = 0;
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle(getString(R.string.Processing));
        progressDialog.setMax(Sharing.number_of_item_in_total);
        progressDialog.setMessage(getString(R.string.calculating_the_test_result) + "\n" + getString(R.string.calculating_test_result_do_not_close));
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 100 && Sharing.stop_showing_process == 0)
                {
                    try
                    {
                        int waiting_time =  Sharing.number_of_item_in_total / frequency_per_second *1000 / 100;
                        if (waiting_time == 0)
                        {
                            waiting_time = 19;
                        }
                        Log.d("processing", String.valueOf(Sharing.number_of_item_in_total));
                        Log.d("processing", String.valueOf(waiting_time));
                        Thread.sleep(waiting_time);
                        if (Sharing.number_of_item_in_total / 100 == 0)
                        {
                            if (progressDialog.getProgress() < 98.5)
                            {
                                progressDialog.incrementProgressBy(1);
                            }
                        }
                        else
                        {
                            if (progressDialog.getProgress() < Sharing.number_of_item_in_total * 0.985)
                            {
                                progressDialog.incrementProgressBy((int)Math.ceil((double) Sharing.number_of_item_in_total / 100));
                            }
                        }
                        if (i != 99)
                        {
                            i++;
                        }
                        // i++;

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //progressDialog.setProgress(Sharing.number_of_item_in_total);
                if (Sharing.number_of_item_in_total != 0)
                {
                    progressDialog.setProgress(Sharing.number_of_item_in_total);
                }
                else
                {
                    progressDialog.setProgress(100);
                }
                try
                {
                    Thread.sleep(Sharing.hundred_sleeping_time);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                Intent intent = new Intent(DisplayCalculatingActivity.this, ParallelLinePracticeResultActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                startActivity(intent);
                DisplayCalculatingActivity.this.finish();

            }
        }).start();
    }

    @Override
    public void onStart ()
    {
        super.onStart();
    }

    @Override
    public void onRestart ()
    {
        super.onRestart();
    }

    @Override
    public void onResume ()
    {
        super.onResume();
    }

    @Override
    public void onPause ()
    {
        super.onPause();
    }



    @Override
    public void onStop ()
    {
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
        super.onStop();

    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }
}
