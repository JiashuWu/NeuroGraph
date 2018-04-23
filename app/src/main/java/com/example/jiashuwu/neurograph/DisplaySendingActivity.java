package com.example.jiashuwu.neurograph;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.interfaces.DSAKey;
import java.util.Locale;

public class DisplaySendingActivity extends AppCompatActivity {

    private int count = 0;
    private ProgressDialog progressDialog;

    private TextView file_location_textview;

    private Button finish_button;
    private Button copy_file_path_button;

    private int frequency_per_second = Sharing.frequency_per_second;

    private MyReceiver myReceiver;

    private final Handler handler = new Handler()
    {
        public void handleMessgae (Message message)
        {
            final int what = message.what;
            switch (what)
            {
                case 0: updateTextview();
            }
        }
    };

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

    private void updateTextview ()
    {
        file_location_textview.setText(Sharing.file_path);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(DisplaySendingActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DisplaySendingActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("PROGRESSES", String.valueOf(Sharing.number_of_item_in_total));

        file_location_textview = (TextView) findViewById(R.id.display_sending_file_location_textview);
        finish_button = (Button) findViewById(R.id.display_sending_finish_button);
        copy_file_path_button = (Button) findViewById(R.id.display_sending_copy_file_path_button);

        file_location_textview.setText(R.string.copy_file_path_message);

        copy_file_path_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DisplaySendingActivity.this, getString(R.string.file_path_copied), Toast.LENGTH_LONG).show();
            }
        });

        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sharing.redirect_source.equalsIgnoreCase("switch_setting"))
                {
                    Intent intent = new Intent (DisplaySendingActivity.this, SettingPageActivity.class);
                    startActivity(intent);
                    DisplaySendingActivity.this.finish();
                }
                else {
                    Intent intent = new Intent(DisplaySendingActivity.this, DataListActivity.class);
                    startActivity(intent);
                    DisplaySendingActivity.this.finish();
                }
            }
        });

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        DisplaySendingActivity.this.registerReceiver(myReceiver, intentFilter);


        Sharing.stop_showing_process = 0;

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle(getString(R.string.sending));
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
                        Log.d("iiiiii", String.valueOf(i));

                        //i++;
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
            }
        }).start();


        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayProgressActivity.this);
        builder.setTitle("Successfully Saved");
        builder.setCancelable(false);
        builder.setMessage("The file has been saved into the file system.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DisplayProgressActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplayProgressActivity.this.finish();
            }
        });
        builder.setNegativeButton("Copy File Path", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DisplayProgressActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DisplayProgressActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplayProgressActivity.this.finish();
            }
        });
        builder.create();
        builder.show();
        */




        /*
        count = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBar.getProgress() != 100)
                {
                    try
                    {
                        Thread.sleep(100);
                        progressBar.incrementProgressBy(1);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        */


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_sending_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_display_sending_sd_card)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(DisplaySendingActivity.this);
            builder.setTitle(getString(R.string.copy_file_path));
            builder.setCancelable(false);
            builder.setMessage(getString(R.string.file_path_) + "\n" + Sharing.file_path);
            builder.setPositiveButton(getString(R.string.button_copy), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(DisplaySendingActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();
                }
            });
            builder.create();
            builder.show();
        }
        return true;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (Sharing.redirect_source.equalsIgnoreCase("switch_setting"))
            {
                Intent intent = new Intent (DisplaySendingActivity.this, SettingPageActivity.class);
                startActivity(intent);
                DisplaySendingActivity.this.finish();
            }
            else
            {
                Intent intent = new Intent(DisplaySendingActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplaySendingActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        if (myReceiver != null)
        {
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
