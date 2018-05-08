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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

import javax.xml.datatype.Duration;

public class DurationSelectionActivity extends AppCompatActivity {

    private int user_id;
    private String test_type;
    private String image_type;

    private Button start_test_button;
    private SeekBar duration_seekbar;
    private TextView duration_progress_textview;
    private TextView duration_test_instruction;

    private Spinner duration_selection_page_with_seekbar_width_spinner;
    private Spinner duration_selection_page_without_seekbar_width_spinner;

    private int interval_duration;

    private int painter_width;

    private ArrayAdapter adapter;

    private long exitTime;

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
            TextScaleUtilsLower.scaleTextSize(DurationSelectionActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DurationSelectionActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type").toString();
        image_type = getIntent().getStringExtra("image_type").toString();

        if (test_type.contains("static") || test_type.contains("parallel") || test_type.contains("circular"))
        {
            // Static tests and dynamic tests will use different layout file.
            // For static tests, the layout file doesn't contain the seekbar
            // While for dynamic tests, the layout file contains a seekbar which is used for user to choose duration interval.

            setContentView(R.layout.activity_duration_selection_without_seekbar);

            duration_selection_page_without_seekbar_width_spinner = (Spinner) findViewById(R.id.duration_without_seekbar_width_spinner);

            String [] mList = getResources().getStringArray(R.array.painter_width);
            adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, mList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            duration_selection_page_without_seekbar_width_spinner.setAdapter(adapter);

            duration_selection_page_without_seekbar_width_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0: painter_width = 5;break;
                        case 1: painter_width = 10;break;
                        case 2: painter_width = 15;break;
                        default: painter_width = 5;break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            duration_test_instruction = (TextView) findViewById(R.id.duration_selection_page_test_instruction);
            if (test_type.equalsIgnoreCase("static_full_background"))
            {
                duration_test_instruction.setText(R.string.static_full_background_test_instruction);
            }
            else if (test_type.equalsIgnoreCase("static_corner_background"))
            {
                duration_test_instruction.setText(R.string.static_corner_background_test_instruction);
            }
            else if (test_type.equalsIgnoreCase("parallel_line_practice"))
            {
                duration_test_instruction.setText(R.string.parallel_line_practice_instruction);
            }
            else if (test_type.equalsIgnoreCase("parallel_line_test"))
            {
                duration_test_instruction.setText(R.string.parallel_line_test_instruction);
            }
            /*
            else if (test_type.equalsIgnoreCase("circular_motion_test"))
            {
                duration_test_instruction.setText("This is the circular motion test. During the test the background, which contains a red circle in the centre of the screen, will always be here. Your task is to draw a circle using this red circle as the centre. ");
            }
            */

            start_test_button = (Button) findViewById(R.id.duration_selection_page_start_test_button);
            start_test_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (test_type.equalsIgnoreCase("static_full_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, StaticBackgroundTestActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(0));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        Sharing.painter_width = painter_width;
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    else if (test_type.equalsIgnoreCase("static_corner_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, StaticCornerBackgroundTestActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(0));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        Sharing.painter_width = painter_width;
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    else if (test_type.equalsIgnoreCase("parallel_line_test"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, ParallelLineTestActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(0));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        Sharing.painter_width = painter_width;
                        Sharing.sharing_image = "parallel_line";
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    else if (test_type.equalsIgnoreCase("parallel_line_practice"))
                    {
                        Intent intent = new Intent (DurationSelectionActivity.this, ParallelLinePracticeActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(0));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        Sharing.painter_width = painter_width;
                        Sharing.sharing_image = "";
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    /*
                    else if (test_type.equalsIgnoreCase("circular_motion_test"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, CircularMotionTestActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(0));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        Sharing.painter_width = painter_width;
                        Sharing.sharing_image = "circular_motion";
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    */
                }
            });

        }
        if (test_type.contains("dynamic"))
        {
            setContentView(R.layout.activity_duration_selection_with_seekbar);
            start_test_button = (Button) findViewById(R.id.duration_selection_page_start_test_button);
            duration_seekbar = (SeekBar) findViewById(R.id.duration_selection_seekbar);
            duration_progress_textview = (TextView) findViewById(R.id.duration_progress_textview);
            duration_progress_textview.setText(getString(R.string.current_time_interval) + " " + duration_seekbar.getProgress() + " " + getString(R.string.second));
            duration_test_instruction = (TextView) findViewById(R.id.duration_selection_page_test_instruction);

            if (test_type.equalsIgnoreCase("dynamic_blank_background"))
            {
                duration_test_instruction.setText(R.string.dynamic_blank_background_test_instruction);
            }
            else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
            {
                duration_test_instruction.setText(R.string.dynamic_seasonal_background_test_instruction);
            }
            
            duration_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    duration_progress_textview.setText(getString(R.string.current_time_interval) + " " + progress + " " + getString(R.string.second));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            duration_selection_page_with_seekbar_width_spinner = (Spinner) findViewById(R.id.duration_with_seekbar_width_spinner);

            String [] mList = getResources().getStringArray(R.array.painter_width);
            adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, mList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            duration_selection_page_with_seekbar_width_spinner.setAdapter(adapter);

            duration_selection_page_with_seekbar_width_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0: painter_width = 5;break;
                        case 1: painter_width = 10;break;
                        case 2: painter_width = 15;break;
                        default: painter_width = 5;break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            start_test_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interval_duration = duration_seekbar.getProgress();
                    if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, DynamicShowBackgroundActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(interval_duration));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = interval_duration;
                        Sharing.painter_width = painter_width;
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                    else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                    {
                        Intent intent = new Intent (DurationSelectionActivity.this, DynamicSeasonalBackgroundTestActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", String.valueOf(interval_duration));
                        intent.putExtra("painter_width", String.valueOf(painter_width));
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = interval_duration;
                        Sharing.painter_width = painter_width;
                        startActivity(intent);
                        DurationSelectionActivity.this.finish();
                    }
                }
            });

        }





    }

    // TODO OPTIONAL 2/4


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent;
            if (Sharing.test_to_duration.equalsIgnoreCase("yes"))
            {
                Sharing.test_to_duration = "";
                intent = new Intent (DurationSelectionActivity.this, TestSelectionActivity.class);
            }
            else
            {
                intent = new Intent(DurationSelectionActivity.this, ImageSelectionActivity.class);
            }
            intent.putExtra("user_id", String.valueOf(user_id));
            intent.putExtra("test_type", test_type);
            startActivity(intent);
            DurationSelectionActivity.this.finish();
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
