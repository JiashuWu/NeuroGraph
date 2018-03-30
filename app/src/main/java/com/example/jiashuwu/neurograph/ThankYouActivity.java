package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class ThankYouActivity extends AppCompatActivity {

    private int user_id;

    private boolean initial_isScale;

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
            TextScaleUtilsLower.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        initial_isScale = Sharing.isScale;
        setContentView(R.layout.activity_thank_you);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }, 3000);
    }
}
