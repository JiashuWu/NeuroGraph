package com.example.jiashuwu.neurograph;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
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

import java.util.Locale;

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
        builder.setTitle(R.string.change_setting);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_change_setting_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.change_setting_text);
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

    public void showAppTutorialStoreWhileSwitch ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
        builder.setTitle(R.string.store_data_after_switching);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_sending_before_switch_user_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.store_data_switching_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialChangeSetting();
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
        builder.setTitle(R.string.switch_user);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_switch_user_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.switch_user_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialStoreWhileSwitch();
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

    public void showAppTutorialEditPersonal ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
        builder.setTitle(R.string.edit_information1);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_edit_personal_information_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.edit_information_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSwitchUser();
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
        builder.setTitle(R.string.view_information);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_view_personal_information_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.view_information_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialEditPersonal();
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
        builder.setTitle(getResources().getString(R.string.delete_data));
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_delete_all_data_new);
        tutorial_text.setText(R.string.delete_data_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialPersonal();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCopyFilePath();
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

    public void showAppTutorialCopyFilePath ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
        builder.setTitle(R.string.copy_file_path);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_copy_file_path_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.choose_file_type_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialDeleteData();
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
        builder.setTitle(R.string.choose_file_type);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_choose_file_type_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.copy_file_path_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialCopyFilePath();
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
        builder.setTitle(R.string.store_data_file);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_generate_data_filw_new);
        tutorial_text.setText(R.string.store_data_file_tutorial_text);
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
        builder.setTitle(R.string.send_data_email);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_send_data_email_new);
        tutorial_text.setText(R.string.send_data_email_tutorial_text);
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
        builder.setTitle(R.string.long_click_to_delete1);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_delete_single_data_new);
        tutorial_text.setText(R.string.long_click_to_delete_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialSendEmail();
            }
        });
        builder.setNegativeButton(R.string.button_previous, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialViewDataDetail();
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

    public void showAppTutorialViewDataDetail ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
        builder.setTitle(R.string.test_details);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_view_data_detail_new);
        tutorial_text.setText(R.string.test_details_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialLongClickDelete();
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
        builder.setTitle(R.string.data_list);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_data_list_new);
        tutorial_text.setText(R.string.data_list_tutorial_text);
        builder.setPositiveButton(R.string.button_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAppTutorialViewDataDetail();
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
        builder.setTitle(R.string.procesing);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_provessing_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.processing_tutorial_text);
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
        builder.setTitle(getResources().getString(R.string.draw_it));
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_spiral_test_new);
        tutorial_text.setText(R.string.draw_it_tutorial_text);
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
        builder.setTitle(R.string.choose_painter_width);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_choose_painter_width_new);
        tutorial_text.setText(R.string.choose_painter_width_tutorial_text);
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
        builder.setTitle(R.string.choose_interval_duration);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_duration_selection_with_seekbar_new);
        tutorial_text.setText(R.string.choose_interval_duration_tutorial_text);
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
        builder.setTitle(R.string.test_instructions);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_choose_width_new);
        tutorial_text.setText(R.string.test_instruction_tutorial_text);
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
        builder.setTitle(R.string.select_test_image);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_image_selection_new);
        tutorial_text.setText(R.string.select_test_image_tutorial_text);
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
        builder.setTitle(R.string.select_dynamic_tests);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_dynamic_test_selection_new);
        tutorial_text.setText(R.string.select_dynamic_tests_tutorial_text);
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
        builder.setTitle(R.string.select_static_tests);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_image.setImageResource(R.drawable.screen_static_test_selection_new);
        tutorial_text.setText(R.string.select_static_tests_tutorial_text);
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

    public void showAppTutorialCircularMotionBackground ()
    {
        LayoutInflater inflater = LayoutInflater.from(TutorialActivity.this);
        View view = inflater.inflate(R.layout.activity_tutorial_activity_tutorial_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
        builder.setTitle(R.string.choose_background);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_circular_motion_choose_background);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.choose_background_tutorial_text);
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
        builder.setTitle(R.string.circular_motion_test);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_circular_motion_test_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.circular_motion_test_tutorial_text);
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
        builder.setTitle(R.string.parallel_line_test);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_parallel_line_test_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.parallel_line_test_tutorial_text);
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
        builder.setTitle(R.string.practice_results);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_practice_result_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.practice_result_tutorial_text);
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
        builder.setTitle(R.string.practice_makes_perfect);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_practice);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.practice_makes_perfect_tutorial_text);
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
        builder.setTitle(R.string.information_collection);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_information_collection_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.information_collection_tutorial_text);
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
        builder.setTitle(R.string.account_centre);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_account_centre);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.account_centre_tutorial_text);
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
        builder.setTitle(R.string.introduction_page);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_introduction_page);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.introduction_page_tutorial_text);
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
        builder.setTitle(R.string.clean_cache);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_clean_cache_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.clean_cache_tutorial_text);
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
        builder.setTitle(R.string.view_copyright);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_copyright_information_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.view_copyright_tutorial_text);
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
        builder.setTitle(R.string.about_your_device);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_device_information_new);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.about_your_device_tutorial_text);
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
        builder.setTitle(R.string.information);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screenshot_info_page);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.information_tutorial_text);
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
        builder.setTitle(R.string.choose_your_theme);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_choose_colour);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.choose_your_theme_tutorial_text);
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
        builder.setTitle(R.string.customise_font_size);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_choose_font_size);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.customise_font_size_tutorial_text);
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
        builder.setTitle(R.string.choose_your_language);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_language);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.choose_your_language_tutorial_text);
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
        builder.setTitle(R.string.setting_page);
        builder.setCancelable(false);
        builder.setView(view);
        tutorial_image = (ImageView) view.findViewById(R.id.tutorial_alertdialog_image);
        tutorial_image.setImageResource(R.drawable.screen_home_page);
        tutorial_text = (TextView) view.findViewById(R.id.tutorial_alertdialog_text);
        tutorial_text.setText(R.string.setting_page_tutorial_text);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(TutorialActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(TutorialActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();

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
            showAppTutorialViewDataDetail();
        }
        else if (id == R.id.nav_choose_file_types)
        {
            showAppTutorialChooseFileType();
        }
        else if (id == R.id.nav_copy_file_path)
        {
            showAppTutorialCopyFilePath();
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
            showAppTutorialEditPersonal();
        }
        else if (id == R.id.nav_switch_user)
        {
            showAppTutorialSwitchUser();
        }
        else if (id == R.id.nav_store_while_switch)
        {
            showAppTutorialStoreWhileSwitch();
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
