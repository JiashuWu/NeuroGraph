package com.example.jiashuwu.neurograph;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProgressActivity extends AppCompatActivity {

    private int count = 0;
    private ProgressDialog progressDialog;

    private TextView file_location_textview;

    private Button finish_button;
    private Button copy_file_path_button;

    private int frequency_per_second = 280;

    private final Handler handler = new Handler()
    {
        public void handleMessgae (Message message)
        {
            final int what = message.what;
            switch (what)
            {
                case 0: updateTextview();
            }
        }
    };

    private void updateTextview ()
    {
        file_location_textview.setText(Sharing.file_path);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_progress);

        Log.d("PROGRESSES", String.valueOf(Sharing.number_of_item_in_total));

        file_location_textview = (TextView) findViewById(R.id.display_progress_file_location_textview);
        finish_button = (Button) findViewById(R.id.display_progress_finish_button);
        copy_file_path_button = (Button) findViewById(R.id.display_progress_copy_file_path_button);

        file_location_textview.setText("You can choose to copy the file location to Clipboard by simply clicking Copy File Path button. ");

        copy_file_path_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DisplayProgressActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();
            }
        });

        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayProgressActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplayProgressActivity.this.finish();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMax(Sharing.number_of_item_in_total);
        progressDialog.setMessage("Please waiting while processing. Please DO NOT close the app while processing. ");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 100)
                {
                    try
                    {
                        int waiting_time =  Sharing.number_of_item_in_total / frequency_per_second *1000 / 100;
                        if (waiting_time == 0)
                        {
                            waiting_time = 19;
                        }
                        Log.d("processing", String.valueOf(Sharing.number_of_item_in_total));
                        Log.d("processing", String.valueOf(waiting_time));
                        Thread.sleep(waiting_time);
                        if (Sharing.number_of_item_in_total / 100 == 0)
                        {
                            progressDialog.incrementProgressBy(1);
                        }
                        else
                        {
                            progressDialog.incrementProgressBy((int)Math.ceil((double) Sharing.number_of_item_in_total / 100));
                        }
                        i++;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                progressDialog.setProgress(100);
                progressDialog.dismiss();
            }
        }).start();


        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayProgressActivity.this);
        builder.setTitle("Successfully Saved");
        builder.setCancelable(false);
        builder.setMessage("The file has been saved into the file system.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DisplayProgressActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplayProgressActivity.this.finish();
            }
        });
        builder.setNegativeButton("Copy File Path", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("file_path",Sharing.file_path);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DisplayProgressActivity.this, "File Path copied to clipboard.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DisplayProgressActivity.this, DataListActivity.class);
                startActivity(intent);
                DisplayProgressActivity.this.finish();
            }
        });
        builder.create();
        builder.show();
        */




        /*
        count = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBar.getProgress() != 100)
                {
                    try
                    {
                        Thread.sleep(100);
                        progressBar.incrementProgressBy(1);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        */


    }
}
