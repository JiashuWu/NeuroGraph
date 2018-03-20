package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThankYouActivity extends AppCompatActivity {

    private int user_id;

    private boolean initial_isScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        initial_isScale = Sharing.isScale;
        super.onCreate(savedInstanceState);
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
