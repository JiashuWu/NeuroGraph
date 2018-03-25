package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class DurationSelectionActivity extends AppCompatActivity {

    private int user_id;
    private String test_type;
    private String image_type;

    private Button start_test_button;
    private SeekBar duration_seekbar;
    private TextView duration_progress_textview;

    private Spinner duration_selection_page_with_seekbar_width_spinner;
    private Spinner duration_selection_page_without_seekbar_width_spinner;

    private int interval_duration;

    private int painter_width;

    private ArrayAdapter adapter;

    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(DurationSelectionActivity.this, Sharing.isScale);

        super.onCreate(savedInstanceState);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type").toString();
        image_type = getIntent().getStringExtra("image_type").toString();

        if (test_type.contains("static"))
        {
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
                }
            });

        }
        if (test_type.contains("dynamic"))
        {
            setContentView(R.layout.activity_duration_selection_with_seekbar);
            start_test_button = (Button) findViewById(R.id.duration_selection_page_start_test_button);
            duration_seekbar = (SeekBar) findViewById(R.id.duration_selection_seekbar);
            duration_progress_textview = (TextView) findViewById(R.id.duration_progress_textview);
            duration_progress_textview.setText("Current time interval " + duration_seekbar.getProgress() + " seconds");
            duration_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    duration_progress_textview.setText("Current time interval " + progress + " seconds");
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
    /*

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
    */

}
