package com.example.jiashuwu.neurograph;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.PreferenceChangeListener;

public class DynamicBlankBackgroundTestActivity extends AppCompatActivity {

    private Context context;
    private MySurfaceViewForDynamicBlankBackground viewForDynamicBlankBackground;
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

    public static ArrayList<Float> x_list;
    public static ArrayList<Float> y_list;
    public static ArrayList<Float> pressure_list;
    public static ArrayList<String> timestamp_list;

    private MyDatabaseHelper databaseHelper;

    private int databaseVersion = 1;
    private String databaseName = "information.db";

    public int test_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_dynamic_blank_background_test);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getWritableDatabase();

        user_id = getIntent().getIntExtra("user_id", -100000);
        test_type = getIntent().getStringExtra("test_type");
        image_type = getIntent().getStringExtra("image_type");
        interval_duration = getIntent().getIntExtra("interval_duration", -100000);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);

        test_starting_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

        context = this;

        viewForDynamicBlankBackground = (MySurfaceViewForDynamicBlankBackground) findViewById(R.id.mySurfaceViewForDynamicBlankBackground);

        clear_button = (Button) findViewById(R.id.dynamic_blank_background_test_clear_button);
        clear_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //清除
                viewForDynamicBlankBackground.reset();
            }
        });

        finish_button = (Button) findViewById(R.id.dynamic_blank_background_test_finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                millisecond = calendar.get(Calendar.MILLISECOND);

                test_ending_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

                Intent intent = new Intent(DynamicBlankBackgroundTestActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
            Log.d("TAG_DATA_INFO", String.valueOf(test_id));
            Log.d("TAG_DATA_INFO", timestamp_list.get(i));
            Log.d("TAG_DATA_INFO", String.valueOf(x_list.get(i)));
            Log.d("TAG_DATA_INFO", String.valueOf(y_list.get(i)));
            Log.d("TAG_DATA_INFO", String.valueOf(pressure_list.get(i)));
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