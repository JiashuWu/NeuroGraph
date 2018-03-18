package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }, 3000);
    }
}
