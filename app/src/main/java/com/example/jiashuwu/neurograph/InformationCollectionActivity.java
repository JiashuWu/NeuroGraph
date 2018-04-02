package com.example.jiashuwu.neurograph;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class InformationCollectionActivity extends AppCompatActivity {

    private EditText name_edittext;
    private EditText age_edittext;
    private Spinner gender_spinner;
    private Spinner education_spinner;
    private EditText ratingscore_edittext;
    private RadioGroup radioGroup;
    private Button next_button;

    private String name = "";
    private String age = "";
    private String gender = "";
    private String education = "";
    private String ratingscore = "";
    private String current_receiving_treatment = "";

    private ArrayAdapter gender_adapter;
    private ArrayAdapter education_adapter;

    private MyDatabaseHelper databaseHelper;

    private int user_id;

    private int databaseVersion = DatabaseInformation.databaseVersion;
    private String databaseName = DatabaseInformation.databaseName;

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







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(InformationCollectionActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(InformationCollectionActivity.this, Sharing.isScale);
        }
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getWritableDatabase();

        name_edittext = (EditText) findViewById(R.id.information_collection_name_edittext);
        age_edittext = (EditText) findViewById(R.id.information_collection_age_edittext);
        gender_spinner = (Spinner) findViewById(R.id.information_collection_gender_spinner);
        education_spinner = (Spinner) findViewById(R.id.information_collection_education_spinner);
        ratingscore_edittext = (EditText) findViewById(R.id.information_collection_ratingscore_edittext);
        radioGroup = (RadioGroup) findViewById(R.id.information_collection_radiogroup);
        next_button = (Button) findViewById(R.id.information_collection_next_button);


        final String [] gender_list = getResources().getStringArray(R.array.gender_list);
        gender_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, gender_list);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(gender_adapter);

        final String [] education_list = getResources().getStringArray(R.array.education_level);
        education_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, education_list);
        education_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education_spinner.setAdapter(education_adapter);

        gender_spinner.setSelection(0);

        education_spinner.setSelection(0);

        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: gender = "Male";break;
                    case 1: gender = "Female";break;
                    case 2: gender = "Others";break;
                }
                Log.d("gender", gender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        education_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: education = "Primary School or below";break;
                    case 1: education = "High School";break;
                    case 2: education = "Undergraduate";break;
                    case 3: education = "Postgraduate";break;
                    case 4: education = "Doctor or higher";break;
                }
                Log.d("education", education);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean can_continue = true;
                name = name_edittext.getText().toString();
                if (name.equalsIgnoreCase(""))
                {
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_empty));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.name_empty));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                age = age_edittext.getText().toString();
                if (can_continue && age.equalsIgnoreCase(""))
                {
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_empty));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.age_empty));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                int i = 0;
                int count = 0;
                for (i = 0 ; i < age.length() ; i++)
                {
                    if (!Character.isDigit(age.charAt(i)) || age.contains("."))
                    {
                        count = count + 1;
                    }
                }
                if (can_continue && count != 0)
                {
                    count = 0;
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_invalid));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.age_invalid));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                if (can_continue && (Integer.parseInt(age) < 0 || Integer.parseInt(age) > 170))
                {
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_invalid));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.age_invalid));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                ratingscore = ratingscore_edittext.getText().toString();
                // RatingScore can now be empty
                /*
                if (can_continue && ratingscore.equalsIgnoreCase(""))
                {
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_invalid));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.rating_score_empty));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                */
                count = 0;
                for (i = 0 ; i < ratingscore.length() ; i++)
                {
                    if ((!Character.isDigit(ratingscore.charAt(i)) && ratingscore.charAt(i) != '.') || (ratingscore.charAt(i) == '.' && i == ratingscore.length() - 1))
                    {
                        count = count + 1;
                    }
                }
                if (can_continue && count != 0)
                {
                    count = 0;
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_invalid));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.rating_score_invalid));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (can_continue && findViewById(selectedId) == null)
                {
                    can_continue = false;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InformationCollectionActivity.this);
                    dialog.setTitle(getString(R.string.information_empty));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.treatment_empty));
                    dialog.setPositiveButton(getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Should do nothing here;
                        }
                    });
                    dialog.show();
                }
                if (can_continue && findViewById(selectedId) != null)
                {
                    final RadioButton radioSelectedButton = (RadioButton)findViewById(selectedId);
                    Log.d("RADIO_SELECTED", String.valueOf(selectedId));
                    if (selectedId == R.id.information_collection_radio_yes)
                    {
                        Log.d("RADIO_SELECTED", "YES");
                        current_receiving_treatment = "yes";
                    }
                    if (selectedId == R.id.information_collection_radio_no)
                    {
                        Log.d("RADIO_SELECTED", "NO");
                        current_receiving_treatment = "no";
                    }
                    //current_receiving_treatment = radioSelectedButton.getText().toString();
                }
                if (can_continue)
                {
                    if (ratingscore.equalsIgnoreCase(""))
                    {
                        ratingscore = "0";
                    }
                    // If can_continue is true, then we need to store the user's information into the database.
                    SQLiteDatabase database = databaseHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", name);
                    values.put("age", Integer.parseInt(age));
                    values.put("gender", gender);
                    values.put("education", education);
                    values.put("rating_score", Double.parseDouble(ratingscore));
                    values.put("current_receiving_treatment", current_receiving_treatment);
                    Log.d("TAG_USER_INFO", name);
                    Log.d("TAG_USER_INFO", age);
                    Log.d("TAG_USER_INFO", gender);
                    Log.d("TAG_USER_INFO", education);
                    Log.d("TAG_USER_INFO", ratingscore);
                    Log.d("TAG_USER_INFO", current_receiving_treatment);
                    database.insert("User", null, values);
                    // We now want to get this user's user_id
                    database = databaseHelper.getReadableDatabase();
                    String query = "SELECT user_id FROM User WHERE name = ? AND age = ? AND gender = ? AND education = ? AND rating_score = ? AND current_receiving_treatment = ?";
                    String [] parameters = new String [] {name, age, gender, education, ratingscore, current_receiving_treatment};
                    Cursor cursor = database.rawQuery(query, parameters);
                    while (cursor.moveToNext())
                    {
                        user_id = Integer.parseInt(cursor.getString(0).toString());
                    }
                    Log.d("TAG_USER_INFO", String.valueOf(user_id));
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

                    Intent intent = new Intent(InformationCollectionActivity.this, TestSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    startActivity(intent);
                    InformationCollectionActivity.this.finish();
                }
            }
        });




    }


    // TODO OPTIONAL 4/4

    /*

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

    */

}
