package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThankYouActivity extends AppCompatActivity {

    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }, 3000);
    }
}
