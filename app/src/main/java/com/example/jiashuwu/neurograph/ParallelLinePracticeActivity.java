package com.example.jiashuwu.neurograph;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ParallelLinePracticeActivity extends AppCompatActivity {

    private Context context;
    private MySurfaceViewForParallelLinePractice viewForParallelLinePractice;
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
    public String month_s;
    public String day_s;
    public String hour_s;
    public String minute_s;
    public String second_s;
    public String millisecond_s;

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

    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(ParallelLinePracticeActivity.this, Sharing.isScale);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_parallel_line_practice);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getWritableDatabase();

        user_id = 0;
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type");

        image_type = "parallel_line";
        interval_duration = 0;

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

        context = this;

        viewForParallelLinePractice = (MySurfaceViewForParallelLinePractice) findViewById(R.id.mySurfaceViewForParallelLinePractice);

        clear_button = (Button) findViewById(R.id.parallel_line_practice_clear_button);
        clear_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //清除
                viewForParallelLinePractice.reset();
            }
        });

        finish_button = (Button) findViewById(R.id.parallel_line_practice_finish_button);
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

                Intent intent = new Intent(ParallelLinePracticeActivity.this, ThankYouParallelActivity.class);
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
    public void onStop()
    {
        x_list = Sharing.x_list;
        y_list = Sharing.y_list;
        pressure_list = Sharing.pressure_list;
        timestamp_list = Sharing.timestamp_list;
        touch_point_size_list = Sharing.touch_point_size_list;

        Log.d("NULL_DETECTOR", String.valueOf(x_list == null));

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
        Log.d("destroy", "activity_goes_away1");
    }


    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        Log.d("destroy", "activity_goes_away3");
    }
}