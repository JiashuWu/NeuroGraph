package com.example.jiashuwu.neurograph;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import static android.Manifest.permission.ACCESS_NOTIFICATION_POLICY;

public class SettingPageActivity extends AppCompatActivity {

    private String language = "";
    private String fontsize = "";

    private Spinner setting_page_language_spinner;
    private Spinner setting_page_fontsize_spinner;

    private Button setting_page_start_button;
    private Button setting_page_data_list_button;

    private ArrayAdapter language_adapter;
    private ArrayAdapter font_adapter;

    private boolean initial_isScale;

    private String initial_language;

    private long exitTime;

    public void initLocaleLanguage ()
    {
        Resources resource = getApplicationContext().getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        switch (Sharing.language)
        {
            case "English (EN-GB)":configuration.locale = Locale.UK;break;
            case "Simplified Chinese (CH-ZN)":configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese (CH-TW)":configuration.locale = Locale.TAIWAN;break;
            case "Japanese (JA-JP)":configuration.locale = Locale.JAPAN;break;
            case "French (FR-FA)":configuration.locale = Locale.FRANCE;break;

            case "English (EN_GB)":configuration.locale = Locale.UK;break;
            case "Simplified Chinese (CH_ZN)":configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese (CH_TW)":configuration.locale = Locale.TAIWAN;break;
            case "Japanese (JA_JP)":configuration.locale = Locale.JAPAN;break;
            case "French (FR_FA)":configuration.locale = Locale.FRANCE;break;

            default:configuration.locale = Locale.getDefault();break;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(SettingPageActivity.this, Sharing.isScale);
        initLocaleLanguage();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_page);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(SettingPageActivity.this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, 200);
        }
        else
        {
            Log.d("GRANTED", "GRANTED");
        }

        initial_isScale = Sharing.isScale;
        initial_language = Sharing.language;

        setting_page_language_spinner = (Spinner) findViewById(R.id.setting_page_language_spinner);
        setting_page_fontsize_spinner = (Spinner) findViewById(R.id.setting_page_font_size_spinner);

        setting_page_start_button = (Button) findViewById(R.id.setting_page_start_button);
        setting_page_data_list_button = (Button) findViewById(R.id.setting_page_data_list_button);

        final String [] language_list = getResources().getStringArray(R.array.language_array);
        language_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, language_list);
        language_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_language_spinner.setAdapter(language_adapter);

        final String [] fontsize_list = getResources().getStringArray(R.array.font_array);
        font_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontsize_list);
        font_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_fontsize_spinner.setAdapter(font_adapter);

        //setting_page_language_spinner.setSelection(0);

        switch (Sharing.language)
        {
            case "English (EN_GB)": setting_page_language_spinner.setSelection(0); break;
            case "Simplified Chinese (CH_ZN)": setting_page_language_spinner.setSelection(1); break;
            case "Traditional Chinese (CH_TW)": setting_page_language_spinner.setSelection(2); break;
        }

        setting_page_fontsize_spinner.setSelection(0);

        setting_page_language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: language = "English (EN_GB)";Sharing.language = "English (EN_GB)";break;
                    case 1: language = "Simplified Chinese (CH_ZN)";Sharing.language = "Simplified Chinese (CH_ZN)";break;
                    case 2: language = "Traditional Chinese (CH_TW)";Sharing.language = "Traditional Chinese (CH_TW)";break;
                    default: language = Locale.getDefault().getLanguage();Sharing.language = Locale.getDefault().getLanguage().toString();break;
                }
                Log.d("language", language);
                if (!Sharing.language.equalsIgnoreCase(initial_language))
                {
                    Intent intent = new Intent (SettingPageActivity.this, TransferActivity.class);
                    startActivity(intent);
                    SettingPageActivity.this.finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (!Sharing.isScale)
        {
            setting_page_fontsize_spinner.setSelection(0);
        }
        else
        {
            setting_page_fontsize_spinner.setSelection(1);
        }

        setting_page_fontsize_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: fontsize = "Normal";Sharing.isScale = false;break;
                    case 1: fontsize = "Large";Sharing.isScale = true;break;
                    default: fontsize = "Normal";Sharing.isScale = false;break;
                }
                Log.d("fontsize", fontsize);
                if (Sharing.isScale != initial_isScale)
                {
                    Intent intent = new Intent (SettingPageActivity.this, TransferActivity.class);
                    startActivity(intent);
                    SettingPageActivity.this.finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        setting_page_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPageActivity.this, InformationCollectionActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("fontsize", fontsize);
                startActivity(intent);

                // Setting page shouldn't finish at this point
                //SettingPageActivity.this.finish();
            }
        });

        setting_page_data_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPageActivity.this, DataListActivity.class);
                // TODO FIX
                // startActivity(intent);

                // Setting page shouldn't finish at this point
                //SettingPageActivity.this.finish();
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

}
