package com.example.jiashuwu.neurograph;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.io.File;

public class SettingPageActivity extends AppCompatActivity {

    private String language = "";
    private String fontsize = "";

    private Spinner setting_page_language_spinner;
    private Spinner setting_page_fontsize_spinner;

    private Button setting_page_start_button;
    private Button setting_page_data_list_button;
    private Button setting_page_introduction_button;
    private Button setting_page_tutorial_button;

    private ArrayAdapter language_adapter;
    private ArrayAdapter font_adapter;

    private boolean initial_isScale;

    private String initial_language;

    private long exitTime;

    private ImageView tutorial_image;
    private TextView tutorial_text;

    private ArrayList<String> title;
    private ArrayList<String> description;

    private ImageView blue_button;
    private ImageView light_blue_button;
    private ImageView green_button;
    private ImageView purple_button;
    private ImageView pink_button;
    private ImageView orange_button;

    private Context context;

    private String initial_colour = "";

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







    // WARNING: UNDER TESTING
    public void createTutorialAlertdialog () //// THIS IS UNDER TESTING
    {
        int counter = 0;
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        final View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle(title.get(counter));
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen1);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(description.get(counter));
        builder.setView(view);
        if (counter != 0)
        builder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tutorial_text.setText("222222 222222");
                builder.setView(view);
            }
        });
        builder.create();
        builder.show();

    }



    /*

    public void showAppTutorialChangeSetting ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Change Setting");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can change the language and font size setting here. ");
        builder.setPositiveButton(R.string.button_all_done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here
                // Blank
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSwitchUser();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSwitchUser ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Switch User");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can switch user here. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialChangeSetting();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialPersonal();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialPersonal ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("View and edit information");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can view or edit your personal information here. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSwitchUser();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDeleteData();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialDeleteData ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Delete Data");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_delete_1);
        tutorial_text.setText("Here you can delete all test data, delete test data prior to a specific data, or delete all data (This will restart the app) ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialPersonal();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialChooseFileType();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialChooseFileType ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose File Type");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can choose which type of file you want to store, you can choose all of them. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDeleteData();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialStoreDataFile();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialStoreDataFile ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Store data file");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_file_output_page);
        tutorial_text.setText("You can also store data file into Android file system, and send it to your PC by connecting your phone via a cable. You can copy the file path to clipboard. Easy to use! ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialChooseFileType();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSendEmail();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSendEmail ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Send data email");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_send_email_page);
        tutorial_text.setText("Want to outputting all test data? Send it via email. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialStoreDataFile();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialLongClickDelete();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialLongClickDelete ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Long click to delete");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_long_click_delete);
        tutorial_text.setText("Delete a piece of data by long clicking it. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSendEmail();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDataList();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialDataList ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Data list");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_data_list);
        tutorial_text.setText("Here you can all the test data, including test ID, test type, image type, detailed coordinates and more! ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialLongClickDelete();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialProcessing();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialProcessing ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Processing");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("After each test, it takes some time to process, please do not close the app during processing. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDataList();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialTestCanvas();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialTestCanvas ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Draw it");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_drawing);
        tutorial_text.setText("Draw the required shape here. You can click \"Clear\" to clear the canvas, and click \"Finish\" to finish the test");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialProcessing();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialWidth();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialWidth ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose painter width");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_width_selection);
        tutorial_text.setText("You can choose the painter width you preferred. We have thin, normal and thick width available. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialTestCanvas();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDuration();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialDuration ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose interval duration");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_duration_selection);
        tutorial_text.setText("You can choose the interval duration of the test. The interval duration only applies to dynamic tests. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialWidth();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialTestInstruction();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialTestInstruction ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Test instructions");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_test_instruction);
        tutorial_text.setText("This page contains some important test instructions. Remember to read them carefully.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDuration();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialImageSelection();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialImageSelection ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Select test image");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_image_selection);
        tutorial_text.setText("Here you can choose the background image of the test. We have spiral and pentagon.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialTestInstruction();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDynamicTestSelection();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }


    public void showAppTutorialDynamicTestSelection ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Select dynamic tests");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_dynamic_test_selection);
        tutorial_text.setText("You can choose a dynamic test here.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialImageSelection();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialStaticTestSelection();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialStaticTestSelection ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Select static tests");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_static_test_selection);
        tutorial_text.setText("You can select a static test here. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDynamicTestSelection();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelTest();
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialCircularMotionBackground ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose Background");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can choose red, blue or black dot as the background. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialStaticTestSelection();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCircularMotion();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialCircularMotion ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Circular Motion Test");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You need to draw a circle around a dot in this test.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCircularMotionBackground();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelTest();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialParallelTest ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Static Parallel Test");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("This is the static parallel test, you need to draw three horizontal parallel lines. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCircularMotion();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelPractice();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialParallelPracticeResult ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Practice Results");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can see whether the two parallel lines you draw are indeed paralle. These grading level will be used: Perfect, Good, Acceptable and Bad. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelTest();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelPractice();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialParallelPractice ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Practice makes perfect");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_parallel);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("This is a playground where you can practice drawing two parallel lines. Our specially designed algorithm will judge whether these two lines are indeed parallel. Have fun! ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelPracticeResult();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialInformationCollectionPage();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialInformationCollectionPage ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("InformationCollection");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_information_collection);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("We need to collect some information to mage the diagnostic more precise. The information include your name, gender, education, previous rating score etc. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialParallelPractice();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageFont();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialAccountCentre ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Account Centre");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("For new user, you need to register before using, for old user, you can simply type in your registration code and start the tests. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialInformationCollectionPage();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialIntroduction();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialIntroduction ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Introduction page");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can find an introduction of this app, including what is new in this version of release. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialAccountCentre();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageCache();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSettingPageCache ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Clean Cache");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can the cache of the app. This won't affect internal storage or database.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialIntroduction();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCopyright();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialCopyright ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("View Copyright");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can find the copyright information here.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageCache();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDevicePage();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialDevicePage ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("About your device");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("You can easily find all the information you need about your device in this page. This can help you figure out the useful information of your device including the size of testing area etc. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCopyright();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialInfoActivity();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialInfoActivity ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Information");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can find the information about this app.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDevicePage();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageTheme();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSettingPageTheme ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose your theme");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can choose your favourite theme colour. We have blue, light blue, green, purple, pink and orange theme.");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialInfoActivity();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageFont();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSettingPageFont ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Customise font size");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_choose_font_size);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can customise the font size, normal or large. The change will take effect immediately. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageTheme();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageLanguage();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }

    public void showAppTutorialSettingPageLanguage ()
    {
        LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
        builder.setTitle("Choose your language");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("Here you can choose your preferred language, our app support English, Simplified Chinese, Traditional Chinese, French, Dutch, German, Italian, Japanese, Russian, Spanish and Portuguese. The change will take effect immediately. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageFont();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPage();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }


    public void showAppTutorialSettingPage ()
    {
        Log.d("tutorial", "Starting_tutorials");
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Setting Page");
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_setting_page);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText("This is the setting page, you can change the app settings like language or font size, you can also find the app introduction information by clicking \"Introduction\" button. ");
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSettingPageLanguage();
            }
        });
        builder.setNeutralButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Should do nothing here;
                // Blank
            }
        });
        builder.create();
        builder.show();
    }
    */


    // This is used for cleaning cache
    public void deleteFileByDirectory (File directory)
    {
        if (directory != null && directory.exists() && directory.isDirectory())
        {
            String [] children = directory.list();
            for (int i = 0 ; i < children.length ; i++)
            {
                deleteFileByDirectory(new File(directory, children[i]));
            }
            directory.delete();
        }
        else if (directory != null && directory.isFile())
        {
            directory.delete();
        }
    }

    public void cleanExternalCache (Context context)
    {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED))
        {
            deleteFileByDirectory(context.getExternalCacheDir());
        }
    }

    public void cleanCache (Context context)
    {
        deleteFileByDirectory(context.getCacheDir());
        cleanExternalCache(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(SettingPageActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(SettingPageActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        Log.d("is_scale", String.valueOf(Sharing.isScale));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(SettingPageActivity.this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, 200);
        }
        else
        {
            Log.d("GRANTED", "GRANTED");
        }

        context = getApplicationContext();

        initial_isScale = Sharing.isScale;
        initial_language = Sharing.language;

        setting_page_language_spinner = (Spinner) findViewById(R.id.setting_page_language_spinner);
        setting_page_fontsize_spinner = (Spinner) findViewById(R.id.setting_page_font_size_spinner);

        setting_page_start_button = (Button) findViewById(R.id.setting_page_start_button);
        setting_page_data_list_button = (Button) findViewById(R.id.setting_page_data_list_button);
        setting_page_introduction_button = (Button) findViewById(R.id.setting_page_introduction_button);
        setting_page_tutorial_button = (Button) findViewById(R.id.setting_page_tutorial_button);

        final String [] language_list = getResources().getStringArray(R.array.language_array);
        language_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, language_list);
        language_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_language_spinner.setAdapter(language_adapter);

        final String [] fontsize_list = getResources().getStringArray(R.array.font_array);
        font_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontsize_list);
        font_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_page_fontsize_spinner.setAdapter(font_adapter);

        /*
        final Button trigger_button = (Button) findViewById(R.id.triggerrrrrrrrrrr);
        trigger_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SettingPageActivity.this, StaticBackgroundTestNewActivity.class);
                startActivity(intent);
                SettingPageActivity.this.finish();
            }
        });
        */

        //setting_page_language_spinner.setSelection(0);

        switch (Sharing.language)
        {
            case "English": setting_page_language_spinner.setSelection(0); break;
            case "Simplified Chinese": setting_page_language_spinner.setSelection(1); break;
            case "Traditional Chinese": setting_page_language_spinner.setSelection(2); break;
            case "Dutch": setting_page_language_spinner.setSelection(3);break;
            case "French": setting_page_language_spinner.setSelection(4);break;
            case "German": setting_page_language_spinner.setSelection(5);break;
            case "Italian": setting_page_language_spinner.setSelection(6);break;
            case "Japanese": setting_page_language_spinner.setSelection(7);break;
            case "Portuguese":setting_page_language_spinner.setSelection(8);break;
            case "Russian": setting_page_language_spinner.setSelection(9);break;
            case "Spanish": setting_page_language_spinner.setSelection(10);break;
            default: setting_page_language_spinner.setSelection(0);break;
        }

        setting_page_fontsize_spinner.setSelection(0);

        setting_page_language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: language = "English";Sharing.language = "English";break;
                    case 1: language = "Simplified Chinese";Sharing.language = "Simplified Chinese";break;
                    case 2: language = "Traditional Chinese";Sharing.language = "Traditional Chinese";break;
                    case 3: language = "Dutch"; Sharing.language = "Dutch";break;
                    case 4: language = "French"; Sharing.language = "French";break;
                    case 5: language = "German"; Sharing.language = "German";break;
                    case 6: language = "Italian"; Sharing.language = "Italian";break;
                    case 7: language = "Japanese"; Sharing.language = "Japanese";break;
                    case 8: language = "Portuguese"; Sharing.language = "Portuguese";break;
                    case 9: language = "Russian"; Sharing.language = "Russian";break;
                    case 10: language = "Spanish";Sharing.language = "Spanish";break;
                    default: language = "English"; Sharing.language = "English";break;
                }
                Log.d("language", language);
                if (!Sharing.language.equalsIgnoreCase(initial_language))
                {
                    Intent intent = new Intent (SettingPageActivity.this, TransferActivity.class);
                    startActivity(intent);
                    SettingPageActivity.this.finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (!Sharing.isScale)
        {
            setting_page_fontsize_spinner.setSelection(0);
        }
        else
        {
            setting_page_fontsize_spinner.setSelection(1);
        }

        setting_page_fontsize_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: fontsize = "Normal";Sharing.isScale = false;break;
                    case 1: fontsize = "Large";Sharing.isScale = true;break;
                    default: fontsize = "Normal";Sharing.isScale = false;break;
                }
                Log.d("fontsize", fontsize);
                if (Sharing.isScale != initial_isScale)
                {
                    Intent intent = new Intent (SettingPageActivity.this, TransferActivity.class);
                    startActivity(intent);
                    SettingPageActivity.this.finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        setting_page_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPageActivity.this, AccountCentreActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("fontsize", fontsize);
                startActivity(intent);

                // Setting page should finish at this point
                SettingPageActivity.this.finish();
            }
        });

        setting_page_data_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPageActivity.this, DataListActivity.class);
                intent.putExtra("source", "setting");
                Sharing.redirect_source = "setting";

                startActivity(intent);
                // Setting page should finish at this point
                SettingPageActivity.this.finish();


            }
        });

        setting_page_introduction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPageActivity.this, IntroductionPageActivity.class);

                startActivity(intent);
                SettingPageActivity.this.finish();
            }
        });

        setting_page_tutorial_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent (SettingPageActivity.this, TutorialsActivity.class);
                //startActivity (intent);
                title = new ArrayList<>();
                description = new ArrayList<>();

                title.add("Setting page");
                description.add("This is the setting page, you can change the app settings like language or font size, you can also find the app introduction information by clicking \\\"Introduction\\\" button. ");

                title.add("Choose your language");
                description.add("Here you can choose your preferred language, our app support English, Simplified Chinese, Traditional Chinese, French, Dutch, German, Italian, Japanese, Russian, Spanish and Portuguese. The change will take effect immediately. ");

                title.add("Customize font size");
                description.add("Here you can customise the font size, normal or large. The change will take effect immediately. ");

                title.add("Collect some information");
                description.add("We need to collect some information to mage the diagnostic more precise. The information include your name, gender, education, previous rating score etc. ");

                title.add("Have some practice");
                description.add("This is a playground where you can practice drawing two parallel lines. Our specially designed algorithm will judge whether these two lines are indeed parallel. Have fun! ");

                title.add("Practice results");
                description.add("Here you can see whether the two parallel lines you draw are indeed paralle. These grading level will be used: Perfect, Good, Acceptable and Bad. ");

                title.add("Static Parallel Test");
                description.add("This is the static parallel test, you need to draw three horizontal parallel lines. ");

                title.add("Select static tests");
                description.add("You can select a static test here. ");

                title.add("Select dynamic tests");
                description.add("You can choose a dynamic test here. ");

                title.add("Select test image");
                description.add("Here you can choose the background image of the test. We have spiral and pentagon. ");

                title.add("Test instructions");
                description.add("This page contains some important test instructions. Remember to read them carefully.");

                title.add("Choose interval duration");
                description.add("You can choose the interval duration of the test. The interval duration only applies to dynamic tests. ");

                title.add("Choose painter width");
                description.add("You can choose the painter width you preferred. We have thin, normal and thick width available. ");

                title.add("Draw it");
                description.add("Draw the required shape here. You can click \\\"Clear\\\" to clear the canvas, and click \\\"Finish\\\" to finish the test. ");

                title.add("Data list");
                description.add("Here you can all the test data, including test ID, test type, image type, detailed coordinates and more! ");

                title.add("Long click to delete");
                description.add("Delete a piece of data by long clicking it. ");

                title.add("Send data email");
                description.add("Want to outputting all test data? Send it via email. ");

                title.add("Store data file");
                description.add("You can also store data file into Android file system, and send it to your PC by connecting your phone via a cabel. You can copy the file path to clipboard. Easy to use! ");

                title.add("Delete data");
                description.add("Here you can delete all test data, delete test data prior to a specific data, or delete all data (This will restart the app) ");

                //showAppTutorialSettingPage();

                Intent intent = new Intent (SettingPageActivity.this, TutorialActivity.class);
                startActivity(intent);
                SettingPageActivity.this.finish();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting_page_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_colour) {
            LayoutInflater inflater = LayoutInflater.from(SettingPageActivity.this);
            View view = inflater.inflate(R.layout.change_colour_alertdialog , null);
            initial_colour = Sharing.colour;
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
            builder.setTitle(R.string.change_colour);
            builder.setCancelable(false);
            builder.setView(view);
            blue_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_blue);
            blue_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "blue";
                    Toast.makeText(SettingPageActivity.this, R.string.blue, Toast.LENGTH_SHORT).show();
                }
            });
            light_blue_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_lightblue);
            light_blue_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "light_blue";
                    Toast.makeText(SettingPageActivity.this, R.string.light_blue, Toast.LENGTH_SHORT).show();
                }
            });
            green_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_green);
            green_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "green";
                    Toast.makeText(SettingPageActivity.this, R.string.green, Toast.LENGTH_SHORT).show();
                }
            });
            purple_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_purple);
            purple_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "purple";
                    Toast.makeText(SettingPageActivity.this, R.string.purple, Toast.LENGTH_SHORT).show();
                }
            });
            pink_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_pink);
            pink_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "pink";
                    Toast.makeText(SettingPageActivity.this, R.string.pink, Toast.LENGTH_SHORT).show();
                }
            });
            orange_button = (ImageView) view.findViewById(R.id.change_colour_alertdialog_orange);
            orange_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sharing.colour = "orange";
                    Toast.makeText(SettingPageActivity.this, R.string.orange, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!Sharing.colour.equalsIgnoreCase(initial_colour))
                    {
                        Intent intent = new Intent(SettingPageActivity.this, TransferActivity.class);
                        startActivity(intent);
                        SettingPageActivity.this.finish();
                    }
                }
            });
            builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here;
                    // leave this as null
                }
            });
            builder.create();
            builder.show();



        }
        else if (id == R.id.action_information)
        {
            Intent intent = new Intent (SettingPageActivity.this, InfoActivity.class);
            startActivity(intent);
            SettingPageActivity.this.finish();
        }
        else if (id == R.id.action_cache)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingPageActivity.this);
            builder.setTitle(R.string.clean_cache);
            builder.setCancelable(false);
            builder.setMessage(R.string.clean_app_cache);
            builder.setPositiveButton(R.string.clean, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cleanCache(context);
                    Toast.makeText(SettingPageActivity.this, R.string.cache_cleaned, Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // LEAVE this as empty block
                }
            });
            builder.create();
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(this, getResources().getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                System.exit(0);
                SettingPageActivity.this.finish();
            }
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
    public void onPause ()
    {
        super.onPause();
    }

    @Override
    public void onStop ()
    {
        super.onStop();
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }





}
