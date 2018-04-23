package com.example.jiashuwu.neurograph;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class DisplayLoadingActivity extends AppCompatActivity {

    private MyReceiver myReceiver;

    private ProgressDialog progressDialog;

    private int frequency_per_second = Sharing.frequency_per_second;

    // private MyDatabaseHelper databaseHelper3;
    // private SQLiteDatabase database3;

    private String databaseName = DatabaseInformation.databaseName;

    private int databaseVersion = DatabaseInformation.databaseVersion;

    private int test_id;
    private int user_id;
    private String name;
    private String timestamp_of_point;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;

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
            TextScaleUtilsLower.scaleTextSize(DisplayLoadingActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DisplayLoadingActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        DisplayLoadingActivity.this.registerReceiver(myReceiver, intentFilter);

        /*

        test_id = Integer.parseInt(getIntent().getStringExtra("test_id").toString());
        name = getIntent().getStringExtra("name").toString();
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_starting_time = getIntent().getStringExtra("test_starting_time").toString();
        test_ending_time = getIntent().getStringExtra("test_ending_time").toString();
        test_type = getIntent().getStringExtra("test_type").toString();
        image_type = getIntent().getStringExtra("image_type").toString();
        interval_duration = Integer.parseInt(getIntent().getStringExtra("interval_duration").toString());

        */

        /*
        databaseHelper3 = new MyDatabaseHelper (DisplayLoadingActivity.this, databaseName, null, databaseVersion);
        databaseHelper3.getReadableDatabase();

        String query = "SELECT * FROM Test WHERE test_id = ? AND user_id = ? AND test_starting_time = ? AND test_ending_time = ? AND test_type = ? AND image_type = ? AND interval_duration = ? ";
        String [] parameters = new String[] {String.valueOf(test_id), String.valueOf(user_id), test_starting_time, test_ending_time, test_type, image_type, String.valueOf(interval_duration)};

        database3 = databaseHelper3.getReadableDatabase();

        Cursor cursor = database3.rawQuery(query, parameters);
        while (cursor.moveToNext())
        {
            Sharing.number_of_item_in_total = Integer.parseInt(cursor.getString(7).toString());
        }
        if (cursor != null)
        {
            cursor.close();
        }
        if (databaseHelper3 != null)
        {
            databaseHelper3.close();
        }
        if (database3 != null)
        {
            database3.close();
        }
        */

        Sharing.stop_showing_process = 0;
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setMax(Sharing.number_of_item_in_total);
        progressDialog.setMessage(getString(R.string.do_not_close));
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
                                progressDialog.incrementProgressBy((int) Math.ceil((double) Sharing.number_of_item_in_total / 100));
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

                Intent intent = new Intent(DisplayLoadingActivity.this, TestDetailScrollingActivity.class);
                startActivity(intent);
                DisplayLoadingActivity.this.finish();

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
