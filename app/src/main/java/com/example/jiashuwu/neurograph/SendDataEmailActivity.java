package com.example.jiashuwu.neurograph;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private String databaseName = "information.db";
    private int databaseVersion = 1;

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



    // GENERATE STRING FROM THE DATABASE

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
                String new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + "\n";
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(SendDataEmailActivity.this, Sharing.isScale);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_email);

        send_button = (Button) findViewById(R.id.send_email_send_button);
        cancel_button = (Button) findViewById(R.id.send_email_cancel_button);

        recipient_edittext = (EditText) findViewById(R.id.send_email_recipient_edittext);
        subject_edittext = (EditText) findViewById(R.id.send_email_subject_editview);

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
                    builder.setTitle("Network Error");
                    builder.setCancelable(false);
                    builder.setMessage("Network not available");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                    builder.setTitle("Invalid Information");
                    builder.setCancelable(false);
                    builder.setMessage("Email address cannot be empty");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here
                            // LEAVE IT AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }
                else if (!recipient.contains("@") || !recipient.contains("."))
                {
                    readyToSend = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle("Invalid Information");
                    builder.setCancelable(false);
                    builder.setMessage("Email address invalid");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                    builder.setTitle("Invalid Information");
                    builder.setCancelable(false);
                    builder.setMessage("Subject cannot be empty");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // should do nothing here
                            // LEAVE THIS AS BLANK BLOCK
                        }
                    });
                    builder.create();
                    builder.show();
                }

                confirm_to_send = false;

                if (readyToSend)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SendDataEmailActivity.this);
                    builder.setTitle("Confirm");
                    builder.setCancelable(false);
                    builder.setMessage("Confirm sending this email ?");
                    builder.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirm_to_send = false;
                            // should do nothing here
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirm_to_send = true;
                            Log.d("email_sig", String.valueOf(confirm_to_send));

                            if (readyToSend && confirm_to_send)
                            {
                                try
                                {
                                    String content = generate_string_from_database();
                                    emailSender.sendMessage("smtp.gmail.com", "neurographdataservice@gmail.com", "gudjhxgh54376912@*:", recipient, subject, content);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                                Intent intent = new Intent(SendDataEmailActivity.this, DataListActivity.class);
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
                builder.setTitle("Cancel Sending");
                builder.setCancelable(false);
                builder.setMessage("Want to quit without sending?");
                builder.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here
                        // null
                    }
                });

                builder.setPositiveButton("Exit without sending", new DialogInterface.OnClickListener() {
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


}
