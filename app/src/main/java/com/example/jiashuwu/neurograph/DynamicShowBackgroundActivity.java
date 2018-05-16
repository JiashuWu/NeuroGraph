package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class DynamicShowBackgroundActivity extends AppCompatActivity {


    private int user_id;
    private String test_type;
    private String image_type;
    private int interval_duration;

    private ImageView imageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(DynamicShowBackgroundActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DynamicShowBackgroundActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_dynamic_show_background);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type");
        image_type = getIntent().getStringExtra("image_type");
        interval_duration = Integer.parseInt(getIntent().getStringExtra("interval_duration").toString());

        imageView = (ImageView) findViewById(R.id.dynamic_show_background_imageview);
        if (Sharing.sharing_image.equalsIgnoreCase("spiral"))
        {
            imageView.setImageResource(R.drawable.spiral_new);
        }
        if (Sharing.sharing_image.equalsIgnoreCase("pentagon"))
        {
            imageView.setImageResource(R.drawable.pentagon_new);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                {
                    intent = new Intent(DynamicShowBackgroundActivity.this , DynamicBlankBackgroundTestActivity.class);

                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    intent.putExtra("image_type", image_type);
                    intent.putExtra("interval_duration", String.valueOf(interval_duration));

                    DynamicShowBackgroundActivity.this.startActivity(intent);
                    DynamicShowBackgroundActivity.this.finish();
                }
            }
        }, 1000 * interval_duration);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(this, getResources().getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStart ()
    {
        super.onStart();
    }

    @Override
    public void onRestart ()
    {
        super.onRestart();
    }

    @Override
    public void onResume ()
    {
        super.onResume();
    }

    @Override
    public void onPause ()
    {
        super.onPause();
    }

    @Override
    public void onStop ()
    {
        super.onStop();
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }
}
