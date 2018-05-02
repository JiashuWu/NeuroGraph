package com.example.jiashuwu.neurograph;

import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class AccountCentreActivity extends AppCompatActivity {

    private Button button_register;
    private Button button_continue;
    private EditText registration_code_edittext;

    private boolean can_continue = true;
    private String registration_code = "";

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private int count = 0;

    private int user_id = 0;

    private Cursor cursor;

    // This method is used to choose colour theme
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

    // This method is used to switch language
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

    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(AccountCentreActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(AccountCentreActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_centre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_register = (Button) findViewById(R.id.account_centre_register_button);
        button_continue = (Button) findViewById(R.id.account_centre_continue_button);
        registration_code_edittext = (EditText) findViewById(R.id.account_centre_edittext);

        databaseHelper = new MyDatabaseHelper(this, DatabaseInformation.databaseName, null, DatabaseInformation.databaseVersion);
        database = databaseHelper.getReadableDatabase();


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AccountCentreActivity.this, InformationCollectionActivity.class);
                startActivity(intent);
                AccountCentreActivity.this.finish();
            }
        });

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                can_continue = true;
                registration_code = registration_code_edittext.getText().toString();
                count = 0;
                String query = "SELECT registration_code FROM User";
                String parameters [] = new String[] {};
                cursor = database.rawQuery(query, parameters);
                while (cursor.moveToNext())
                {
                    if (registration_code.equals(cursor.getString(0).toString()))
                    {
                        count = count + 1;
                    }
                }

                if (count == 1)
                {
                    // get the user ID
                    query = "SELECT user_id FROM User WHERE registration_code = ?";
                    parameters = new String[] {registration_code};
                    databaseHelper = new MyDatabaseHelper(AccountCentreActivity.this, DatabaseInformation.databaseName, null, DatabaseInformation.databaseVersion);
                    database = databaseHelper.getReadableDatabase();
                    cursor = database.rawQuery(query, parameters);
                    while(cursor.moveToNext())
                    {
                        user_id = cursor.getInt(0);
                    }
                    Sharing.current_user_id = user_id;
                    Intent intent = new Intent (AccountCentreActivity.this, TestSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    startActivity(intent);
                    AccountCentreActivity.this.finish();

                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccountCentreActivity.this);
                    builder.setTitle(R.string.registration_code_invalid);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.regist_first);
                    builder.setPositiveButton(R.string.got_it, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Should do nothing here;
                            // Blank
                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
        });
    }

    // Close the database connection in the onStop method
    @Override
    public void onStop ()
    {
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
        super.onStop();;
    }

    // Override the onKeyDown method, go back to SettingPageActivity;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent (AccountCentreActivity.this, SettingPageActivity.class);
            startActivity(intent);
            AccountCentreActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
