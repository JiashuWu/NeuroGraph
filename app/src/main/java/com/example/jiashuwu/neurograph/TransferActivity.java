package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(TransferActivity.this, Sharing.isScale);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Log.d("transferring","transferring");
        Intent intent = new Intent(TransferActivity.this, SettingPageActivity.class);
        startActivity(intent);
        TransferActivity.this.finish();
    }
}
