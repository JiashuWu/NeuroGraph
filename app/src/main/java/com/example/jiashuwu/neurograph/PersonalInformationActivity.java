package com.example.jiashuwu.neurograph;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PersonalInformationActivity extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private TextView content_textview;

    private int user_id;
    private String name;
    private int age;
    private String gender;
    private String education;
    private float rating_score;
    private String current_receiving_treatment;
    private String registration_code;

    private String content = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        content_textview = (TextView) findViewById(R.id.personal_information_content_textview);
        databaseHelper = new MyDatabaseHelper(this, DatabaseInformation.databaseName, null, DatabaseInformation.databaseVersion);
        database = databaseHelper.getReadableDatabase();

        String query = "SELECT * FROM User WHERE user_id = ? ";
        String parameters [] = new String[] {String.valueOf(Sharing.current_user_id)};

        cursor = database.rawQuery(query, parameters);
        while (cursor.moveToNext())
        {
            user_id = cursor.getInt(0);
            name = cursor.getString(1).toString();
            age = cursor.getInt(2);
            gender = cursor.getString(3).toString();
            education = cursor.getString(4).toString();
            rating_score = cursor.getFloat(5);
            current_receiving_treatment = cursor.getString(6).toString();
            registration_code = cursor.getString(7).toString();
        }

        content = content + "User ID = " + String.valueOf(user_id) + "\n";
        content = content + "Registration Code = " + registration_code + "\n";
        content = content + "Name = " + name + "\n";
        content = content + "Age = " + String.valueOf(age) + "\n";
        content = content + "Gender = " + String.valueOf(gender) + "\n";
        content = content + "Education Level = " + education + "\n";
        content = content + "Rating Score = " + String.valueOf(rating_score) + "\n";
        content = content + "Current Receiving Treatment = " + current_receiving_treatment + "\n";

        content_textview.setText(content);









    }

}
