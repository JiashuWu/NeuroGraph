package com.example.jiashuwu.neurograph;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // UNDER CONSIDERATION!!!


    private ImageView tutorial_image;
    private TextView tutorial_text;



    public void showAppTutorialChangeSetting ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tutorial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting_page)
        {
            showAppTutorialSettingPage();
        }
        else if (id == R.id.nav_choose_language)
        {
            showAppTutorialSettingPageLanguage();
        }
        else if (id == R.id.nav_choose_font_size)
        {
            showAppTutorialSettingPageFont();
        }
        else if (id == R.id.nav_choose_theme)
        {
            showAppTutorialSettingPageTheme();
        }
        else if (id == R.id.nav_change_setting)
        {
            showAppTutorialChangeSetting();
        }
        else if (id == R.id.nav_information)
        {
            showAppTutorialInfoActivity();
        }
        else if (id == R.id.nav_about_your_device)
        {
            showAppTutorialDevicePage();
        }
        else if (id == R.id.nav_view_copyright)
        {
            showAppTutorialCopyright();
        }
        else if (id == R.id.nav_clean_cache)
        {
            showAppTutorialSettingPageCache();
        }
        else if (id == R.id.nav_introduction_page)
        {
            showAppTutorialIntroduction();
        }
        else if (id == R.id.nav_account_centre)
        {
            showAppTutorialAccountCentre();
        }
        else if (id == R.id.nav_information_collection)
        {
            showAppTutorialInformationCollectionPage();
        }
        else if (id == R.id.nav_parallel_line_practice)
        {
            showAppTutorialParallelPractice();
        }
        else if (id == R.id.nav_practice_result)
        {
            showAppTutorialParallelPracticeResult();
        }
        else if (id == R.id.nav_static_parallel_test)
        {
            showAppTutorialParallelTest();
        }
        else if (id == R.id.nav_circular_motion)
        {
            showAppTutorialCircularMotion();
        }
        else if (id == R.id.nav_select_static_tests)
        {
            showAppTutorialStaticTestSelection();
        }
        else if (id == R.id.nav_select_dynamic_tests)
        {
            showAppTutorialDynamicTestSelection();
        }
        else if (id == R.id.nav_select_test_image)
        {
            showAppTutorialImageSelection();
        }
        else if (id == R.id.nav_test_instruction)
        {
            showAppTutorialTestInstruction();
        }
        else if (id == R.id.nav_choose_interval_duration)
        {
            showAppTutorialDuration();
        }
        else if (id == R.id.nav_choose_painter_width)
        {
            showAppTutorialWidth();
        }
        else if (id == R.id.nav_draw_it)
        {
            showAppTutorialTestCanvas();
        }
        else if (id == R.id.nav_processing)
        {
            showAppTutorialProcessing();
        }
        else if (id == R.id.nav_data_list)
        {
            showAppTutorialDataList();
        }
        else if (id == R.id.nav_long_click_delete)
        {
            showAppTutorialLongClickDelete();
        }
        else if (id == R.id.nav_send_data_email)
        {
            showAppTutorialSendEmail();
        }
        else if (id == R.id.nav_store_data_file)
        {
            showAppTutorialStoreDataFile();
        }
        else if (id == R.id.nav_view_data)
        {
            // TODO
        }
        else if (id == R.id.nav_choose_file_types)
        {
            showAppTutorialChooseFileType();
        }
        else if (id == R.id.nav_delete_data)
        {
            showAppTutorialDeleteData();
        }
        else if (id == R.id.nav_view_information)
        {
            showAppTutorialPersonal();
        }
        else if (id == R.id.nav_edit_information)
        {
            // TODO
        }
        else if (id == R.id.nav_switch_user)
        {
            showAppTutorialSwitchUser();
        }

        /*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(TutorialActivity.this, SettingPageActivity.class);
            startActivity(intent);
            TutorialActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
