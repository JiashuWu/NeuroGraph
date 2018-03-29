package com.example.jiashuwu.neurograph;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private long exitTime;

    private String language = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //TextScaleUtils.scaleTextSize(WelcomeActivity.this, Sharing.isScale);

        // get the system language
        language = Locale.getDefault().toString();
        Log.d("language_code", language);
        switch (language.toLowerCase())
        {
            case "zh_CN": language = "Simplified Chinese";break;
            case "zh_TW": language = "Traditional Chinese";break;
            case "zh_HK": language = "Traditional Chinese";break;
            case "nl_NL": language = "Dutch";break;
            case "en_AU": language = "English";break;
            case "en_CA": language = "English";break;
            case "en_NZ": language = "English";break;
            case "en_GB": language = "English";break;
            case "en_US": language = "English";break;
            case "fr_FR": language = "French";break;
            case "de_DE": language = "German";break;
            case "it_IT": language = "Italian";break;
            case "ja_JP": language = "Japanese";break;
            case "pt_PT": language = "Portuguese";break;
            case "ru_RU": language = "Russian";break;
            case "es_ES": language = "Spanish";break;
            default: language = "English";break;
        }
        Sharing.language = language;
        Log.d("language", Sharing.language);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Sharing.isScale = false;
                Intent intent = new Intent(WelcomeActivity.this , SettingPageActivity.class);
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, 5000);
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
