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
    public float touch_point_size;
    public int tool_type;

    public ArrayList<Float> x_list;
    public ArrayList<Float> y_list;
    public ArrayList<Float> pressure_list;
    public ArrayList<Float> touch_point_size_list;
    public ArrayList<Integer> tool_type_list;
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

    public String month_s;
    public String day_s;
    public String hour_s;
    public String minute_s;
    public String second_s;
    public String millisecond_s;


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
        touch_point_size_list = new ArrayList<Float>();
        tool_type_list = new ArrayList<>();

        Sharing.number_of_item_in_total = 0;

        calendar = Calendar.getInstance();
        initial_second = calendar.get(Calendar.SECOND);
        changing_second = initial_second;
        Sharing.changing = true;

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
        Sharing.changing = true;
        //calendar = Calendar.getInstance();
        //initial_second = calendar.get(Calendar.SECOND);
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
        Sharing.touch_point_size_list = touch_point_size_list;
        Sharing.tool_type_list = tool_type_list;
        //Sharing.number_of_item_in_total = x_list.size();
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
            Log.d("TAG_TIME", String.valueOf(initial_second));
            Log.d("TAG_TIME", String.valueOf(current_second));
            Log.d("TAG_TIME", String.valueOf(Sharing.changing));
            if (Math.abs(current_second - initial_second) % Sharing.interval_duration == 0 && changing_second != current_second)
            {
                Sharing.changing = !Sharing.changing;
                changing_second = current_second;

            }

            if (Sharing.changing)
            {
                if (Sharing.image_type.equalsIgnoreCase("spiral"))
                {
                    // TODO SOLUTION
                    //mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.spiral_matlab_corrected_version_1), (int)(displayHeight * 1181 / 720), displayHeight, true), (int)((displayWidth - displayHeight * 1181 / 720) / 2), 0, null);//top-right corner);
                    mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.spiral_matlab_corrected_version_1), displayWidth, displayHeight, true), 0, 0, null);//top-right corner);
                }
                if (Sharing.image_type.equalsIgnoreCase("pentagon"))
                {
                    // TODO SOLUTION
                    //mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pentagon_matlab_corrected_version_fixed_1), (int)(displayHeight * 1181 / 720), displayHeight, true), (int)((displayWidth - displayHeight * 1181 / 720) / 2), 0, null);//top-right corner);
                    mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pentagon_matlab_corrected_version_fixed_1), displayWidth, displayHeight, true), 0, 0, null);//top-right corner);
                }
            }
            else
            {
                mCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.white_background), displayWidth, displayHeight, true),0,0,null);//top-right corner
            }

            mpaint.setStyle(Paint.Style.STROKE);
            mpaint.setStrokeWidth(Sharing.painter_width);
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
        pressure = (float) event.getPressure(event.getPointerCount() - 1);
        touch_point_size = (float) event.getSize(event.getPointerCount() - 1);
        tool_type = (int) event.getToolType(event.getPointerCount() - 1);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);

        if (String.valueOf(month).length() == 1)
        {
            month_s = "0" + String.valueOf(month);
        }
        else
        {
            month_s = String.valueOf(month);
        }
        if (String.valueOf(day).length() == 1)
        {
            day_s = "0" + String.valueOf(day);
        }
        else
        {
            day_s = String.valueOf(day);
        }
        if (String.valueOf(hour).length() == 1)
        {
            hour_s = "0" + String.valueOf(hour);
        }
        else
        {
            hour_s = String.valueOf(hour);
        }
        if (String.valueOf(minute).length() == 1)
        {
            minute_s = "0" + String.valueOf(minute);
        }
        else
        {
            minute_s = String.valueOf(minute);
        }
        if (String.valueOf(second).length() == 1)
        {
            second_s = "0" + String.valueOf(second);
        }
        else
        {
            second_s = String.valueOf(second);
        }
        if (String.valueOf(millisecond).length() == 1)
        {
            millisecond_s = "00" + String.valueOf(millisecond);
        }
        else if (String.valueOf(millisecond).length() == 2)
        {
            millisecond_s = "0" + String.valueOf(millisecond);
        }
        else if (String.valueOf(millisecond).length() == 3)
        {
            millisecond_s = String.valueOf(millisecond);
        }

        // Dynamically change the background base on system time.
        current_time = String.valueOf(year) + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s + "." + millisecond_s;

        //current_time = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + "." + String.valueOf(millisecond);

        timestamp_list.add(current_time);
        x_list.add(x);
        y_list.add(y);
        pressure_list.add(pressure);
        touch_point_size_list.add(touch_point_size);
        tool_type_list.add(tool_type);
        Log.d("destroy", String.valueOf(x_list.size()));

        Sharing.number_of_item_in_total = x_list.size();

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
        x_list.clear();
        y_list.clear();
        pressure_list.clear();
        touch_point_size_list.clear();
        tool_type_list.clear();
        timestamp_list.clear();
        mPath.reset();

        Sharing.number_of_item_in_total = 0;

    }
}