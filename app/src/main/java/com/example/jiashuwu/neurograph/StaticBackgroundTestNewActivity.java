package com.example.jiashuwu.neurograph;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

// https://www.cnblogs.com/plokmju/p/android_Canvas.html

// ABANDONED

public class StaticBackgroundTestNewActivity extends Activity {
    private Button btn_save, btn_resume;
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    private Bitmap bmcopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_background_test_new);

        // Initialise a painter, with width 5 and colour set to RED
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        baseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pentagon_full_screen_fixed);
        bmcopy = Bitmap.createBitmap(baseBitmap.getWidth(), baseBitmap.getHeight(), baseBitmap.getConfig());

        canvas = new Canvas(bmcopy);
        canvas.drawBitmap(baseBitmap, new Matrix(), paint);
        iv_canvas = (ImageView) findViewById(R.id.simulate_canvas);
        btn_save = (Button) findViewById(R.id.loooooooooooo) ;
        btn_resume = (Button) findViewById(R.id.miaooooooooooooooo);
        iv_canvas.setImageBitmap(bmcopy);

        btn_save.setOnClickListener(click);
        btn_resume.setOnClickListener(click);
        iv_canvas.setOnTouchListener(touch);
    }

    private View.OnTouchListener touch = new OnTouchListener() {
        // 定义手指开始触摸的坐标
        float startX;
        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                // 用户按下动作
                case MotionEvent.ACTION_DOWN:
                    // 第一次绘图初始化内存图片，指定背景为白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(baseBitmap);
                        canvas.drawColor(Color.WHITE);
                    }
                    // 记录开始触摸的点的坐标
                    startX = event.getX();
                    startY = event.getY();
                    break;
                // 用户手指在屏幕上移动的动作
                case MotionEvent.ACTION_MOVE:
                    // 记录移动位置的点的坐标
                    float stopX = event.getX();
                    float stopY = event.getY();

                    //根据两点坐标，绘制连线
                    canvas.drawLine(startX, startY, stopX, stopY, paint);

                    // 更新开始点的位置
                    startX = event.getX();
                    startY = event.getY();

                    // 把图片展示到ImageView中
                    iv_canvas.setImageBitmap(baseBitmap);
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private View.OnClickListener click = new OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.loooooooooooo:
                    saveBitmap();
                    break;
                case R.id.miaooooooooooooooo:
                    resumeCanvas();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 保存图片到SD卡上
     */
    protected void saveBitmap() {
        try {
            // 保存图片到SD卡上
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".png");
            FileOutputStream stream = new FileOutputStream(file);
            baseBitmap.compress(CompressFormat.PNG, 100, stream);
            Toast.makeText(StaticBackgroundTestNewActivity.this, "保存图片成功", Toast.LENGTH_LONG).show();

            // Android设备Gallery应用只会在启动的时候扫描系统文件夹
            // 这里模拟一个媒体装载的广播，用于使保存的图片可以在Gallery中查看
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment
                    .getExternalStorageDirectory()));
            sendBroadcast(intent);
        } catch (Exception e) {
            Toast.makeText(StaticBackgroundTestNewActivity.this, "保存图片失败", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * 清除画板
     */
    protected void resumeCanvas() {
        // 手动清除画板的绘图，重新创建一个画板
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                    iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);
            iv_canvas.setImageBitmap(baseBitmap);
            Toast.makeText(StaticBackgroundTestNewActivity.this, "清除画板成功，可以重新开始绘图", Toast.LENGTH_LONG).show();
        }
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