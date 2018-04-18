package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    private TextView info_activity_textview_1;
    private int displayWidth;
    private int displayHeight;

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
            TextScaleUtilsLower.scaleTextSize(InfoActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(InfoActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //info_activity_textview_1 = (TextView) findViewById(R.id.info_page_textview1);

        /*
        String device_information_title = "Your device information : \n";

        int testing_area_height = Sharing.device_height_in_pixels - Sharing.device_navigation_bar_height;
        String device_information = "Device Brand : " + Sharing.device_brand + "\n"
                + "Device Manufacturer : " + Sharing.device_manufacturer + "\n"
                + "Device Product Information : " + Sharing.device_product_name + "\n"
                + "Device Model : " + Sharing.device_model + "\n"
                + "Device System Version Code : " + Sharing.device_system_version_code + "\n"
                + "Device Resolution : " + Sharing.device_height_in_pixels + " X " + Sharing.device_width_in_pixels + "\n"
                + "Device Testing Area : " + testing_area_height + " X " + Sharing.device_width_in_pixels + "\n"
                + "Device Navigation Bar Height : " + Sharing.device_navigation_bar_height + "\n";
        */

        /*
        Display display = getWindowManager().getDefaultDisplay();
        displayWidth = display.getWidth();
        displayHeight = display.getHeight();
        */

        /*
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
        */

        //info_activity_textview_1.setText(device_information_title + device_information);


    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent (InfoActivity.this, SettingPageActivity.class);
            startActivity(intent);
            InfoActivity.this.finish();
        }
        else if (id == R.id.action_info_device)
        {
            Intent intent = new Intent (InfoActivity.this, DeviceInformationActivity.class);
            startActivity(intent);
            InfoActivity.this.finish();
        }
        else if (id == R.id.action_info_copyright)
        {
            Intent intent = new Intent(InfoActivity.this, CopyrightInformationActivity.class);
            startActivity(intent);
            InfoActivity.this.finish();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_page_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent (InfoActivity.this, SettingPageActivity.class);
            startActivity(intent);
            InfoActivity.this.finish();
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
        super.onStop();
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }

}
