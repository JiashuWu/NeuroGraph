package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class IntroductionPageActivity extends AppCompatActivity {

    private TextView introduction_textview;
    private TextView introduction_content_textview;

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
            TextScaleUtilsLower.scaleTextSize(IntroductionPageActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(IntroductionPageActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        introduction_textview = (TextView) findViewById(R.id.introduction_page_centre_textview);
        introduction_content_textview = (TextView) findViewById(R.id.introduction_page_content_textview);

        // ANIMATION NUMBER 1
        introduction_textview.setText(R.string.hello);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 3.0f);
        alphaAnimation.setDuration(3000);
        introduction_textview.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // ANIMATION NUMBER 2
                introduction_textview.setText(getResources().getString(R.string.hello));
                AlphaAnimation alphaAnimation1 = new AlphaAnimation(3.0f, 0.0f);
                alphaAnimation1.setDuration(2000);
                introduction_textview.setAnimation(alphaAnimation1);
                alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // ANIMATION NUMBER 3
                        introduction_textview.setText(getResources().getString(R.string.welcome_to_neurograph));
                        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 3.0f);
                        alphaAnimation2.setDuration(3000);
                        introduction_textview.setAnimation(alphaAnimation2);
                        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                // ANIMATION NUMBER 4

                                introduction_textview.setText(getResources().getString(R.string.welcome_to_neurograph));
                                AlphaAnimation alphaAnimation3 = new AlphaAnimation(3.0f, 0.0f);
                                alphaAnimation3.setDuration(2000);
                                introduction_textview.setAnimation(alphaAnimation3);
                                alphaAnimation3.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        introduction_textview.setText("");
                                        Intent intent = new Intent (IntroductionPageActivity.this, IntroductionContentActivity.class);
                                        startActivity(intent);
                                        IntroductionPageActivity.this.finish();
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }



    @Override
    public boolean onOptionsItemSelected (MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if (id == android.R.id.home)
        {
            Intent intent = new Intent (IntroductionPageActivity.this, SettingPageActivity.class);
            startActivity(intent);
            IntroductionPageActivity.this.finish();
        }
        return true;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent (IntroductionPageActivity.this, SettingPageActivity.class);
            startActivity(intent);
            IntroductionPageActivity.this.finish();
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
