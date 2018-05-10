package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ParallelLinePracticeResultActivity extends AppCompatActivity {

    private TextView parallel_line_result_result;
    private TextView parallel_line_result_description;
    private Button parallel_line_result_button_done;

    private int user_id;

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
            TextScaleUtilsLower.scaleTextSize(ParallelLinePracticeResultActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ParallelLinePracticeResultActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallel_line_practice_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("destroy", "starting");

        parallel_line_result_result = (TextView) findViewById(R.id.parallel_line_result_result);
        parallel_line_result_description = (TextView) findViewById(R.id.parallel_line_result_description);
        parallel_line_result_button_done = (Button) findViewById(R.id.parallel_line_result_button_done);

        user_id = 0;
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        for (int i = 0 ; i < Sharing.x_list.size() ; i++)
        {
            Log.d("TAG_X_LIST", String.valueOf(Sharing.x_list.get(i)));
        }

        // Show the practice resulr
        String [] results = calculateParallelLinePracticeResult.calculateParallelLinePracticeResult();
        parallel_line_result_result.setText(results[0]);
        parallel_line_result_description.setText(results[1]);

        parallel_line_result_button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParallelLinePracticeResultActivity.this, TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                startActivity(intent);
                ParallelLinePracticeResultActivity.this.finish();
            }
        });


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
