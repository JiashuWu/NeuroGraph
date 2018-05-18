package com.example.jiashuwu.neurograph;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_NOTIFICATION_POLICY;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SendDataEmailActivity extends AppCompatActivity {

    private Button send_button;
    private Button cancel_button;

    private EditText recipient_edittext;
    private EditText subject_edittext;

    private String recipient = "";
    private String subject = "";

    private boolean readyToSend = true;
    private boolean networkAvailable = false;
    private boolean confirm_to_send = false;

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private MyDatabaseHelper databaseHelperD;
    private SQLiteDatabase databaseD;

    private MyDatabaseHelper databaseHelper1;
    private SQLiteDatabase database1;

    private MyDatabaseHelper databaseHelper2;
    private SQLiteDatabase database2;


    private String databaseName = DatabaseInformation.databaseName;
    private int databaseVersion = DatabaseInformation.databaseVersion;

    private int test_id;
    private int user_id;
    private String name;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;
    private int number_of_points;
    private int age;
    private String gender;
    private String education;
    private double rating_score;
    private String current_receive_treatment;
    private String timestamp_of_point;
    private float x;
    private float y;
    private float pressure;
    private float touch_point_size;
    private int tool_type;
    private String test_starting_time_number_only;
    private String test_ending_time_number_only;

    private String registration_code = "";

    private ArrayList<String> output_csv_strings;
    private ArrayList<String> output_string;

    private CheckBox content_checkbox;
    private CheckBox csv_checkbox;
    private CheckBox txt_checkbox;
    private CheckBox db_checkbox;
    private CheckBox readme_checkbox;

    public String content = "";

    private String send_button_clicked = "";

    private Switch delete_test_data_switch;
    private boolean should_delete_test_data;

    private int painter_width;
    private String language_during_test;
    private String is_scale_during_test;
    private int point_serial_number;

    public String db_file_path = "";

    public ImageView help_imageview;



    // Initialise the language
    public void initLocaleLanguage ()
    {
        Resources resource = getApplicationContext().getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        Locale DUTCH = new Locale("nl", "NL");
        Locale PORTUGAL = new Locale("pt", "PT");
        Locale RUSSIA = new Locale("ru", "RU");
        Locale SPAIN = new Locale("es", "ES");
        switch (Sharing.language)
        {
            case "English": configuration.locale = Locale.UK;break;
            case "Simplified Chinese": configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese": configuration.locale = Locale.TAIWAN;break;
            case "Dutch": configuration.locale = DUTCH;break;
            case "French": configuration.locale = Locale.FRANCE;break;
            case "German": configuration.locale = Locale.GERMANY;break;
            case "Italian": configuration.locale = Locale.ITALY;break;
            case "Portuguese": configuration.locale = PORTUGAL;break;
            case "Russian": configuration.locale = RUSSIA;break;
            case "Spanish": configuration.locale = SPAIN;break;
            default: configuration.locale = Locale.UK;break;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }

    // Initialise the colour themes
    public void init_theme ()
    {
        switch (Sharing.colour)
        {
            case "blue": setTheme(R.style.AppTheme); break;
            case "light_blue": setTheme(R.style.AppThemeLightBlue); break;
            case "green": setTheme(R.style.AppThemeGreen); break;
            case "purple": setTheme(R.style.AppThemePurple); break;
            case "pink": setTheme(R.style.AppThemePink); break;
            case "orange": setTheme(R.style.AppThemeOrange); break;
            default:setTheme(R.style.AppTheme); break;
        }
    }



    // GENERATE STRING FROM THE DATABASE


    // Generate string from database, this is used for generating the email content and data file contents.
    public String generate_string_from_database ()
    {
        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        database = databaseHelper.getReadableDatabase();

        String output_string = "";

        String time_year;
        String time_month;
        String time_day;
        String time_hour;
        String time_minute;
        String time_second;
        String time_millisecond = "";

        String query = "SELECT * FROM Test";
        String query1 = "";
        String query2 = "";
        String [] parameter = new String [] {};
        String [] parameter1;
        String [] parameter2;
        Log.d("NULLL", String.valueOf(parameter == null));

        Cursor cursor = database.rawQuery(query, new String[] {});
        Cursor cursor1;
        Cursor cursor2;
        output_csv_strings = new ArrayList<>();
        while (cursor.moveToNext())
        {
            test_id = cursor.getInt(0);
            user_id = cursor.getInt(1);
            test_starting_time = cursor.getString(2).toString();
            test_ending_time = cursor.getString(3).toString();
            test_type = cursor.getString(4).toString();
            image_type = cursor.getString(5).toString();
            interval_duration = Integer.parseInt(cursor.getString(6).toString());
            number_of_points = cursor.getInt(7);
            painter_width = cursor.getInt(8);
            language_during_test = cursor.getString(9).toString();
            is_scale_during_test = cursor.getString(10).toString();
            int a = 0;
            test_starting_time_number_only = "";
            test_ending_time_number_only = "";
            Log.d("STRINGTEST", test_starting_time);
            for (a = 0 ; a < test_starting_time.length() ; a++)
            {
                if (Character.isDigit(test_starting_time.charAt(a)))
                {
                    test_starting_time_number_only = test_starting_time_number_only + test_starting_time.charAt(a);
                }
            }
            Log.d("STRINGTEST", test_starting_time_number_only);
            Log.d("STRINGTEST", test_ending_time);
            for (a = 0 ; a < test_ending_time.length() ; a++)
            {
                if (Character.isDigit(test_ending_time.charAt(a)))
                {
                    test_ending_time_number_only = test_ending_time_number_only + test_ending_time.charAt(a);
                }
            }
            Log.d("STRINGTEST", test_ending_time_number_only);
            query1 = "SELECT * FROM User WHERE user_id = ?";
            parameter1 = new String [] {String.valueOf(user_id)};
            cursor1 = database.rawQuery(query1, new String [] {String.valueOf(user_id)});
            while (cursor1.moveToNext())
            {
                name = cursor1.getString(1).toString();
                age = Integer.parseInt(cursor1.getString(2).toString());
                gender = cursor1.getString(3).toString();
                education = cursor1.getString(4).toString();
                rating_score = Double.parseDouble(cursor1.getString(5).toString());
                current_receive_treatment = cursor1.getString(6).toString();
                registration_code = cursor1.getString(7).toString();
            }
            if (cursor1 != null)
            {
                cursor1.close();
            }

            output_string = output_string + "TEST INFORMATION" + "\n";

            output_string = output_string + "test_id = " + String.valueOf(test_id) + "\n";
            // output_csv_strings.add("test_id = " + String.valueOf(test_id) + "\n");
            output_string = output_string + "user_id = " + String.valueOf(user_id) + "\n";
            // output_csv_strings.add("user_id = " + String.valueOf(user_id) + "\n");
            output_string = output_string + "test_starting_time = " + test_starting_time + "\n";
            // output_csv_strings.add("test_starting_time = " + test_starting_time + "\n");
            output_string = output_string + "test_ending_time = " + test_ending_time + "\n";
            // output_csv_strings.add("test_ending_time = " + test_ending_time + "\n");
            output_string = output_string + "test_type = " + test_type + "\n";
            // output_csv_strings.add("test_type = " + test_type + "\n");
            output_string = output_string + "image_type = " + image_type + "\n";
            // output_csv_strings.add("image_type = " + image_type + "\n");
            output_string = output_string + "interval duration = " + String.valueOf(interval_duration) + "\n";
            // output_csv_strings.add("interval duration = " + String.valueOf(interval_duration) + "\n");


            output_string = output_string + "number of points = " + number_of_points + "\n";

            output_string = output_string + "painter_width = " + String.valueOf(painter_width) + "\n";

            output_string = output_string + "language_during_test = " + language_during_test + "\n";

            output_string = output_string + "is_scale_during_test = " + is_scale_during_test + "\n";

            output_string = output_string + "TESTEE INFORMATION" + "\n";

            output_string = output_string + "user id = " + user_id + "\n";

            output_string = output_string + "registration code = " + registration_code + "\n";

            output_string = output_string + "user name = " + name + "\n";
            // output_csv_strings.add("user name = " + name + "\n");
            output_string = output_string + "age = " + String.valueOf(age) + "\n";
            // output_csv_strings.add("age = " + String.valueOf(age) + "\n");
            output_string = output_string + "gender = " + gender + "\n";
            // output_csv_strings.add("gender = " + gender + "\n");
            output_string = output_string + "education = " + education + "\n";
            // output_csv_strings.add("education = " + education + "\n");
            output_string = output_string + "rating score = " + String.valueOf(rating_score) + "\n";
            // output_csv_strings.add("rating score = " + String.valueOf(rating_score) + "\n");
            output_string = output_string + "current receive treatment = " + current_receive_treatment + "\n";
            // output_csv_strings.add("current receive treatment = " + current_receive_treatment + "\n");

            output_string = output_string + "DEVICE INFORMATION" + "\n";

            output_string = output_string + "device brand = " + Sharing.device_brand + "\n";
            output_string = output_string + "device model = " + Sharing.device_model + "\n";
            output_string = output_string + "device product name = " + Sharing.device_product_name + "\n";
            output_string = output_string + "device manufacturer = " + Sharing.device_product_name + "\n";
            output_string = output_string + "device screen height in pixels = " + Sharing.device_height_in_pixels + "\n";
            output_string = output_string + "device screen width in pixels = " + Sharing.device_width_in_pixels + "\n";
            output_string = output_string + "device screen testing/drawing height in pixels = " + Sharing.device_testing_area_height + "\n";
            output_string = output_string + "device screen testing/drawing width in pixels = " + Sharing.device_testing_area_width + "\n";
            output_string = output_string + "device navigation bar height = " + Sharing.device_navigation_bar_height + "\n";
            output_string = output_string + "device Android system version code = " + Sharing.device_system_version_code + "\n";

            output_string = output_string + "NEUROGRAPH INFORMATION" + "\n";
            output_string = output_string + "Neurograph version = " + BuildConfig.VERSION_NAME + "\n";

            output_string = output_string + "TEST POINT DATA INFORMATION" + "\n";

            query2 = "SELECT * FROM Data WHERE test_id = ?";
            parameter2 = new String [] {String.valueOf(test_id)};
            cursor2 = database.rawQuery(query2, new String [] {String.valueOf(test_id)});
            while (cursor2.moveToNext())
            {
                timestamp_of_point = cursor2.getString(2).toString();
                x = cursor2.getFloat(3);
                y = cursor2.getFloat(4);
                pressure = cursor2.getFloat(5);
                touch_point_size = cursor2.getFloat(6);
                point_serial_number = cursor2.getInt(7);
                tool_type = cursor2.getInt(8);
                String new_line = String.valueOf(point_serial_number) + " " + timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + " " + String.valueOf(touch_point_size) + " " + String.valueOf(tool_type) + "\n";
                output_string = output_string + new_line;

                Log.d("TIMESSTAMP", timestamp_of_point);
                time_year = timestamp_of_point.split(" ")[0].split("-")[0];
                time_month = timestamp_of_point.split(" ")[0].split("-")[1];
                time_day = timestamp_of_point.split(" ")[0].split("-")[2];
                Log.d("testingsss", timestamp_of_point.split(" ")[1].split(":")[0]);
                time_hour = timestamp_of_point.split(" ")[1].split(":")[0];
                time_minute = timestamp_of_point.split(" ")[1].split(":")[1];
                time_second = timestamp_of_point.split(" ")[1].split(":")[2];
                Log.d("testingggg", String.valueOf(time_second.contains(".")));
                int k = 0;
                int count = 0;
                time_millisecond = "";
                for (k = 0 ; k < time_second.length() ; k++)
                {
                    if (count == 1)
                    {
                        time_millisecond = time_millisecond + time_second.charAt(k);
                    }
                    if (time_second.charAt(k) == '.')
                    {
                        count = 1;
                    }
                }
                String new_time_second = "";
                k = 0;
                count = 0;
                for (k = 0 ; k < time_second.length() ; k++)
                {
                    if (time_second.charAt(k) != '.')
                    {
                        new_time_second = new_time_second + time_second.charAt(k);
                    }
                    else
                    {
                        break;
                    }
                }
                /*
                if (new_time_second.length() == 1)
                {
                    new_time_second = "0" + new_time_second;
                }
                if (time_millisecond.length() == 1)
                {
                    time_millisecond = "00" + time_millisecond;
                }
                else if (time_millisecond.length() == 2)
                {
                    time_millisecond = "0" + time_millisecond;
                }
                if (time_month.length() == 1)
                {
                    time_month = "0" + time_month;
                }
                if (time_minute.length() == 1)
                {
                    time_minute = "0" + time_minute;
                }
                */
                String new_csv_line = point_serial_number + "," + time_year + "," + time_month + "," + time_day + "," + time_hour + "," + time_minute + "," + new_time_second + "," + time_millisecond + "," + String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(pressure) + "," + String.valueOf(touch_point_size) + "," + String.valueOf(tool_type) + "," + String.valueOf(test_id) + "," + test_type + "," + image_type + "," + String.valueOf(interval_duration) + "," + String.valueOf(number_of_points) + "," + test_starting_time_number_only.substring(0, 8) + "," + test_starting_time_number_only.substring(8) + "," + test_ending_time_number_only.substring(0, 8) + "," + test_ending_time_number_only.substring(8) + "," + String.valueOf(painter_width) + "," + language_during_test + "," + is_scale_during_test + "," + String.valueOf(user_id) + "," + registration_code + "," + name + "," + String.valueOf(age) + "," + gender + "," + education + "," + rating_score + "," + current_receive_treatment + "," + Sharing.device_brand + "," + Sharing.device_model + "," + Sharing.device_product_name + "," + Sharing.device_manufacturer + "," + Sharing.device_height_in_pixels + "," + Sharing.device_width_in_pixels + "," + Sharing.device_testing_area_height + "," + Sharing.device_testing_area_width + "," + Sharing.device_navigation_bar_height + "," + Sharing.device_system_version_code + "," + BuildConfig.VERSION_NAME + "\n";
                output_csv_strings.add(new_csv_line);
            }

            output_string = output_string + "\n";

            if (cursor2 != null)
            {
                cursor2.close();
            }
            Log.d("TAG_OUTPUT", output_string);

        }
        if (cursor != null)
        {
            cursor.close();
        }
        Sharing.csv_string_arraylist = output_csv_strings;

        if (database != null)
        {
            database.close();
        }
        if (databaseHelper != null)
        {
            databaseHelper.close();
        }

        return output_string;
    }


    // If the notification permission is not granted, direct the user to go to the system setting to turn it on.
    private void goToSetting(){
        if (Build.VERSION.SDK_INT >= 26) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", SendDataEmailActivity.this.getApplicationContext().getPackageName());
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", SendDataEmailActivity.this.getApplicationContext().getPackageName());
            intent.putExtra("app_uid", SendDataEmailActivity.this.getApplicationInfo().uid);
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT < 21)
        {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setData(Uri.fromParts("package", SendDataEmailActivity.this.getApplicationContext().getPackageName(), null));
            startActivity(intent);
        }
    }




    // Check whether the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permission is granted or not.
    private boolean checkPermission() {
        int read_permission = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int write_permission = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return read_permission == PackageManager.PERMISSION_GRANTED  && write_permission == PackageManager.PERMISSION_GRANTED;
    }


    // A worker thread which is used to send email, this is a new thread which runs in the background.
    // Close the app during the sending process will kill this thread, so
    // DO NOT CLOSE THE APP WHILE SENDING THE EMAIL.

    private void sendEmailWorker ()
    {
        try
        {
            content = generate_string_from_database();
            //output_csv_strings = generate_csv_string_from_database();
            //Sharing.csv_string_arraylist = output_csv_strings;

            db_file_path = getApplication().getDatabasePath("information") + ".db";
            Sharing.db_file_path = db_file_path;

            emailSender.sendMessage("smtp.gmail.com", "neurographdataservice@gmail.com", "gudjhxgh54376912@*:", recipient, subject, content);
            //emailSender.sendMessage("smtp.gmail.com", "neurographdataservice@gmail.com", "gudjhxgh54376912@*:", recipient, subject, content);

            Intent push = new Intent ();
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, push, 0);

            NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel channel = null;
                if (channel == null)
                {
                    channel = new NotificationChannel("my_channel_01", getString(R.string.channel_name), importance);
                    channel.setDescription(getString(R.string.neurograph_notification));
                    channel.enableLights(true);
                    channel.setLightColor(Color.RED);
                    channel.enableVibration(true);
                    channel.setVibrationPattern(new long []{100, 200, 300, 400, 500, 400, 300, 200, 100});
                    manager.createNotificationChannel(channel);
                }
                Notification notification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.icon_new_ps_transparent) // TODO ICON
                        .setContentTitle(getString(R.string.successful_message_email_file))
                        .setContentInfo("Neurograph Notification")
                        .setContentText(Sharing.file_path)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(false)
                        .setFullScreenIntent(contentIntent, true)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .setOngoing(false)
                        .setChannelId("my_channel_01")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();
                manager.notify(90, notification);
            }
            else
            {
                Notification notification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.icon_new_ps_transparent) // TODO ICON
                        .setTicker("Neurograph Notification")
                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentTitle(getResources().getString(R.string.successful_message_email_file))
                        .setContentInfo("Neurograph Notification")
                        .setContentText(Sharing.file_path)
                        .setAutoCancel(false)
                        .setFullScreenIntent(contentIntent, true)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();
                manager.notify(0, notification);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        if (should_delete_test_data)
        {
            databaseHelperD = new MyDatabaseHelper (SendDataEmailActivity.this, databaseName, null, databaseVersion);
            databaseD = databaseHelperD.getWritableDatabase();
            databaseD.delete("Data", "", new String[] {});
            databaseD.delete("Test", "", new String[] {});
            if (databaseD != null)
            {
                databaseD.close();
            }
            if (databaseHelperD != null)
            {
                databaseHelperD.close();
            }
        }



        Sharing.stop_showing_process = 0;
        Intent broadcastMessage = new Intent ();
        broadcastMessage.setAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        broadcastMessage.putExtra("stop_showing_process", "1");
        sendBroadcast(broadcastMessage);




    }

    // Get total number of tests
    public int getNumber_of_test_in_total ()
    {
        int answer = 0;

        databaseHelper2 = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        databaseHelper2.getReadableDatabase();

        database2 = databaseHelper2.getReadableDatabase();

        String query = "SELECT COUNT () FROM Test";
        String [] parameters = new String[]{};
        Cursor cursor = database2.rawQuery(query, parameters);
        while (cursor.moveToNext())
        {
            answer = cursor.getInt(0);
        }

        if (cursor != null)
        {
            cursor.close();
        }
        if (database2 != null)
        {
            database2.close();
        }
        if (databaseHelper2 != null)
        {
            databaseHelper2.close();
        }

        return answer;
    }

    // Get total number of points in all tests
    public int getNumber_of_item_in_total ()
    {
        databaseHelper1 = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper1.getReadableDatabase();

        database1 = databaseHelper1.getReadableDatabase();

        int answer = 0;

        String query = "SELECT number_of_points FROM Test";
        String [] parameter = new String [] {};
        Cursor cursor = database1.rawQuery(query, parameter);
        while (cursor.moveToNext())
        {
            answer = answer + cursor.getInt(0);
        }

        if (cursor != null)
        {
            cursor.close();
        }
        if (database1 != null)
        {
            database1.close();
        }
        if (databaseHelper1 != null)
        {
            databaseHelper1.close();
        }
        return answer;
    }

    // onCreate() method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(SendDataEmailActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(SendDataEmailActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getNumber_of_test_in_total() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
            builder.setTitle(R.string.no_test_data);
            builder.setCancelable(false);
            builder.setMessage(R.string.no_test_data_to_send);
            builder.setPositiveButton(getResources().getString(R.string.got_it), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent (SendDataEmailActivity.this, DataListActivity.class);
                    startActivity(intent);
                    SendDataEmailActivity.this.finish();
                }
            });
            builder.create();
            builder.show();
        }

        // REQUEST PERMISSION
        ActivityCompat.requestPermissions(SendDataEmailActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);
        //ActivityCompat.requestPermissions(SendDataEmailActivity.this, new String[]{READ_EXTERNAL_STORAGE}, 200);
        //ActivityCompat.requestPermissions(SendDataEmailActivity.this, new String[]{INTERNET, ACCESS_NETWORK_STATE}, 200);

        help_imageview = (ImageView) findViewById(R.id.send_data_email_help_imageview);
        help_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                builder.setTitle(getResources().getString(R.string.help));
                builder.setCancelable(false);
                builder.setMessage(".txt = text file" + "\n" + ".csv = comma separated value file" + "\n" + ".db = database file" + "\n" + "Readme = Instructions and Explanations");
                builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here
                        // Blank
                    }
                });
                builder.create();
                builder.show();
            }
        });

        delete_test_data_switch = (Switch) findViewById(R.id.send_email_delete_data_switch);
        delete_test_data_switch.setChecked(true);

        should_delete_test_data = true;

        delete_test_data_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    should_delete_test_data = true;
                }
                else
                {
                    should_delete_test_data = false;
                }
            }
        });

        send_button = (Button) findViewById(R.id.send_email_send_button);
        cancel_button = (Button) findViewById(R.id.send_email_cancel_button);

        recipient_edittext = (EditText) findViewById(R.id.send_email_recipient_edittext);
        subject_edittext = (EditText) findViewById(R.id.send_email_subject_editview);

        if (!Sharing.previous_email_address.equalsIgnoreCase(""))
        {
            recipient_edittext.setText(Sharing.previous_email_address);
        }

        content_checkbox = (CheckBox) findViewById(R.id.send_email_checkbox);
        Sharing.show_as_content = true;

        // INITIALLY SET CHECKBOX TO CHECKED (TRUE)
        content_checkbox.setChecked(true);

        if (!NotificationManagerCompat.from(SendDataEmailActivity.this.getApplicationContext()).areNotificationsEnabled())
        {
            Log.d("NOTIFICATIONHHH", "NOTIFICATION");
            AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
            builder.setTitle(R.string.notification_permission_title);
            builder.setCancelable(false);
            builder.setMessage(R.string.turn_on_notification);
            builder.setNegativeButton(R.string.button_dismiss, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // WARNING
                    // LEAVE THIS AS EMPRT BLOCK !!!
                }
            });
            builder.setPositiveButton(R.string.go_to_system_setting, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToSetting();
                    // The program will go to setting page if the user willing to turn on the notification;
                }
            });
            builder.create();
            builder.show();
        }

        content_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Sharing.show_as_content = true;
                }
                else
                {
                    Sharing.show_as_content = false;
                }
            }
        });

        csv_checkbox = (CheckBox) findViewById(R.id.send_data_email_csv_checkbox);
        txt_checkbox = (CheckBox) findViewById(R.id.send_data_email_txt_checkbox);
        db_checkbox = (CheckBox) findViewById(R.id.send_data_email_db_checkbox);
        readme_checkbox = (CheckBox) findViewById(R.id.send_data_email_readme_checkbox);

        csv_checkbox.setChecked(true);
        Sharing.email_csv = true;
        txt_checkbox.setChecked(true);
        Sharing.email_txt = true;
        db_checkbox.setChecked(true);
        Sharing.email_db = true;
        readme_checkbox.setChecked(true);
        Sharing.email_readme = true;

        csv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Sharing.email_csv = true;
                }
                else
                {
                    Sharing.email_csv = false;
                }
            }
        });

        txt_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Sharing.email_txt = true;
                }
                else
                {
                    Sharing.email_txt = false;
                }
            }
        });

        db_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Sharing.email_db = true;
                }
                else
                {
                    Sharing.email_db = false;
                }
            }
        });

        readme_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Sharing.email_readme = true;
                }
                else
                {
                    Sharing.email_readme = false;
                }
            }
        });



        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CHECKING THE VALIDITY OF INFORMATION ENTERED

                recipient = recipient_edittext.getText().toString();
                subject = subject_edittext.getText().toString();

                readyToSend = true;
                networkAvailable = false;

                Context context = getApplicationContext();
                final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected())
                {
                    networkAvailable = true;
                }

                if (networkAvailable == false)
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(R.string.network_error);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.network_unavailable_message);
                    builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Should do nothing here
                            // WARNING
                            // LEAVE THIS AS EMPRT BLOCK !!!
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (recipient.isEmpty())
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(R.string.invalid_information);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.email_address_empty);
                    builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here
                            // LEAVE IT AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (!recipient.contains("@") || !recipient.contains(".") || recipient.contains(" "))
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(getResources().getString(R.string.invalid_information));
                    builder.setCancelable(false);
                    builder.setMessage(R.string.email_address_invalid);
                    builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here;
                            // LEAVE THIS AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (subject.isEmpty())
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(getResources().getString(R.string.invalid_information));
                    builder.setCancelable(false);
                    builder.setMessage(R.string.email_subject_empty);
                    builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here
                            // LEAVE THIS AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (!checkPermission())
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(R.string.permission_not_granted);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.permission_not_granted_message);
                    builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here
                            // LEAVE THIS AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (!Sharing.email_csv && !Sharing.email_txt && !Sharing.email_readme && !Sharing.email_db)
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(R.string.please_choose_file_type);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.please_choose_at_least_one_file_type_to_send);
                    builder.setPositiveButton(getResources().getString(R.string.got_it), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Should do nothing here
                            // blank
                        }
                    });
                    builder.create();
                    builder.show();
                }

                confirm_to_send = false;

                if (readyToSend)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle(R.string.confirm);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.confirm_sending_email);
                    builder.setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirm_to_send = false;
                            // should do nothing here
                        }
                    });

                    builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirm_to_send = true;
                            Log.d("email_sig", String.valueOf(confirm_to_send));

                            if (readyToSend && confirm_to_send)
                            {
                                //sendEmailWorker();

                                Sharing.previous_email_address = recipient;

                                Sharing.number_of_item_in_total = getNumber_of_item_in_total();
                                send_button_clicked = "send";
                                /*
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SendDataEmailActivity.this);
                                builder1.setTitle(R.string.successfully_sent);
                                builder1.setCancelable(false);
                                builder1.setMessage(R.string.success_message1);
                                builder1.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
                                        startActivity(intent);
                                        SendDataEmailActivity.this.finish();
                                    }
                                });
                                builder1.setNegativeButton(R.string.copy_file_path, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                                        clipboardManager.setPrimaryClip(clipData);
                                        Toast.makeText(SendDataEmailActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
                                        startActivity(intent);
                                        SendDataEmailActivity.this.finish();
                                    }
                                });
                                builder1.create();
                                builder1.show();
                                */

                                Intent intent = new Intent(SendDataEmailActivity.this, DisplaySendingActivity.class);
                                startActivity(intent);
                                SendDataEmailActivity.this.finish();


                            }
                        }
                    });
                    builder.create();
                    builder.show();
                }

            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                builder.setTitle(R.string.cancel_sending);
                builder.setCancelable(false);
                builder.setMessage(R.string.quit_message);
                builder.setNegativeButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here
                        // null
                    }
                });

                builder.setPositiveButton(R.string.exit_without_sending, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
                        startActivity(intent);
                        SendDataEmailActivity.this.finish();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {

            /*
            Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
            startActivity(intent);
            SendDataEmailActivity.this.finish();
            */

            AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
            builder.setTitle(R.string.cancel_sending);
            builder.setCancelable(false);
            builder.setMessage(R.string.quit_message);
            builder.setNegativeButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // null
                }
            });

            builder.setPositiveButton(R.string.exit_without_sending, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
                    startActivity(intent);
                    SendDataEmailActivity.this.finish();
                }
            });
            builder.create();
            builder.show();


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStart ()
    {
        super.onStart();
    }

    @Override
    public void onPause ()
    {
        super.onPause();
    }

    @Override
    public void onStop ()
    {
        if (send_button_clicked.equalsIgnoreCase("send"))
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendEmailWorker();
                }
            }).start();
        }
        super.onStop();
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
    public void onDestroy ()
    {
        super.onDestroy();
    }

}
