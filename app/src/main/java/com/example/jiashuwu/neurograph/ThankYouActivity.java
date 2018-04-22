package com.example.jiashuwu.neurograph;

import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class ThankYouActivity extends AppCompatActivity {

    private int user_id;

    private boolean initial_isScale;

    private ProgressBar progressBar;

    private float progress;
    private int count = 0;
    private TextView numbers_textview;
    private TextView percent_textview;



    private ProgressDialog progressDialog;

    private int frequency_per_second = Sharing.frequency_per_second_for_thank_you_page;

    private MyReceiver myReceiver;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        initial_isScale = Sharing.isScale;
        setContentView(R.layout.activity_thank_you);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("ORDER", "ON_START");

        //progressBar = (ProgressBar) findViewById(R.id.thank_you_activity_progressbar);


        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        /*
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage (Message message)
            {

            }
        };
        */

        /*
        count = 0;
        new Thread ()
        {
            public void run ()
            {
                while (count != Sharing.number_of_item_in_total)
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    count = count + 1;
                    int number1 = (int) ((double) count * 100 / (double) Sharing.number_of_item_in_total);
                    float number2 = (float) ((float) count * 100 / (float) Sharing.number_of_item_in_total);
                    progressBar.setProgress(number1);
                    //numbers_textview.setText(count + "/" + Sharing.number_of_item_in_total);
                    //percent_textview.setText(number2 + "%");

                }
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }.start();
        */


        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        ThankYouActivity.this.registerReceiver(myReceiver, intentFilter);


        Log.d("processing", String.valueOf(Sharing.number_of_item_in_total));
        Log.d("destroy", "on_create");


        Sharing.stop_showing_process = 0;
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle(getResources().getString(R.string.thank_you));
        progressDialog.setMax(Sharing.number_of_item_in_total);
        progressDialog.setMessage(getString(R.string.thank_you_for_participation) + "\n" + getString(R.string.thank_you_please_do_not_close));
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
                        Log.d("destroy", String.valueOf(i));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
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
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }).start();



        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }, waiting_time);
        */




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
        unregisterReceiver(myReceiver);
        super.onStop();
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }
}
