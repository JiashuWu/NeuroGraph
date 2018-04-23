package com.example.jiashuwu.neurograph;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jiashu Wu on 17/03/2018.
 */



public class MyDatabaseHelper extends SQLiteOpenHelper {

    private int databaseVersion = DatabaseInformation.databaseVersion;
    private String databaseName = DatabaseInformation.databaseName;

    public MyDatabaseHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    //User Table
    //user_id, name, age, gender, education, rating_score, current_receiving_treatment
    public static final String CREATE_USER_TABLE = "CREATE TABLE User ("
            + "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name VARCHAR(600) NOT NULL, "
            + "age INTEGER NOT NULL, "
            + "gender VARCHAR(10) NOT NULL, "
            + "education VARCHAR(100) NOT NULL, "
            + "rating_score REAL, "
            + "current_receiving_treatment VARCHAR(100), "
            + "registration_code VARCHAR(100) "
            + " ) ";

    //Test Table
    //test_id, user_id, test_starting_time, test_ending_time, test_type, image_type, interval_duration, number_of_points, painter_width, language_during_test, is_scale_during_test
    public static final String CREATE_TEST_TABLE = "CREATE TABLE Test ("
            + "test_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER REFERENCES User (user_id) ON UPDATE CASCADE ON DELETE CASCADE, "
            + "test_starting_time TEXT NOT NULL, "
            + "test_ending_time TEXT NOT NULL, "
            + "test_type TEXT NOT NULL, "
            + "image_type TEXT NOT NULL, "
            + "interval_duration INTEGER, "
            + "number_of_points INTEGER, "
            + "painter_width INTEGER, "
            + "language_during_test TEXT, "
            + "is_scale_during_test TEXT "
            + " ) ";


    // Date Table
    // data_id, test_id, timestamp_of_point, x, y, pressure, touch_point_size, point_serial_number, tool_type
    public static final String CREATE_DATA_TABLE = "CREATE TABLE Data ("
            + "data_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "test_id INTEGER REFERENCES Test (test_id) ON UPDATE CASCADE ON DELETE CASCADE, "
            + "timestamp_of_point TEXT NOT NULL, "
            + "x REAL NOT NULL, "
            + "y REAL NOT NULL, "
            + "pressure REAL, "
            + "touch_point_size REAL, "
            + "point_serial_number INTEGER, "
            + "tool_type INTEGER "
            + " ) ";


    @Override
    public void onCreate (SQLiteDatabase db)
    {
        //db.execSQL("DROP TABLE IF EXISTS User");
        //db.execSQL("DROP TABLE IF EXISTS Item");

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Test");
        db.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(db);
    }

    public static void upgradeDatabase (SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Test");
        db.execSQL("DROP TABLE IF EXISTS Data");
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        db.execSQL(CREATE_DATA_TABLE);
    }


}
