package com.example.jiashuwu.neurograph;

import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                    if (registration_code.equalsIgnoreCase(cursor.getString(0).toString()))
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
                    builder.setTitle("Registration Code Invalid");
                    builder.setCancelable(false);
                    builder.setMessage("Registration code in invalid. You need to register first.");
                    builder.setPositiveButton("Got It", new DialogInterface.OnClickListener() {
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

}
