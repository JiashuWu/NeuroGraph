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
    public static ArrayList<Integer> tool_type_list;

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

    public static String redirect_source = "";

    public static int stop_showing_process;

    public static String test_detail;

    public static int frequency_per_second = 300;

    public static int frequency_per_second_for_thank_you_page = 300;

    public static String colour = "";

    public static String test_to_duration = "";

    public static int hundred_sleeping_time = 1200;

    public static String device_system_version_code;

    public static String device_brand;

    public static String device_model;

    public static int device_height_in_pixels;

    public static int device_width_in_pixels;

    public static int device_navigation_bar_height;

    public static String device_manufacturer;

    public static String device_product_name;

    public static int device_testing_area_width;

    public static int device_testing_area_height;

    public static boolean test_selection_has_already_asked_file_issue;

    public static String previous_email_address = "";

    public static String db_file_path = "";

    public static boolean email_csv = true;

    public static boolean email_txt = true;

    public static boolean email_db = true;

    public static boolean email_readme = true;

    public static boolean store_csv = true;

    public static boolean store_txt = true;

    public static boolean store_db = true;

    public static boolean store_readme = true;

    public static ArrayList<String> test_detail_arraylist = new ArrayList<>();

    public static String test_detail_title = "";

    public static int entries_per_page = 200;


    public Sharing()
    {

    }



}
