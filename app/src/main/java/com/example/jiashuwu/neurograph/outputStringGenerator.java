package com.example.jiashuwu.neurograph;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiashu Wu on 23/03/2018.
 */

public class outputStringGenerator {

    // TODO
    // THIS FILE IS USED TO GENERATE OUTPUT STRINGS.

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private String databaseName = DatabaseInformation.databaseName;
    private int databaseVersion = DatabaseInformation.databaseVersion;

    private int test_id;
    private int user_id;
    private String name;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;
    private int age;
    private String gender;
    private String education;
    private int rating_score;
    private String current_receive_treatment;
    private String timestamp_of_point;
    private float x;
    private float y;
    private float pressure;
    private float touch_point_size;


    public String generate_string_from_database (Context context)
    {
        databaseHelper = new MyDatabaseHelper (context, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        String output_string = "";

        String query = "SELECT * FROM Test";
        String query1 = "";
        String query2 = "";
        String [] parameter = new String [] {};
        String [] parameter1;
        String [] parameter2;

        Cursor cursor = database.rawQuery(query, parameter);
        Cursor cursor1;
        Cursor cursor2;
        while (cursor.moveToNext())
        {
            test_id = cursor.getInt(0);
            user_id = cursor.getInt(1);
            test_starting_time = cursor.getString(2).toString();
            test_ending_time = cursor.getString(3).toString();
            test_type = cursor.getString(4).toString();
            image_type = cursor.getString(5).toString();
            interval_duration = Integer.parseInt(cursor.getString(6).toString());
            query1 = "SELECT * FROM User WHERE user_id = ?";
            parameter1 = new String [] {String.valueOf(user_id)};
            cursor1 = database.rawQuery(query1, parameter1);
            while (cursor1.moveToNext())
            {
                name = cursor1.getString(1).toString();
                age = Integer.parseInt(cursor1.getString(2).toString());
                gender = cursor1.getString(3).toString();
                education = cursor1.getString(4).toString();
                rating_score = Integer.parseInt(cursor1.getString(5).toString());
                current_receive_treatment = cursor1.getString(6).toString();
            }
            if (cursor1 != null)
            {
                cursor1.close();
            }
            output_string = output_string + "test_id = " + String.valueOf(test_id) + "\n";
            output_string = output_string + "user_id = " + String.valueOf(user_id) + "\n";
            output_string = output_string + "test_starting_time = " + test_starting_time + "\n";
            output_string = output_string + "test_ending_time = " + test_ending_time + "\n";
            output_string = output_string + "test_type = " + test_type + "\n";
            output_string = output_string + "image_type = " + image_type + "\n";
            output_string = output_string + "interval duration = " + String.valueOf(interval_duration) + "\n";
            output_string = output_string + "user name = " + name + "\n";
            output_string = output_string + "age = " + String.valueOf(age) + "\n";
            output_string = output_string + "gender = " + gender + "\n";
            output_string = output_string + "education = " + education + "\n";
            output_string = output_string + "rating score = " + String.valueOf(rating_score) + "\n";
            output_string = output_string + "current receive treatment" + current_receive_treatment + "\n";
            query2 = "SELECT * FROM Data WHERE test_id = ?";
            parameter2 = new String [] {String.valueOf(test_id)};
            cursor2 = database.rawQuery(query2, parameter2);
            while (cursor2.moveToNext())
            {
                timestamp_of_point = cursor.getString(2).toString();
                x = cursor.getFloat(3);
                y = cursor.getFloat(4);
                pressure = cursor.getFloat(5);
                touch_point_size = cursor.getFloat(6);
                String new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + " " + String.valueOf(touch_point_size) + "\n";
                output_string = output_string + new_line;
            }
            if (cursor2 != null)
            {
                cursor2.close();
            }

        }

        if (cursor != null)
        {
            cursor.close();
        }
        if (database != null)
        {
            database.close();
        }
        if (databaseHelper != null)
        {
            databaseHelper.close();
        }
        return output_string;
    }

}
