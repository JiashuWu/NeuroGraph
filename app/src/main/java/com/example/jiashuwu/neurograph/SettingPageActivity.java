package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(SettingPageActivity.this, Sharing.isScale);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_page);

        initial_isScale = Sharing.isScale;

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
                startActivity(intent);

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
