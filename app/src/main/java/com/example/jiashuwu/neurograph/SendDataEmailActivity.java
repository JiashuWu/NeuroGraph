package com.example.jiashuwu.neurograph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class SendDataEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(SendDataEmailActivity.this, Sharing.isScale);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_email);
    }


}
