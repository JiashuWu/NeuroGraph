package com.example.jiashuwu.neurograph;

import java.util.ArrayList;

/**
 * Created by Jiashu Wu on 18/03/2018.
 */

public class Sharing {

    public static int user_id;
    public static String test_type;
    public static String image_type;
    public static int interval_duration;
    public static String test_starting_time;
    public static String test_ending_time;

    public static ArrayList<Float> x_list;
    public static ArrayList<Float> y_list;
    public static ArrayList<Float> pressure_list;
    public static ArrayList<String> timestamp_list;
    public static ArrayList<Float> touch_point_size_list;

    public static String sharing_image;

    public static boolean changing;

    public static boolean isScale;

    public static int painter_width;

    public static boolean show_as_content;

    public static String file_path;

    public static String language = "";

    public static int file_version = 1;

    public static ArrayList<String> csv_string_arraylist;

    public static int progress;
    public static int progress_total;

    public static int number_of_item_finished;
    public static int number_of_item_in_total;

    public static int current_user_id;

    public static String redirect_source;

    public static int stop_showing_process;

    public static String test_detail;

    public static int frequency_per_second = 260;

    public Sharing()
    {

    }



}
