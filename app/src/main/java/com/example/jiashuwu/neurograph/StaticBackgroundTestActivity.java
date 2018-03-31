package com.example.jiashuwu.neurograph;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.prefs.PreferenceChangeListener;

public class StaticBackgroundTestActivity extends AppCompatActivity {

    private Context context;
    private MySurfaceViewForStaticBackground viewForStaticBackground;
    private Button clear_button;
    private Button finish_button;
    private Button capture_button;

    public int user_id;
    public String test_type;
    public String image_type;
    public int interval_duration;

    public Calendar calendar;
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;
    public int millisecond;

    public String test_starting_time;
    public String test_ending_time;

    public static ArrayList<Float> x_list;
    public static ArrayList<Float> y_list;
    public static ArrayList<Float> pressure_list;
    public static ArrayList<String> timestamp_list;
    public static ArrayList<Float> touch_point_size_list;

    private MyDatabaseHelper databaseHelper;

    private int databaseVersion = DatabaseInformation.databaseVersion;
    private String databaseName = DatabaseInformation.databaseName;

    public int test_id;

    private int painter_width;

    public String month_s;
    public String day_s;
    public String hour_s;
    public String minute_s;
    public String second_s;
    public String millisecond_s;

    public static Calendar calendar11;
    public static int year11;
    public static int month11;
    public static int day11;
    public static int hour11;
    public static int minute11;
    public static int second11;
    public static int millisecond11;
    public static String month_s11;
    public static String day_s11;
    public static String hour_s11;
    public static String minute_s11;
    public static String second_s11;
    public static String millisecond_s11;

    public String user_name;

    private long exitTime;


    public void getScreenShot ()
    {
        calendar11 = Calendar.getInstance();
        year11 = calendar11.get(Calendar.YEAR);
        month11 = calendar11.get(Calendar.MONTH) + 1;
        day11 = calendar11.get(Calendar.DAY_OF_MONTH);
        hour11 = calendar11.get(Calendar.HOUR_OF_DAY);
        minute11 = calendar11.get(Calendar.MINUTE);
        second11= calendar11.get(Calendar.SECOND);
        millisecond11 = calendar11.get(Calendar.MILLISECOND);

        if (String.valueOf(month11).length() == 1)
        {
            month_s11 = "0" + String.valueOf(month11);
        }
        else
        {
            month_s11 = String.valueOf(month11);
        }
        if (String.valueOf(day11).length() == 1)
        {
            day_s11 = "0" + String.valueOf(day11);
        }
        else
        {
            day_s11 = String.valueOf(day11);
        }
        if (String.valueOf(hour11).length() == 1)
        {
            hour_s11 = "0" + String.valueOf(hour11);
        }
        else
        {
            hour_s11 = String.valueOf(hour11);
        }
        if (String.valueOf(minute11).length() == 1)
        {
            minute_s11 = "0" + String.valueOf(minute11);
        }
        else
        {
            minute_s11 = String.valueOf(minute11);
        }
        if (String.valueOf(second11).length() == 1)
        {
            second_s11 = "0" + String.valueOf(second11);
        }
        else
        {
            second_s11 = String.valueOf(second11);
        }
        if (String.valueOf(millisecond11).length() == 1)
        {
            millisecond_s11 = "00" + String.valueOf(millisecond11);
        }
        else if (String.valueOf(millisecond11).length() == 2)
        {
            millisecond_s11 = "0" + String.valueOf(millisecond11);
        }
        else if (String.valueOf(millisecond11).length() == 3)
        {
            millisecond_s11 = String.valueOf(millisecond11);
        }

        String file_time = String.valueOf(year11) + month_s11 + day_s11 + hour_s11 + minute_s11 + second_s11 + millisecond_s11;

        try
        {
            File file = new File(Environment.getExternalStorageDirectory(), "Neurograph");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String output_file_name = "NeurographScreenShotFile" + "_" + user_name + "_" + test_type + "_" + image_type + "_" + file_time + ".png";
        String file_path = Environment.getExternalStorageDirectory() + "/Neurograph/" + output_file_name;

        try
        {
            View view = getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(file_path);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
            TextScaleUtilsLower.scaleTextSize(StaticBackgroundTestActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(StaticBackgroundTestActivity.this, Sharing.isScale);
        }
        initLocaleLanguage();

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_static_background_test);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getWritableDatabase();

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type");
        image_type = getIntent().getStringExtra("image_type");
        interval_duration = Integer.parseInt(getIntent().getStringExtra("interval_duration").toString());

        /*
        SQLiteDatabase database11 = databaseHelper.getReadableDatabase();
        String query11 = "SELECT name FROM User WHERE user_id = ?";
        String [] parameter11 = new String[] {String.valueOf(user_id)};
        Cursor cursor11 = database11.rawQuery(query11, parameter11);
        while (cursor11.moveToNext())
        {
            user_name = cursor11.getString(0).toString();
        }
        if (cursor11 != null)
        {
            cursor11.close();
        }
        if (database11 != null)
        {
            database11.close();
        }
        */

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);

        if (String.valueOf(month).length() == 1)
        {
            month_s = "0" + String.valueOf(month);
        }
        else
        {
            month_s = String.valueOf(month);
        }
        if (String.valueOf(day).length() == 1)
        {
            day_s = "0" + String.valueOf(day);
        }
        else
        {
            day_s = String.valueOf(day);
        }
        if (String.valueOf(hour).length() == 1)
        {
            hour_s = "0" + String.valueOf(hour);
        }
        else
        {
            hour_s = String.valueOf(hour);
        }
        if (String.valueOf(minute).length() == 1)
        {
            minute_s = "0" + String.valueOf(minute);
        }
        else
        {
            minute_s = String.valueOf(minute);
        }
        if (String.valueOf(second).length() == 1)
        {
            second_s = "0" + String.valueOf(second);
        }
        else
        {
            second_s = String.valueOf(second);
        }
        if (String.valueOf(millisecond).length() == 1)
        {
            millisecond_s = "00" + String.valueOf(millisecond);
        }
        else if (String.valueOf(millisecond).length() == 2)
        {
            millisecond_s = "0" + String.valueOf(millisecond);
        }
        else if (String.valueOf(millisecond).length() == 3)
        {
            millisecond_s = String.valueOf(millisecond);
        }

        test_starting_time = String.valueOf(year) + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s + "." + millisecond_s;

        //test_starting_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

        context = this;

        viewForStaticBackground = (MySurfaceViewForStaticBackground) findViewById(R.id.mySurfaceViewForStaticBackground);

        clear_button = (Button) findViewById(R.id.static_background_test_clear_button);
        clear_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //清除
                viewForStaticBackground.reset();
            }
        });

        /*
        capture_button = (Button) findViewById(R.id.static_background_test_capture_button);
        capture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO IF YOU WANT A SCREENSHOT
                getScreenShot();
            }
        });
        */

        finish_button = (Button) findViewById(R.id.static_background_test_finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO IF YOU WANT A SCREENSHOT
                // getScreenShot((View) getWindow().getDecorView());
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                millisecond = calendar.get(Calendar.MILLISECOND);

                if (String.valueOf(month).length() == 1) {
                    month_s = "0" + String.valueOf(month);
                } else {
                    month_s = String.valueOf(month);
                }
                if (String.valueOf(day).length() == 1) {
                    day_s = "0" + String.valueOf(day);
                } else {
                    day_s = String.valueOf(day);
                }
                if (String.valueOf(hour).length() == 1) {
                    hour_s = "0" + String.valueOf(hour);
                } else {
                    hour_s = String.valueOf(hour);
                }
                if (String.valueOf(minute).length() == 1) {
                    minute_s = "0" + String.valueOf(minute);
                } else {
                    minute_s = String.valueOf(minute);
                }
                if (String.valueOf(second).length() == 1) {
                    second_s = "0" + String.valueOf(second);
                } else {
                    second_s = String.valueOf(second);
                }
                if (String.valueOf(millisecond).length() == 1) {
                    millisecond_s = "00" + String.valueOf(millisecond);
                } else if (String.valueOf(millisecond).length() == 2) {
                    millisecond_s = "0" + String.valueOf(millisecond);
                } else if (String.valueOf(millisecond).length() == 3) {
                    millisecond_s = String.valueOf(millisecond);
                }

                test_ending_time = String.valueOf(year) + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s + "." + millisecond_s;

                //test_ending_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);


                Intent intent = new Intent(StaticBackgroundTestActivity.this, ThankYouActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("destroy", "activity_goes_away1");
    }

    @Override
    public void onStop ()
    {
        x_list = Sharing.x_list;
        y_list = Sharing.y_list;
        pressure_list = Sharing.pressure_list;
        timestamp_list = Sharing.timestamp_list;
        touch_point_size_list = Sharing.touch_point_size_list;

        //Log.d("destroy", String.valueOf(x_list.size()));

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("test_starting_time", test_starting_time);
        values.put("test_ending_time", test_ending_time);
        values.put("test_type", test_type);
        values.put("image_type", image_type);
        values.put("interval_duration", interval_duration);
        Log.d("TAG_TEST_INFO", String.valueOf(user_id));
        Log.d("TAG_TEST_INFO", test_starting_time);
        Log.d("TAG_TEST_INFO", test_ending_time);
        Log.d("TAG_TEST_INFO", test_type);
        Log.d("TAG_TEST_INFO", image_type);
        Log.d("TAG_TEST_INFO", String.valueOf(interval_duration));
        database.insert("Test", null, values);

        // We now want to get this test's test_id

        database = databaseHelper.getReadableDatabase();
        String query = "SELECT test_id FROM Test WHERE user_id = ? AND test_starting_time = ? AND test_ending_time = ? AND test_type = ? AND image_type = ? AND interval_duration = ?";
        String [] parameters = new String [] {String.valueOf(user_id), test_starting_time, test_ending_time, test_type, image_type, String.valueOf(interval_duration)};
        Cursor cursor = database.rawQuery(query, parameters);
        while (cursor.moveToNext())
        {
            test_id = Integer.parseInt(cursor.getString(0).toString());
        }
        Log.d("TAG_TEST_INFO", String.valueOf(user_id));

        database = databaseHelper.getWritableDatabase();
        int i = 0;
        values = new ContentValues();
        for (i = 0 ; i < x_list.size() ; i++)
        {

            values.put("test_id", test_id);
            values.put("timestamp_of_point", timestamp_list.get(i));
            values.put("x", x_list.get(i));
            values.put("y", y_list.get(i));
            values.put("pressure", pressure_list.get(i));
            values.put("touch_point_size", touch_point_size_list.get(i));
            Log.d("TAG_DATA_INFO", String.valueOf(test_id));
            Log.d("TAG_DATA_INFO", timestamp_list.get(i));
            Log.d("TAG_DATA_INFO", String.valueOf(x_list.get(i)));
            Log.d("TAG_DATA_INFO", String.valueOf(y_list.get(i)));
            Log.d("TAG_DATA_INFO", String.valueOf(pressure_list.get(i)));
            Log.d("TAG_DATA_INFO", String.valueOf(touch_point_size_list.get(i)));
            database.insert("Data", null, values);
        }


        if (cursor != null)
        {
            cursor.close();
        }
        if (values != null)
        {
            values.clear();
        }
        if (database != null)
        {
            database.close();
        }
        if (databaseHelper != null)
        {
            databaseHelper.close();
        }

        super.onStop();
        Log.d("destroy", "activity_goes_away2");
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        Log.d("destroy", "activity_goes_away3");
    }
}