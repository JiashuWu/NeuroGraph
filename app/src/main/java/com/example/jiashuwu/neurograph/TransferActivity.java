package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Intent intent = new Intent(TransferActivity.this, SettingPageActivity.class);
        startActivity(intent);
        TransferActivity.this.finish();
    }
}
