package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.logging.SocketHandler;

public class TestDetailScrollingActivity extends AppCompatActivity {

    private TextView test_detail_textview;
    private TextView test_detail_title_textview;
    private TextView test_detail_info_textview;
    private TextView test_detail_buttom_textview;

    private int entries_per_page = 100;


    private int test_id;
    private String name;
    private int user_id;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;

    private MyDatabaseHelper databaseHelper;

    private String databaseName = DatabaseInformation.databaseName;
    private int databaseVersion = DatabaseInformation.databaseVersion;

    private SQLiteDatabase database;

    private String test_detail = "";

    private float x;
    private float y;
    private float pressure;
    private String timestamp_of_point;
    private float touch_point_size;

    private String textview_content = "";

    private int entry_begin = 0;
    private int entry_end = 0;

    private int i = 0;
    private int j = 0;

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
            TextScaleUtilsLower.scaleTextSize(TestDetailScrollingActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(TestDetailScrollingActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        entries_per_page = Sharing.entries_per_page;

        test_detail_textview = (TextView) findViewById(R.id.test_detail_textview);
        test_detail_title_textview = (TextView) findViewById(R.id.test_detail_title_textview);
        test_detail_info_textview = (TextView) findViewById(R.id.test_detail_info_title);
        test_detail_buttom_textview = (TextView) findViewById(R.id.test_detail_title_buttom_textview);

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


        textview_content = "";
        test_detail_info_textview.setText(Sharing.test_detail_title);

        if (Sharing.test_detail_arraylist.size() <= entries_per_page)
        {
            entry_begin = 0;
            entry_end = Sharing.test_detail_arraylist.size();
            test_detail_title_textview.setText(getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            for (i = entry_begin ; i < entry_end ; i++)
            {
                textview_content = textview_content + Sharing.test_detail_arraylist.get(i) + "\n";
            }
        }
        else if (Sharing.test_detail_arraylist.size() > entries_per_page)
        {
            entry_begin = 0;
            entry_end = entries_per_page;
            test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            for (i = entry_begin ; i < entry_end ; i++)
            {
                textview_content = textview_content + Sharing.test_detail_arraylist.get(i) + "\n";
            }
        }
        if (Sharing.test_detail_arraylist.size() == 0)
        {
            entry_begin = 0;
            entry_end = 0;
            test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
        }
        test_detail_textview.setText(textview_content);

        /*
        test_detail =
                "test_id = " + String.valueOf(test_id) + "\n"
                + "name = " + name + "\n"
                + "user_id = " + String.valueOf(user_id) + "\n"
                + "test_starting_time = " + test_starting_time + "\n"
                + "test_ending_time = " + test_ending_time + "\n"
                + "test type = " + test_type + "\n"
                + "image type = " + image_type + "\n"
                + "interval duration = " + String.valueOf(interval_duration) + "\n";

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        String query = "SELECT * FROM Data WHERE test_id = ?";
        String [] paramaters = new String[] {String.valueOf(test_id)};

        database = databaseHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, paramaters);
        String new_line = "";
        while (cursor.moveToNext())
        {
            timestamp_of_point = cursor.getString(2).toString();
            x = cursor.getFloat(3);
            y = cursor.getFloat(4);
            pressure = cursor.getFloat(5);
            touch_point_size = cursor.getFloat(6);
            Log.d("TOUCH_SIZE", String.valueOf(touch_point_size));
            new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + " " + String.valueOf(touch_point_size) + "\n";
            test_detail = test_detail + new_line;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        test_detail_textview.setText(test_detail);

        if (database != null)
        {
            database.close();
        }
        if (databaseHelper != null)
        {
            databaseHelper.close();
        }
        */




    }


    // Control the toolbar, switch data detail pages;
    @Override
    public boolean onOptionsItemSelected (MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if (id == android.R.id.home)
        {
            Intent intent = new Intent (TestDetailScrollingActivity.this, DataListActivity.class);
            startActivity(intent);
            TestDetailScrollingActivity.this.finish();
        }
        else if (id == R.id.action_test_detail_previous)
        {
            // TODO SHOW THE PREVIOUS entries_per_page ENTRIES IF AVAILABLE
            if (entry_begin - entries_per_page < 0)
            {
                Toast.makeText(TestDetailScrollingActivity.this, R.string.no_previous_entries, Toast.LENGTH_SHORT).show();
            }
            else
            {
                entry_begin = entry_begin - entries_per_page;
                entry_end = entry_begin + entries_per_page;
                textview_content = "";
                test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                for (i = entry_begin ; i < entry_end ; i++)
                {
                    textview_content = textview_content + Sharing.test_detail_arraylist.get(i);
                }
                test_detail_textview.setText(textview_content);
            }
            if (Sharing.test_detail_arraylist.size() == 0)
            {
                entry_begin = 0;
                entry_end = 0;
                test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            }
        }
        else if (id == R.id.action_test_detail_next)
        {
            // TODO SHOW THE NEXT entries_per_page ENTRIES IF AVAILABLE
            if (entry_begin + entries_per_page > Sharing.test_detail_arraylist.size())
            {
                Toast.makeText(TestDetailScrollingActivity.this, R.string.no_next_entries, Toast.LENGTH_SHORT).show();
            }
            else
            {
                entry_begin = entry_begin + entries_per_page;
                entry_end = entry_end + entries_per_page;
                if (entry_end > Sharing.test_detail_arraylist.size())
                {
                    entry_end = Sharing.test_detail_arraylist.size();
                }
                test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin+1) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                textview_content = "";
                for (i = entry_begin ; i < entry_end ; i++)
                {
                    textview_content = textview_content + Sharing.test_detail_arraylist.get(i);
                }
                test_detail_textview.setText(textview_content);
            }
            if (Sharing.test_detail_arraylist.size() == 0)
            {
                entry_begin = 0;
                entry_end = 0;
                test_detail_title_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
                test_detail_buttom_textview.setText(getResources().getString(R.string.display) + String.valueOf(entry_begin) + " - " + String.valueOf(entry_end) + " " + getResources().getString(R.string.total) + String.valueOf(Sharing.test_detail_arraylist.size()));
            }
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_detail_scrolling_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {

            Intent intent = new Intent(TestDetailScrollingActivity.this, DataListActivity.class);
            startActivity(intent);
            TestDetailScrollingActivity.this.finish();

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
