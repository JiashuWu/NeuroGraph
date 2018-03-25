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

        language = Locale.getDefault().getLanguage().toString();
        switch (language.toLowerCase())
        {
            case "en": language = "English (EN_GB)";break;
            case "zh": language = "Simplified Chinese (CH_ZN)";break;
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
