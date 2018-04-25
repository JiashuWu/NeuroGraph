package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PersonalInformationActivity extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private TextView content_textview;
    private Button button_edit;


    private int user_id;
    private String name;
    private int age;
    private String gender;
    private String education;
    private float rating_score;
    private String current_receiving_treatment;
    private String registration_code;

    private String content = "";


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
            TextScaleUtilsLower.scaleTextSize(PersonalInformationActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(PersonalInformationActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        content_textview = (TextView) findViewById(R.id.personal_information_content_textview);
        button_edit = (Button) findViewById(R.id.personal_information_button_edit);
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

        content = content + getString(R.string.user_id_equal) + String.valueOf(user_id) + "\n";
        content = content + getString(R.string.registration_code_equal) + registration_code + "\n";
        content = content + getString(R.string.name_equal) + name + "\n";
        content = content + getString(R.string.age_equal) + String.valueOf(age) + "\n";
        content = content + getString(R.string.gender_equal) + String.valueOf(gender) + "\n";
        content = content + getString(R.string.education_level_equal) + education + "\n";
        content = content + getString(R.string.rating_score_equal) + String.valueOf(rating_score) + "\n";
        content = content + getString(R.string.current_receiving_treatment_equal) + current_receiving_treatment + "\n";

        content_textview.setText(content);

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

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInformationActivity.this, PersonalInformationEditPageActivity.class);
                startActivity(intent);
                PersonalInformationActivity.this.finish();
            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_information, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            Intent intent = new Intent(PersonalInformationActivity.this, TestSelectionActivity.class);
            intent.putExtra("user_id", String.valueOf(Sharing.current_user_id));
            startActivity(intent);
            PersonalInformationActivity.this.finish();
        }
        else if (id == R.id.action_user_edit)
        {
            Intent intent = new Intent (PersonalInformationActivity.this, PersonalInformationEditPageActivity.class);
            startActivity(intent);
            PersonalInformationActivity.this.finish();
        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent (PersonalInformationActivity.this, TestSelectionActivity.class);
            intent.putExtra("user_id", String.valueOf(Sharing.current_user_id));
            startActivity(intent);
            PersonalInformationActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStart()
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
