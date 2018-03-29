package com.example.jiashuwu.neurograph;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Jiashu Wu on 29/03/2018.
 */

public class calculateParallelLinePracticeResult {

    public static ArrayList<Float> x_list;
    public static ArrayList<Float> y_list;
    public static ArrayList<Float> x_list_line_1;
    public static ArrayList<Float> y_list_line_1;
    public static ArrayList<Float> x_list_line_2;
    public static ArrayList<Float> y_list_line_2;
    public static ArrayList<Float> slope_list_line_1;
    public static ArrayList<Float> slope_list_line_2;
    public static float summary_slope_line_1;
    public static float summary_slope_line_2;
    public static float final_result;

    public static String [] calculateParallelLinePracticeResult()
    {
        x_list = new ArrayList<>();
        y_list = new ArrayList<>();
        x_list_line_1 = new ArrayList<>();
        y_list_line_1 = new ArrayList<>();
        x_list_line_2 = new ArrayList<>();
        y_list_line_2 = new ArrayList<>();
        slope_list_line_1 = new ArrayList<>();
        slope_list_line_2 = new ArrayList<>();
        summary_slope_line_1 = 0;
        summary_slope_line_2 = 0;
        final_result = 0;



        x_list = Sharing.x_list;
        y_list = Sharing.y_list;
        Log.d("debuggggg", String.valueOf(x_list.get(1)));
        int i = 0;
        int position = 0;
        for (i = 0 ; i < x_list.size() ; i++)
        {
            if (x_list.get(i) != 0)
            {
                x_list_line_1.add(x_list.get(i));
                y_list_line_1.add(y_list.get(i));
            }
            else
            {
                position = i + 1;
                break;
            }
        }
        for (i = position ; i < x_list.size() ; i++)
        {
            if (x_list.get(i) != 0)
            {
                x_list_line_2.add(x_list.get(i));
                y_list_line_2.add(y_list.get(i));
            }
            else
            {
                break;
            }
        }
        for (i = 1 ; i < x_list_line_1.size() ; i++)
        {
            slope_list_line_1.add((float)((y_list_line_1.get(i) - y_list_line_1.get(0)) / (x_list_line_1.get(i) - x_list_line_1.get(0))));
        }
        for (i = 1 ; i < x_list_line_2.size() ; i++)
        {
            slope_list_line_2.add((float)((y_list_line_2.get(i) - y_list_line_2.get(0)) / (x_list_line_2.get(i) - x_list_line_2.get(0))));
        }
        Log.d("summary001", String.valueOf(slope_list_line_1.size()));
        Log.d("summary002", String.valueOf(slope_list_line_2.size()));
        float count = 1;
        float sum = 0;
        for (i = 0 ; i < slope_list_line_1.size() ; i++)
        {
            Log.d("SUMMARYYY", String.valueOf(slope_list_line_1.get(i)));
        }
        for (i = 0 ; i < slope_list_line_1.size() ; i++)
        {
            summary_slope_line_1 = summary_slope_line_1 + count * slope_list_line_1.get(i);
            sum = sum + count;
            count = count + (float)1;
        }
        Log.d("summary=", String.valueOf(summary_slope_line_1));
        Log.d("sum", String.valueOf(sum));
        summary_slope_line_1 = summary_slope_line_1 / sum;
        count = 1;
        sum = 0;
        for (i = 0 ; i < slope_list_line_2.size() ; i++)
        {
            summary_slope_line_2 = summary_slope_line_2 + count * slope_list_line_2.get(i);
            sum = sum + count;
            count = count + (float)1;
        }
        summary_slope_line_2 = summary_slope_line_2 / sum;

        Log.d("summary01", String.valueOf(summary_slope_line_1));
        Log.d("summary02", String.valueOf(summary_slope_line_2));
        final_result = summary_slope_line_1 - summary_slope_line_2;
        final_result = Math.abs(final_result);
        Log.d("FINAL_RESULT", String.valueOf(final_result));
        String result = "";
        String result_description = "";
        if (final_result <= 0.07)
        {
            result = "Perfect";
            result_description = "The two lines you drew are perfectly parallel";
        }
        else if (final_result <= 0.35)
        {
            result = "Good";
            result_description = "The two lines you drew are relatively parallel";
        }
        else if (final_result <= 0.70)
        {
            result = "Acceptable";
            result_description = "The two lines you drew are parallel but not that good";
        }
        else
        {
            result = "Bad";
            result_description = "The two lines you drew are not parallel";
        }
        return new String[]{result, result_description};
    }

}
