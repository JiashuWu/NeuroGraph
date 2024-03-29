package com.example.jiashuwu.neurograph;

import android.content.ContentValues;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.prefs.PreferenceChangeListener;

public class ParallelLineTestActivity extends AppCompatActivity {

    private Context context;
    private MySurfaceViewForParallelLineTest viewForParallelLineTest;
    private Button clear_button;
    private Button finish_button;

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

    public int number_of_points;

    public static ArrayList<Float> x_list;
    public static ArrayList<Float> y_list;
    public static ArrayList<Float> pressure_list;
    public static ArrayList<String> timestamp_list;
    public static ArrayList<Float> touch_point_size_list;
    public static ArrayList<Integer> tool_type_list;

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

    private long exitTime;

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

    public void storeDataWorker ()
    {
        x_list = Sharing.x_list;
        y_list = Sharing.y_list;
        pressure_list = Sharing.pressure_list;
        timestamp_list = Sharing.timestamp_list;
        touch_point_size_list = Sharing.touch_point_size_list;
        tool_type_list = Sharing.tool_type_list;

        number_of_points = x_list.size();

        //Log.d("destroy", String.valueOf(x_list.size()));

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("test_starting_time", test_starting_time);
        values.put("test_ending_time", test_ending_time);
        values.put("test_type", test_type);
        values.put("image_type", image_type);
        values.put("interval_duration", interval_duration);
        values.put("number_of_points", number_of_points);
        values.put("painter_width", Sharing.painter_width);
        values.put("language_during_test", Sharing.language);
        if (Sharing.isScale)
        {
            values.put("is_scale_during_test", "large");
        }
        else
        {
            values.put("is_scale_during_test", "normal");
        }
        Log.d("TAG_TEST_INFO", String.valueOf(user_id));
        Log.d("TAG_TEST_INFO", test_starting_time);
        Log.d("TAG_TEST_INFO", test_ending_time);
        Log.d("TAG_TEST_INFO", test_type);
        Log.d("TAG_TEST_INFO", image_type);
        Log.d("TAG_TEST_INFO", String.valueOf(interval_duration));
        database.insert("Test", null, values);

        // We now want to get this test's test_id

        database = databaseHelper.getReadableDatabase();
        String query = "SELECT test_id FROM Test WHERE user_id = ? AND test_starting_time = ? AND test_ending_time = ? AND test_type = ? AND image_type = ? AND interval_duration = ? AND number_of_points = ? ";
        String [] parameters = new String [] {String.valueOf(user_id), test_starting_time, test_ending_time, test_type, image_type, String.valueOf(interval_duration), String.valueOf(number_of_points)};
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
            values.put("point_serial_number", i+1);
            values.put("tool_type", tool_type_list.get(i));
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

        Sharing.stop_showing_process = 0;
        Intent broadcastMessage = new Intent ();
        broadcastMessage.setAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        broadcastMessage.putExtra("stop_showing_process", "1");
        sendBroadcast(broadcastMessage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(ParallelLineTestActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ParallelLineTestActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_parallel_line_test);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getWritableDatabase();

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type");
        image_type = getIntent().getStringExtra("image_type");
        interval_duration = Integer.parseInt(getIntent().getStringExtra("interval_duration").toString());

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

        viewForParallelLineTest = (MySurfaceViewForParallelLineTest) findViewById(R.id.mySurfaceViewForParallelLineTest);

        clear_button = (Button) findViewById(R.id.parallel_line_test_clear_button);
        clear_button.setTextColor(Color.RED);
        clear_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //清除
                viewForParallelLineTest.reset();
            }
        });

        finish_button = (Button) findViewById(R.id.parallel_line_test_finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                test_ending_time = String.valueOf(year) + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s + "." + millisecond_s;

                //test_ending_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

                Intent intent = new Intent(ParallelLineTestActivity.this, ThankYouActivity.class);
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
                Toast.makeText(this, getResources().getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
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
    public void onPause()
    {
        super.onPause();
        Log.d("destroy", "activity_goes_away1");
    }

    @Override
    public void onStop ()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                storeDataWorker();
            }
        }).start();

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