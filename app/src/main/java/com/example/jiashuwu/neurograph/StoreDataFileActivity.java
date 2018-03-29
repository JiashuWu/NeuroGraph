package com.example.jiashuwu.neurograph;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import javax.mail.Store;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class StoreDataFileActivity extends AppCompatActivity {

    private Button button_generate;

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;
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
    private int age;
    private String gender;
    private String education;
    private int rating_score;
    private String current_receive_treatment;
    private String timestamp_of_point;
    private float x;
    private float y;
    private float pressure;
    private float touch_point_size;

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

    private void goToSetting(){
        if (Build.VERSION.SDK_INT >= 26) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", StoreDataFileActivity.this.getApplicationContext().getPackageName());
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", StoreDataFileActivity.this.getApplicationContext().getPackageName());
            intent.putExtra("app_uid", StoreDataFileActivity.this.getApplicationInfo().uid);
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT < 21)
        {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.setData(Uri.fromParts("package", StoreDataFileActivity.this.getApplicationContext().getPackageName(), null));
            startActivity(intent);
        }
    }

    public String generate_string_from_database ()
    {
        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        database = databaseHelper.getReadableDatabase();

        String output_string = "";

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
        while (cursor.moveToNext())
        {
            test_id = cursor.getInt(0);
            user_id = cursor.getInt(1);
            test_starting_time = cursor.getString(2).toString();
            test_ending_time = cursor.getString(3).toString();
            test_type = cursor.getString(4).toString();
            image_type = cursor.getString(5).toString();
            interval_duration = Integer.parseInt(cursor.getString(6).toString());
            query1 = "SELECT * FROM User WHERE user_id = ?";
            parameter1 = new String [] {String.valueOf(user_id)};
            cursor1 = database.rawQuery(query1, new String [] {String.valueOf(user_id)});
            while (cursor1.moveToNext())
            {
                name = cursor1.getString(1).toString();
                age = Integer.parseInt(cursor1.getString(2).toString());
                gender = cursor1.getString(3).toString();
                education = cursor1.getString(4).toString();
                rating_score = Integer.parseInt(cursor1.getString(5).toString());
                current_receive_treatment = cursor1.getString(6).toString();
            }
            if (cursor1 != null)
            {
                cursor1.close();
            }
            output_string = output_string + "test_id = " + String.valueOf(test_id) + "\n";
            output_string = output_string + "user_id = " + String.valueOf(user_id) + "\n";
            output_string = output_string + "test_starting_time = " + test_starting_time + "\n";
            output_string = output_string + "test_ending_time = " + test_ending_time + "\n";
            output_string = output_string + "test_type = " + test_type + "\n";
            output_string = output_string + "image_type = " + image_type + "\n";
            output_string = output_string + "interval duration = " + String.valueOf(interval_duration) + "\n";
            output_string = output_string + "user name = " + name + "\n";
            output_string = output_string + "age = " + String.valueOf(age) + "\n";
            output_string = output_string + "gender = " + gender + "\n";
            output_string = output_string + "education = " + education + "\n";
            output_string = output_string + "rating score = " + String.valueOf(rating_score) + "\n";
            output_string = output_string + "current receive treatment = " + current_receive_treatment + "\n";
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
                String new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + " " + String.valueOf(touch_point_size) + "\n";
                output_string = output_string + new_line;
            }
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

        return output_string;
    }

    private boolean checkPermission() {
        int read_permission = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int write_permission = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return read_permission == PackageManager.PERMISSION_GRANTED  && write_permission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextScaleUtils.scaleTextSize(StoreDataFileActivity.this, Sharing.isScale);

        initLocaleLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data_file);

        ActivityCompat.requestPermissions(StoreDataFileActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);

        if (!NotificationManagerCompat.from(StoreDataFileActivity.this.getApplicationContext()).areNotificationsEnabled())
        {
            Log.d("NOTIFICATIONHHH", "NOTIFICATION");
            AlertDialog.Builder builder = new AlertDialog.Builder(StoreDataFileActivity.this);
            builder.setTitle("Notification permission");
            builder.setCancelable(false);
            builder.setMessage("Turn on notification");
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // WARNING
                    // LEAVE THIS AS EMPRT BLOCK !!!
                }
            });
            builder.setPositiveButton("Go To Setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToSetting();
                    // The program will go to setting page if the user willing to turn on the notification;
                }
            });
            builder.create();
            builder.show();
        }

        button_generate = (Button) findViewById(R.id.store_data_file_generate_button);
        button_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output_file_name = "NeurographOutputDataFile.txt";

                File file = new File(Environment.getExternalStorageDirectory(), "Neurograph");
                if (!file.exists())
                {
                    file.mkdirs();
                }


                File output_file = new File(Environment.getExternalStorageDirectory(), "/" + "NeurographDataOutput.txt");
                try
                {
                    FileWriter fileWriter = new FileWriter(output_file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(generate_string_from_database());
                    bufferedWriter.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                String file_path = Environment.getExternalStorageDirectory() + "/" + "NeurographDataOutput.txt";
                Log.d("file_path1", file_path);
                Sharing.file_path = file_path;

                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_LOW;
                    NotificationChannel channel = null;
                    if (channel == null)
                    {
                        channel = new NotificationChannel("my_channel_01", getString(R.string.channel_name), importance);
                        channel.setDescription("Neurograph_Notification");
                        channel.enableLights(true);
                        channel.setLightColor(Color.RED);
                        channel.enableVibration(true);
                        channel.setVibrationPattern(new long []{100, 200, 300, 400, 500, 400, 300, 200, 100});
                        manager.createNotificationChannel(channel);
                    }
                    Notification notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Email sent and file saved")
                            .setContentInfo("Neurograph Notification")
                            .setContentText(Sharing.file_path)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(false)
                            .setOngoing(true)
                            .setChannelId("my_channel_01")
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();
                    manager.notify(90, notification);
                }
                else
                {
                    Notification notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setTicker("Neurograph Notification")
                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentTitle("Email sent and file saved")
                            .setContentInfo("Neurograph Notification")
                            .setContentText(Sharing.file_path)
                            .setAutoCancel(false)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();
                    manager.notify(0, notification);
                }

                AlertDialog.Builder builder1 = new AlertDialog.Builder(StoreDataFileActivity.this);
                builder1.setTitle("Successfully Saved");
                builder1.setCancelable(false);
                builder1.setMessage("The file has been saved into the file system.");
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(StoreDataFileActivity.this, DataListActivity.class);
                        startActivity(intent);
                        StoreDataFileActivity.this.finish();
                    }
                });
                builder1.setNegativeButton("Copy File Path", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(StoreDataFileActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(StoreDataFileActivity.this, DataListActivity.class);
                        startActivity(intent);
                        StoreDataFileActivity.this.finish();
                    }
                });
                builder1.create();
                builder1.show();
            }
        });
    }
}
