package com.example.jiashuwu.neurograph;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class ThankYouActivity extends AppCompatActivity {

    private int user_id;

    private boolean initial_isScale;

    private ProgressBar progressBar;

    private float progress;
    private int count = 0;
    private TextView numbers_textview;
    private TextView percent_textview;



    private ProgressDialog progressDialog;

    private int frequency_per_second = 330;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ThankYouActivity.this, Sharing.isScale);
        }
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        initial_isScale = Sharing.isScale;
        setContentView(R.layout.activity_thank_you);

        Log.d("ORDER", "ON_START");

        //progressBar = (ProgressBar) findViewById(R.id.thank_you_activity_progressbar);


        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        /*
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage (Message message)
            {

            }
        };
        */

        /*
        count = 0;
        new Thread ()
        {
            public void run ()
            {
                while (count != Sharing.number_of_item_in_total)
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    count = count + 1;
                    int number1 = (int) ((double) count * 100 / (double) Sharing.number_of_item_in_total);
                    float number2 = (float) ((float) count * 100 / (float) Sharing.number_of_item_in_total);
                    progressBar.setProgress(number1);
                    //numbers_textview.setText(count + "/" + Sharing.number_of_item_in_total);
                    //percent_textview.setText(number2 + "%");

                }
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }.start();
        */

        Log.d("processing", String.valueOf(Sharing.number_of_item_in_total));
        Log.d("destroy", "on_create");


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Thank you");
        progressDialog.setMax(Sharing.number_of_item_in_total);
        progressDialog.setMessage("Thank you for your participation. Please waiting while processing");
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
                        Log.d("destroy", String.valueOf(i));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                progressDialog.setProgress(100);
                progressDialog.dismiss();
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }).start();



        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThankYouActivity.this , TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                Sharing.isScale = initial_isScale;
                // Log.d("isScale", String.valueOf(Sharing.isScale));
                ThankYouActivity.this.startActivity(intent);
                ThankYouActivity.this.finish();
            }
        }, waiting_time);
        */




    }
}
