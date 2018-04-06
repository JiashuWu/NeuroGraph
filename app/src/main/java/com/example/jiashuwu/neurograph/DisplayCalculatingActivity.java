package com.example.jiashuwu.neurograph;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DisplayCalculatingActivity extends AppCompatActivity {

    private int user_id;

    private ProgressDialog progressDialog;

    private MyReceiver myReceiver;

    private int frequency_per_second = 290;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calculating);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        DisplayCalculatingActivity.this.registerReceiver(myReceiver, intentFilter);


        Sharing.stop_showing_process = 0;
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
                while(i < 100 && Sharing.stop_showing_process == 0)
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
                        if (i != 99)
                        {
                            i++;
                        }
                        // i++;

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                progressDialog.setProgress(100);
                progressDialog.dismiss();
                Intent intent = new Intent(DisplayCalculatingActivity.this, ParallelLinePracticeResultActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                startActivity(intent);
                DisplayCalculatingActivity.this.finish();

            }
        }).start();
    }

    @Override
    public void onStop ()
    {
        unregisterReceiver(myReceiver);
        super.onStop();

    }
}
