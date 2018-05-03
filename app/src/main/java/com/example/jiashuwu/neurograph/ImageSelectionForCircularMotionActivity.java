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

public class ImageSelectionForCircularMotionActivity extends AppCompatActivity {

    private int user_id;
    private String test_type;
    private String image_type;

    private Button start_test_button;

    private TextView image_selection_for_circular_motion_test_instruction;

    private Spinner image_selection_for_circular_motion_width_spinner;
    private Spinner image_selection_for_circular_motion_image_spinner;

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
            TextScaleUtilsLower.scaleTextSize(ImageSelectionForCircularMotionActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ImageSelectionForCircularMotionActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection_for_circular_motion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type").toString();

        image_selection_for_circular_motion_width_spinner = (Spinner) findViewById(R.id.image_selection_for_circular_motion_width_spinner);
        image_selection_for_circular_motion_image_spinner = (Spinner) findViewById(R.id.image_selection_for_circular_motion_image_type_spinner);

        String [] mList = getResources().getStringArray(R.array.painter_width);
        adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        image_selection_for_circular_motion_width_spinner.setAdapter(adapter);

        mList = getResources().getStringArray(R.array.circular_motion_image_type);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        image_selection_for_circular_motion_image_spinner.setAdapter(adapter);

        image_selection_for_circular_motion_width_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        image_selection_for_circular_motion_image_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: image_type = "red_dot"; Sharing.sharing_image = "red_dot"; break;
                    case 1: image_type = "blue_dot"; Sharing.sharing_image = "blue_dot"; break;
                    case 2: image_type = "black_dot"; Sharing.sharing_image = "black_dot"; break;
                    default: image_type = "red_dot"; Sharing.sharing_image = "red_dot"; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        image_selection_for_circular_motion_test_instruction = (TextView) findViewById(R.id.image_selection_for_circular_motion_test_instruction);

        image_selection_for_circular_motion_test_instruction.setText(R.string.circular_motion_test_instruction);

        start_test_button = (Button) findViewById(R.id.image_selection_for_circular_motion_start_test_button);
        start_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ImageSelectionForCircularMotionActivity.this, CircularMotionTestActivity.class);
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
                ImageSelectionForCircularMotionActivity.this.finish();
            }
        });








    }

    // TODO OPTIONAL 2/4


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent;

            intent = new Intent (ImageSelectionForCircularMotionActivity.this, TestSelectionActivity.class);

            intent.putExtra("user_id", String.valueOf(user_id));
            intent.putExtra("test_type", test_type);
            startActivity(intent);
            ImageSelectionForCircularMotionActivity.this.finish();
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
