package com.example.jiashuwu.neurograph;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataListActivity extends AppCompatActivity {

    private ListView data_listview;

    private SimpleAdapter simpleAdapter;

    private MyDatabaseHelper databaseHelper;

    private String databaseName = "information.db";

    private int databaseVersion = 1;

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
    private float x;
    private float y;
    private float pressure;

    private SQLiteDatabase database;

    public void build_data_list ()
    {
        datalist.clear();

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
            datalist.add(map);
            if (cursor1 != null)
            {
                cursor1.close();
            }
        }

        simpleAdapter = new SimpleAdapter(DataListActivity.this, datalist, R.layout.listview_item, new String [] {"test_id" , "name" , "test_starting_time" , "test_type" , "image_type"}, new int [] {R.id.list_item_test_number , R.id.list_item_tester_name , R.id.list_item_test_time , R.id.list_item_test_type , R.id.list_item_image_type});
        data_listview.setAdapter(simpleAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(DataListActivity.this, Sharing.isScale);
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
                Intent intent = new Intent(DataListActivity.this, TestDetailScrollingActivity.class);
                intent.putExtra("test_id", data_detail.get("test_id").toString());
                intent.putExtra("name", data_detail.get("name").toString());
                intent.putExtra("user_id", data_detail.get("user_id").toString());
                intent.putExtra("test_starting_time", data_detail.get("test_starting_time").toString());
                intent.putExtra("test_ending_time", data_detail.get("test_ending_time").toString());
                intent.putExtra("test_type", data_detail.get("test_type").toString());
                intent.putExtra("image_type", data_detail.get("image_type").toString());
                intent.putExtra("interval_duration", data_detail.get("interval_duration").toString());
                startActivity(intent);
            }
        });

        data_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, Object> data_detail = (HashMap<String, Object>) data_listview.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
                builder.setTitle("Make Changes");
                builder.setCancelable(false);
                builder.setMessage("Want to delete?");
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int item_to_be_deleted = (int)data_detail.get("test_id");
                        database = databaseHelper.getWritableDatabase();
                        database.delete("Data", "test_id = ? ", new String[] {String.valueOf(item_to_be_deleted)});
                        database.delete("Test", "test_id = ? ", new String[] {String.valueOf(item_to_be_deleted)});

                        build_data_list();

                    }
                });
                builder.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here;
                    }
                });
                builder.create();
                builder.show();
                return false;
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
        }
        else if (id == R.id.action_delete)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataListActivity.this);
            builder.setTitle("Delete all data");
            builder.setCancelable(false);
            builder.setMessage("Delete all data ?");
            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    database = databaseHelper.getWritableDatabase();
                    database.delete("Data", "", new String[] {});
                    database.delete("Test", "", new String[] {});
                    build_data_list();

                }
            });
            builder.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
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
}
