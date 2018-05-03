package com.example.jiashuwu.neurograph;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.SocketHandler;

public class DataListActivity extends AppCompatActivity {

    private ListView data_listview;

    private SimpleAdapter simpleAdapter;

    private MyDatabaseHelper databaseHelper;

    private String databaseName = DatabaseInformation.databaseName;

    private int databaseVersion = DatabaseInformation.databaseVersion;

    private ArrayList<Map<String, Object>> datalist;

    private int test_id;
    private int data_id;
    private int user_id;
    private String name;
    private String timestamp_of_point;
    private String test_starting_time;
    private String test_ending_time;
    private String test_type;
    private String image_type;
    private int interval_duration;
    private int number_of_points;
    private float x;
    private float y;
    private float pressure;

    private Spinner delete_option_spinner;
    private DatePicker delete_date_datepicker;

    private ArrayAdapter delete_option_adapter;

    private SQLiteDatabase database;

    private String delete_option = "";

    private int delete_year;
    private int delete_month;
    private int delete_day;
    private String delete_month_s;
    private String delete_day_s;

    private long exitTime;

    private String source = "";

    private String item_clicked = "";

    private String test_detail = "";

    private MyDatabaseHelper databaseHelper2;
    private SQLiteDatabase database2;

    private float touch_point_size;

    private int selected_test_id;

    private RadioButton radioButton100;
    private RadioButton radioButton200;
    private RadioButton radioButton500;
    private RadioButton radioButton800;
    private RadioButton radioButton1000;

    private RadioGroup radioGroup;

    public Intent intent_det;

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


    public void loadItemWorker ()
    {
        Sharing.test_detail_arraylist.clear();
        test_detail =
                "test_id = " + String.valueOf(test_id) + "\n"
                        + "name = " + name + "\n"
                        + "user_id = " + String.valueOf(user_id) + "\n"
                        + "test_starting_time = " + test_starting_time + "\n"
                        + "test_ending_time = " + test_ending_time + "\n"
                        + "test type = " + test_type + "\n"
                        + "image type = " + image_type + "\n"
                        + "interval duration = " + String.valueOf(interval_duration) + "\n"
                        + "number of points = " + String.valueOf(number_of_points) + "\n";

        Sharing.test_detail_title = "";
        Sharing.test_detail_title = Sharing.test_detail_title +
                "Test ID = " + String.valueOf(test_id) + "\n" +
                "Name = " + name + "\n" +
                "User ID = " + String.valueOf(user_id) + "\n" +
                "Test Starting Time = " + test_starting_time + "\n" +
                "Test Ending Time = " + test_ending_time + "\n" +
                "Test Type = " + test_type + "\n" +
                "Image Type = " + image_type + "\n" +
                "Interval Duration = " + String.valueOf(interval_duration) + "\n" +
                "Number of Points = " + String.valueOf(number_of_points) + "\n";

        databaseHelper2 = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper2.getReadableDatabase();

        String query = "SELECT * FROM Data WHERE test_id = ?";
        String [] parameters = new String[] {String.valueOf(selected_test_id)};

        database2 = databaseHelper2.getReadableDatabase();

        Cursor cursor = database2.rawQuery(query, parameters);
        String new_line = "";
        while (cursor.moveToNext())
        {
            timestamp_of_point = cursor.getString(2).toString();
            x = cursor.getFloat(3);
            y = cursor.getFloat(4);
            pressure = cursor.getFloat(5);
            touch_point_size = cursor.getFloat(6);
            Log.d("TOUCH_SIZE", String.valueOf(touch_point_size));
            new_line = timestamp_of_point + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(pressure) + " " + String.valueOf(touch_point_size) + "\n";
            test_detail = test_detail + new_line;
            Sharing.test_detail_arraylist.add(new_line);
        }

        Sharing.test_detail = test_detail;

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

        Sharing.stop_showing_process = 0;
        Intent broadcastMessage = new Intent ();
        broadcastMessage.setAction("com.example.jiashuwu.neurograph.action.MyReceiver");
        broadcastMessage.putExtra("stop_showing_process", "1");
        sendBroadcast(broadcastMessage);
    }

    public void build_data_list ()
    {
        datalist.clear();

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        database = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM Test";
        String query1 = "";

        String [] parameters = new String [] {};
        String [] parameters1;

        Cursor cursor = database.rawQuery(query, parameters);
        Cursor cursor1;
        while (cursor.moveToNext())
        {
            test_id = cursor.getInt(0);
            user_id = cursor.getInt(1);
            test_starting_time = cursor.getString(2).toString();
            test_ending_time = cursor.getString(3).toString();
            test_type = cursor.getString(4).toString();
            image_type = cursor.getString(5).toString();
            interval_duration = Integer.parseInt(cursor.getString(6).toString());
            number_of_points = Integer.parseInt(cursor.getString(7).toString());

            query1 = "SELECT name FROM User WHERE user_id = ?";
            parameters1 = new String [] {String.valueOf(user_id)};
            cursor1 = database.rawQuery(query1, parameters1);
            while (cursor1.moveToNext())
            {
                name = cursor1.getString(0).toString();
            }

            Map<String, Object> map = new HashMap <> ();

            map.put("test_id", test_id);
            map.put("name", name);
            map.put("user_id", user_id);
            map.put("test_starting_time", test_starting_time);
            map.put("test_ending_time", test_ending_time);
            map.put("test_type", test_type);
            map.put("image_type", image_type);
            map.put("interval_duration", interval_duration);
            map.put("number_of_points", number_of_points);

            datalist.add(map);

            if (cursor1 != null)
            {
                cursor1.close();
            }
        }

        if (cursor != null)
        {
            cursor.close();
        }
        if (database != null)
        {
            database.close();
        }
        if (databaseHelper != null)
        {
            databaseHelper.close();
        }

        simpleAdapter = new SimpleAdapter(DataListActivity.this, datalist, R.layout.listview_item, new String [] {"test_id" , "name" , "test_starting_time" , "test_type" , "image_type"}, new int [] {R.id.list_item_test_number , R.id.list_item_tester_name , R.id.list_item_test_time , R.id.list_item_test_type , R.id.list_item_image_type});
        data_listview.setAdapter(simpleAdapter);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(DataListActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(DataListActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data_listview = (ListView) findViewById(R.id.data_listview);

        databaseHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        databaseHelper.getReadableDatabase();

        datalist = new ArrayList<>();

        build_data_list();

        data_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, Object> data_detail = (HashMap<String, Object>) data_listview.getItemAtPosition(position);
                intent_det = new Intent(DataListActivity.this, DisplayLoadingActivity.class);
                // intent.putExtra("test_id", data_detail.get("test_id").toString());
                test_id = Integer.parseInt(data_detail.get("test_id").toString());
                selected_test_id = Integer.parseInt(data_detail.get("test_id").toString());
                // intent.putExtra("name", data_detail.get("name").toString());
                name = data_detail.get("name").toString();
                // intent.putExtra("user_id", data_detail.get("user_id").toString());
                user_id = Integer.parseInt(data_detail.get("user_id").toString());
                // intent.putExtra("test_starting_time", data_detail.get("test_starting_time").toString());
                test_starting_time = data_detail.get("test_starting_time").toString();
                // intent.putExtra("test_ending_time", data_detail.get("test_ending_time").toString());
                test_ending_time = data_detail.get("test_ending_time").toString();
                // intent.putExtra("test_type", data_detail.get("test_type").toString());
                test_type = data_detail.get("test_type").toString();
                // intent.putExtra("image_type", data_detail.get("image_type").toString());
                image_type = data_detail.get("image_type").toString();
                // intent.putExtra("interval_duration", data_detail.get("interval_duration").toString());
                interval_duration = Integer.parseInt(data_detail.get("interval_duration").toString());
                // intent.putExtra("number_of_points", data_detail.get("number_of_points").toString());
                number_of_points = Integer.parseInt(data_detail.get("number_of_points").toString());

                Sharing.number_of_item_in_total = number_of_points;

                item_clicked = "clicked";
                LayoutInflater inflater = LayoutInflater.from(DataListActivity.this);
                final View view1 = inflater.inflate(R.layout.choose_data_detail_number_alertdialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
                builder.setTitle(R.string.number_of_entries_per_page);
                builder.setCancelable(false);
                builder.setView(view1);

                radioGroup = (RadioGroup) view1.findViewById(R.id.choose_entries_radio_group);

                builder.setPositiveButton(getString(R.string.proceed), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (view1.findViewById(selectedId) != null)
                        {
                            if (selectedId == R.id.radiobutton_choose_entries_100)
                            {
                                Sharing.entries_per_page = 100;
                            }
                            else if (selectedId == R.id.radiobutton_choose_entries_200)
                            {
                                Sharing.entries_per_page = 200;
                            }
                            else if (selectedId == R.id.radiobutton_choose_entries_500)
                            {
                                Sharing.entries_per_page = 500;
                            }
                            else if (selectedId == R.id.radiobutton_choose_entries_800)
                            {
                                Sharing.entries_per_page = 800;
                            }
                            else if (selectedId == R.id.radiobutton_choose_entries_1000)
                            {
                                Sharing.entries_per_page = 1000;
                            }
                        }
                        startActivity(intent_det);
                        DataListActivity.this.finish();
                    }
                });
                builder.create();
                builder.show();

            }
        });

        data_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, Object> data_detail = (HashMap<String, Object>) data_listview.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
                builder.setTitle(R.string.make_changes);
                builder.setCancelable(false);
                builder.setMessage(R.string.want_to_delete);
                builder.setNegativeButton(R.string.button_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int item_to_be_deleted = (int)data_detail.get("test_id");
                        databaseHelper = new MyDatabaseHelper (DataListActivity.this, databaseName, null, databaseVersion);
                        database = databaseHelper.getWritableDatabase();
                        database.delete("Data", "test_id = ? ", new String[] {String.valueOf(item_to_be_deleted)});
                        database.delete("Test", "test_id = ? ", new String[] {String.valueOf(item_to_be_deleted)});

                        if (database != null)
                        {
                            database.close();
                        }
                        if (databaseHelper != null)
                        {
                            databaseHelper.close();
                        }

                        build_data_list();

                    }
                });
                builder.setPositiveButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here;
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_list_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share_data)
        {
            Intent intent = new Intent(DataListActivity.this, SendDataEmailActivity.class);
            intent.putExtra("user_id", String.valueOf(user_id));
            // TODO FIX
            startActivity(intent);
            DataListActivity.this.finish();
            // The DataListActivity finishes when we go to SendDataEmailActivity;
            // FIXED
        }
        else if (id == R.id.action_store_file)
        {
            Intent intent = new Intent (DataListActivity.this, StoreDataFileActivity.class);
            intent.putExtra("user_id", String.valueOf(user_id));
            startActivity(intent);
            DataListActivity.this.finish();

        }
        else if (id == R.id.action_delete)
        {
            LayoutInflater inflater = LayoutInflater.from(DataListActivity.this);
            View view = inflater.inflate(R.layout.activity_data_list_delete_option_alertdialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
            builder.setTitle(R.string.delete_data);
            builder.setCancelable(false);
            builder.setView(view);

            delete_option_spinner = (Spinner) view.findViewById(R.id.alertdialog_delete_option_spinner);

            final String [] delete_option_list = getResources().getStringArray(R.array.delete_options);
            delete_option_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, delete_option_list);
            delete_option_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            delete_option_spinner.setAdapter(delete_option_adapter);

            delete_option_spinner.setSelection(0);

            delete_option_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0: delete_option = "test";break;
                        case 1: delete_option = "specific";break;
                        case 2: delete_option = "all";break;
                        default: delete_option = "test";break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            builder.setPositiveButton(getResources().getString(R.string.delete), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (delete_option.equalsIgnoreCase("all"))
                    {
                        databaseHelper = new MyDatabaseHelper (DataListActivity.this, databaseName, null, databaseVersion);
                        database = databaseHelper.getWritableDatabase();
                        MyDatabaseHelper.upgradeDatabase(database);
                        if (database != null)
                        {
                            database.close();
                        }
                        if (databaseHelper != null)
                        {
                            databaseHelper.close();
                        }
                        //build_data_list();

                        // Make a Toast
                        Toast.makeText(DataListActivity.this, getResources().getString(R.string.deletion_completed), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DataListActivity.this, SettingPageActivity.class);
                        startActivity(intent);
                        DataListActivity.this.finish();
                    }
                    if (delete_option.equalsIgnoreCase("test"))
                    {
                        databaseHelper = new MyDatabaseHelper (DataListActivity.this, databaseName, null, databaseVersion);
                        database = databaseHelper.getWritableDatabase();
                        database.delete("Data", "", new String[] {});
                        database.delete("Test", "", new String[] {});
                        if (database != null)
                        {
                            database.close();
                        }
                        if (databaseHelper != null)
                        {
                            databaseHelper.close();
                        }
                        Toast.makeText(DataListActivity.this, getResources().getString(R.string.deletion_completed), Toast.LENGTH_LONG).show();
                        build_data_list();
                    }
                    if (delete_option.equalsIgnoreCase("specific"))
                    {
                        LayoutInflater inflater = LayoutInflater.from(DataListActivity.this);
                        View view = inflater.inflate(R.layout.activity_data_list_delete_date_alertdialog, null);

                        AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
                        builder.setTitle(R.string.detele_data_before_this_date);
                        builder.setCancelable(false);
                        builder.setView(view);
                        delete_date_datepicker = (DatePicker) view.findViewById(R.id.delete_time_datepicker);

                        Calendar calendar = Calendar.getInstance();

                        delete_year = calendar.get(Calendar.YEAR);
                        delete_month = calendar.get(Calendar.MONTH) + 1;
                        delete_day = calendar.get(Calendar.DAY_OF_MONTH);

                        builder.setNegativeButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Should do nothing here;
                            }
                        });

                        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete_year = delete_date_datepicker.getYear();
                                delete_month = delete_date_datepicker.getMonth() + 1;
                                delete_day = delete_date_datepicker.getDayOfMonth();

                                if (String.valueOf(delete_month).length() == 1)
                                {
                                    delete_month_s = "0" + String.valueOf(delete_month);
                                }
                                else
                                {
                                    delete_month_s = String.valueOf(delete_month);
                                }
                                if (String.valueOf(delete_day).length() == 1)
                                {
                                    delete_day_s = "0" + String.valueOf(delete_day);
                                }
                                else
                                {
                                    delete_day_s = String.valueOf(delete_day);
                                }


                                String delete_before_this_date = String.valueOf(delete_year) + "-" + delete_month_s + "-" + delete_day_s + " " + "00:00:00.000";

                                databaseHelper = new MyDatabaseHelper (DataListActivity.this, databaseName, null, databaseVersion);
                                database = databaseHelper.getWritableDatabase();
                                //database.delete("Data", "", new String[] {});
                                database.delete("Test", "test_ending_time < ?", new String[] {delete_before_this_date});

                                if (database != null)
                                {
                                    database.close();
                                }
                                if (databaseHelper != null)
                                {
                                    databaseHelper.close();
                                }

                                Toast.makeText(DataListActivity.this, R.string.deletion_completed, Toast.LENGTH_LONG).show();


                                build_data_list();
                            }
                        });
                        builder.create();
                        builder.show();



                    }
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here;
                }
            });
            builder.create();
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO OPTIONAL 1/4



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (Sharing.redirect_source.equalsIgnoreCase("setting") || Sharing.redirect_source.equalsIgnoreCase("switch_setting"))
            {
                Intent intent = new Intent(DataListActivity.this, SettingPageActivity.class);
                startActivity(intent);
                DataListActivity.this.finish();
            }
            else if (Sharing.redirect_source.equalsIgnoreCase("test_selection"))
            {
                Intent intent = new Intent(DataListActivity.this, TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(Sharing.current_user_id));
                startActivity(intent);
                DataListActivity.this.finish();
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
        if (item_clicked.equalsIgnoreCase("clicked"))
        {
            item_clicked = "";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadItemWorker();
                }
            }).start();
        }
        super.onStop();
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }

}
