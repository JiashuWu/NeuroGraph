package com.example.jiashuwu.neurograph;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class TestDetailScrollingActivity extends AppCompatActivity {

    private TextView test_detail_textview;

    private int test_id;
    private String name;
    private int user_id;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;

    private MyDatabaseHelper databaseHelper;

    private String databaseName = "information.db";
    private int databaseVersion = 1;

    private SQLiteDatabase database;

    private String test_detail = "";

    private float x;
    private float y;
    private float pressure;
    private String timestamp_of_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        test_detail_textview = (TextView) findViewById(R.id.test_detail_textview);

        test_id = Integer.parseInt(getIntent().getStringExtra("test_id").toString());
        name = getIntent().getStringExtra("name").toString();
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_starting_time = getIntent().getStringExtra("test_starting_time").toString();
        test_ending_time = getIntent().getStringExtra("test_ending_time").toString();
        test_type = getIntent().getStringExtra("test_type").toString();
        image_type = getIntent().getStringExtra("image_type").toString();
        interval_duration = Integer.parseInt(getIntent().getStringExtra("interval_duration").toString());

        test_detail =
                "test_id = " + String.valueOf(test_id) + "\n"
                + "name = " + name + "n"
                + "user_id = " + String.valueOf(user_id) + "\n"
                + "test_starting_time = " + test_starting_time + "\n"
                + "test_ending_time = " + test_ending_time + "\n"
                + "test type = " + test_type + "\n"
                + "image type = " + image_type + "\n"
                + "interval duration" + String.valueOf(interval_duration) + "\n";

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        String query = "SELECT * FROM Data WHERE test_id = ?";
        String [] paramaters = new String[] {String.valueOf(test_id)};

        database = databaseHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, paramaters);
        String new_line = "";
        while (cursor.moveToNext())
        {
            timestamp_of_point = cursor.getString(2).toString();
            x = cursor.getFloat(3);
            y = cursor.getFloat(4);
            pressure = cursor.getFloat(5);
            new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + "\n";
            test_detail = test_detail + new_line;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        test_detail_textview.setText(test_detail);




    }
}
