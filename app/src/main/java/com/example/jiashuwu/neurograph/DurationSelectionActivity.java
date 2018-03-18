package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.xml.datatype.Duration;

public class DurationSelectionActivity extends AppCompatActivity {

    private int user_id;
    private String test_type;
    private String image_type;

    private Button start_test_button;
    private SeekBar duration_seekbar;
    private TextView duration_progress_textview;
    private int interval_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user_id = getIntent().getIntExtra("user_id", -100000);
        test_type = getIntent().getStringExtra("test_type").toString();
        image_type = getIntent().getStringExtra("image_type").toString();

        if (test_type.contains("static"))
        {
            setContentView(R.layout.activity_duration_selection_without_seekbar);
            start_test_button = (Button) findViewById(R.id.duration_selection_page_start_test_button);
            start_test_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (test_type.equalsIgnoreCase("static_full_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, StaticBackgroundTestActivity.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", 0);
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        startActivity(intent);
                    }
                    else if (test_type.equalsIgnoreCase("static_corner_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, StaticCornerBackgroundTestActivity.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", 0);
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = 0;
                        startActivity(intent);
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

            start_test_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interval_duration = duration_seekbar.getProgress();
                    if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                    {
                        Intent intent = new Intent(DurationSelectionActivity.this, DynamicShowBackgroundActivity.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", interval_duration);
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = interval_duration;
                        startActivity(intent);
                    }
                    else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                    {
                        Intent intent = new Intent (DurationSelectionActivity.this, DynamicSeasonalBackgroundTestActivity.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("test_type", test_type);
                        intent.putExtra("image_type", image_type);
                        intent.putExtra("interval_duration", interval_duration);
                        Sharing.user_id = user_id;
                        Sharing.test_type = test_type;
                        Sharing.image_type = image_type;
                        Sharing.interval_duration = interval_duration;
                        startActivity(intent);
                    }
                }
            });
        }





    }
}
