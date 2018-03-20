package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class DynamicShowBackgroundActivity extends AppCompatActivity {


    private int user_id;
    private String test_type;
    private String image_type;
    private int interval_duration;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(DynamicShowBackgroundActivity.this, Sharing.isScale);

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
            imageView.setImageResource(R.drawable.spiral1);
        }
        if (Sharing.sharing_image.equalsIgnoreCase("pentagon"))
        {
            imageView.setImageResource(R.drawable.pentagon1);
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
}
