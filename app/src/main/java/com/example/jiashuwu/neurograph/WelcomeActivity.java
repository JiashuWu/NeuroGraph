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

        language = Locale.getDefault().toString();
        Log.d("language_code", language);
        switch (language.toLowerCase())
        {
            case "zh_CN": language = "Simplified Chinese (ZH_CN)";break;
            case "zh_TW": language = "Traditional Chinese (ZH_TW)";break;
            case "zh_HK": language = "Traditional Chinese (ZH_HK)";break;
            case "nl_NL": language = "Dutch Netherlands (NL_NL)";break;
            case "en_AU": language = "English Australia (EN_AU)";break;
            case "en_CA": language = "English Canada (EN_CA)";break;
            case "en_NZ": language = "English New Zealand (EN_NZ)";break;
            case "en_GB": language = "English Great Britain (EN_GB)";break;
            case "en_US": language = "English United States (EN_US)";break;
            case "fr_FR": language = "French France (FR_FR)";break;
            case "de_DE": language = "German Germany (DE_DE)";break;
            case "it_IT": language = "Italian Italy (IT_IT)";break;
            case "ja_JP": language = "Japanese Japan (JA_JP)";break;
            case "pt_PT": language = "Portuguese Portugal (PT_PT)";break;
            case "ru_RU": language = "Russian Russia (RU_RU)";break;
            case "es_ES": language = "Spanish Spain (ES_ES)";break;
            default: language = "English Great Britain (EN_GB)";break;
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
