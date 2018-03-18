package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingPageActivity extends AppCompatActivity {

    private String language = "";
    private String fontsize = "";

    private Spinner setting_page_language_spinner;
    private Spinner setting_page_fontsize_spinner;

    private Button setting_page_start_button;

    private ArrayAdapter language_adapter;
    private ArrayAdapter font_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        setting_page_language_spinner = (Spinner) findViewById(R.id.setting_page_language_spinner);
        setting_page_fontsize_spinner = (Spinner) findViewById(R.id.setting_page_font_size_spinner);

        setting_page_start_button = (Button) findViewById(R.id.setting_page_start_button);

        final String [] language_list = getResources().getStringArray(R.array.language_array);
        language_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, language_list);
        language_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_language_spinner.setAdapter(language_adapter);

        final String [] fontsize_list = getResources().getStringArray(R.array.font_array);
        font_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontsize_list);
        font_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_fontsize_spinner.setAdapter(font_adapter);

        setting_page_language_spinner.setSelection(0);

        setting_page_fontsize_spinner.setSelection(0);

        setting_page_language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: language = "English (EN_GB)";break;
                    case 1: language = "Simplified Chinese (CH_ZN)";break;
                    case 2: language = "Traditional Chinese (CH_TW)";break;
                    default: language = "English (EN_GB)";break;
                }
                Log.d("language", language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        setting_page_fontsize_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: fontsize = "Normal";break;
                    case 1: fontsize = "Large";break;
                    default: fontsize = "Normal";break;
                }
                Log.d("fontsize", fontsize);
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
                SettingPageActivity.this.finish();
            }
        });
    }
}
