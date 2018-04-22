package com.example.jiashuwu.neurograph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class FullHeightActivity extends AppCompatActivity {

    private int displayHeight;


    private int getStatusHeight() {
        int statusHeight = 0;
        Rect localRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_height);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        displayHeight = getStatusHeight();

        Sharing.device_height_in_pixels = displayHeight;

        /*
        Intent intent = new Intent(FullHeightActivity.this, SettingPageActivity.class);
        startActivity(intent);
        FullHeightActivity.this.finish();
        */

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
