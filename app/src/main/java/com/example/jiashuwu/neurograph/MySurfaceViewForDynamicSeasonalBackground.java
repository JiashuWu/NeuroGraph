package com.example.jiashuwu.neurograph;

/**
 * Created by Jiashu Wu on 18/03/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.apache.http.conn.scheme.HostNameResolver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;



public class MySurfaceViewForDynamicSeasonalBackground extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private SurfaceHolder mSurfaceHolder;
    private static Canvas mCanvas;
    private boolean startDraw;
    private Path mPath = new Path();
    private Paint mpaint = new Paint();

    public Calendar calendar = Calendar.getInstance();
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;
    public int millisecond;

    public String current_time;

    public float x;
    public float y;
    public float pressure;

    public ArrayList<Float> x_list;
    public ArrayList<Float> y_list;
    public ArrayList<Float> pressure_list;
    public ArrayList year_list;
    public ArrayList month_list;
    public ArrayList day_list;
    public ArrayList hour_list;
    public ArrayList minute_list;
    public ArrayList second_list;
    public ArrayList millisecond_list;
    public ArrayList<String> timestamp_list;

    public boolean image;

    public int displayWidth = 0;
    public int displayHeight = 0;

    public int delay = 0;
    public Handler handler;

    public int initial_second = 0;
    public int changing_second = 0;
    public int current_second = 0;


    public MySurfaceViewForDynamicSeasonalBackground(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setKeepScreenOn(true);

        timestamp_list = new ArrayList<String>();
        x_list = new ArrayList<Float>();
        y_list = new ArrayList<Float>();
        pressure_list = new ArrayList<Float>();

        calendar = Calendar.getInstance();
        initial_second = calendar.get(Calendar.SECOND);

        Sharing.changing = false;

    }

    @Override
    public void run()
    {
        while (startDraw)
        {
            draw();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        startDraw = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        // Nothing here
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        Sharing.x_list = x_list;
        Sharing.y_list = y_list;
        Sharing.pressure_list = pressure_list;
        Sharing.timestamp_list = timestamp_list;
        startDraw = false;
        //Log.d("destroy", "surface_destroy");
    }

    private void draw()
    {
        try
        {
            mCanvas = mSurfaceHolder.lockCanvas();
            //mCanvas.drawColor(Color.WHITE);

            Display display = ((DynamicSeasonalBackgroundTestActivity)getContext()).getWindowManager().getDefaultDisplay();
            displayWidth = display.getWidth();
            displayHeight = display.getHeight();
            //mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_dashboard_black_24dp), displayWidth, displayHeight, true),0,0,null);//top-right corner

            // The code below change the background dynamically

            calendar = Calendar.getInstance();
            current_second = calendar.get(Calendar.SECOND);

            if (Math.abs(current_second - initial_second) % Sharing.interval_duration == 0 && changing_second != current_second)
            {
                Sharing.changing = !Sharing.changing;
                changing_second = current_second;
            }

            if (Sharing.changing)
            {
                if (Sharing.image_type.equalsIgnoreCase("spiral"))
                {
                    mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.spiral1), displayWidth, displayHeight, true), 0, 0, null);//top-right corner);
                }
                if (Sharing.image_type.equalsIgnoreCase("pentagon"))
                {
                    mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pentagon1), displayWidth, displayHeight, true), 0, 0, null);//top-right corner);
                }
            }
            else
            {
                mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.white_background), displayWidth, displayHeight, true),0,0,null);//top-right corner
            }

            mpaint.setStyle(Paint.Style.STROKE);
            mpaint.setStrokeWidth(5);
            mpaint.setColor(Color.BLACK);
            mCanvas.drawPath(mPath, mpaint);

        }
        catch (Exception e)
        {
            // Nothing here
        }
        finally
        {
            if (mCanvas != null)
            {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        x = (float) event.getX();
        y = (float) event.getY();
        pressure = (float) event.getPressure();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);

        current_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

        timestamp_list.add(current_time);
        x_list.add(x);
        y_list.add(y);
        pressure_list.add(pressure);
        Log.d("destroy", String.valueOf(x_list.size()));

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                mPath.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:

                mPath.lineTo(x, y);
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void reset()
    {
        mPath.reset();
    }
}