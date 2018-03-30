package com.example.jiashuwu.neurograph;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by Jiashu Wu on 20/03/2018.
 */

public class TextScaleUtils {
    public static void scaleTextSize (Activity activity,boolean isScale)
    {
        float size;
        Configuration configuration = activity.getResources().getConfiguration();
        configuration.setToDefaults();
        if (isScale)
        {
            size = 1.5f;
        }
        else
        {
            size = 1f;
        }
        configuration.fontScale = size;
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        activity.getApplicationContext().getResources().updateConfiguration(configuration, metrics);

    }
}
