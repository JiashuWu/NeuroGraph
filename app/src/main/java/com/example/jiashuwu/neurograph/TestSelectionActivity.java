package com.example.jiashuwu.neurograph;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.Locale;

import javax.xml.datatype.Duration;

public class TestSelectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private ViewGroup.LayoutParams layoutParams;

    private View view_practice;
    private View view_static;
    private View view_dynamic;

    private ArrayList<View> viewList;

    private PagerAdapter pageAdapter;

    private Button practice_button;
    private Button parallel_test_button;
    private Button circular_motion_button;
    private Button test1_button;
    private Button test2_button;
    private Button test3_button;
    private Button test4_button;

    private ImageView imageview01;
    private ImageView imageview02;
    private ImageView imageview03;
    private ImageView imageview11;
    private ImageView imageview12;
    private ImageView imageview21;
    private ImageView imageview22;

    // private Spinner parallel_line_practice_width_spinner;

    private int user_id;

    private String test_type = "";

    private long exitTime;

    private int painter_width = 0;

    private ArrayAdapter adapter;

    private Spinner alertdialog_language_spinner;
    private Spinner alertdialog_font_spinner;

    private ArrayAdapter language_adapter;
    private ArrayAdapter font_adapter;

    private String initial_language;
    private boolean initial_isScale;

    private String databaseName = DatabaseInformation.databaseName;
    private int databaseVersion = DatabaseInformation.databaseVersion;

    private MyDatabaseHelper databaseHelper2;
    private SQLiteDatabase database2;

    private Spinner store_file_option_spinner;
    private int selected_store_file_option = 0;

    private ArrayAdapter store_file_option_adapter;


    /**
     * This method is used to change the locale and language settings.
     */
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

    /**
     * This method is used to change the theme.
     */
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

    /**
     * This method is used to get the total number of items
     * @return
     */
    public int getNumber_of_test_in_total ()
    {
        int answer = 0;

        databaseHelper2 = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        databaseHelper2.getReadableDatabase();

        database2 = databaseHelper2.getReadableDatabase();

        String query = "SELECT COUNT () FROM Test";
        String [] parameters = new String[]{};
        Cursor cursor = database2.rawQuery(query, parameters);
        while (cursor.moveToNext())
        {
            answer = cursor.getInt(0);
        }
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

        return answer;
    }

    /**
     * Set up for Navigation Bar at the bottom.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_practice_tests:
                    viewPager.setCurrentItem(0);
                    practice_button = (Button) findViewById(R.id.test_selection_page0_test0_button);
                    parallel_test_button = (Button) findViewById(R.id.test_selection_page0_parallel_line_test_button);
                    circular_motion_button = (Button) findViewById(R.id.test_selection_page_circular_motion_test_button);
                    /*
                    imageview01 = (ImageView) findViewById(R.id.test_selection_page0_imageview1);
                    imageview02 = (ImageView) findViewById(R.id.test_selection_page0_imageview2);
                    imageview03 = (ImageView) findViewById(R.id.test_selection_page0_imageview3);
                    layoutParams = imageview01.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    layoutParams = imageview02.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    layoutParams = imageview03.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    */

                    // parallel_line_practice_width_spinner = (Spinner) findViewById(R.id.test_selection_test0_width_spinner);
                    String [] mList = getResources().getStringArray(R.array.painter_width);
                    adapter = new ArrayAdapter(TestSelectionActivity.this , android.R.layout.simple_spinner_dropdown_item, mList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // parallel_line_practice_width_spinner.setAdapter(adapter);
                    /*
                    parallel_line_practice_width_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position)
                            {
                                case 0: painter_width = 5;break;
                                case 1: painter_width = 10;break;
                                case 2: painter_width = 15;break;
                                default: painter_width = 5;break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    */

                    practice_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TestSelectionActivity.this, DurationSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "parallel_line_practice";
                            Sharing.painter_width = painter_width;
                            intent.putExtra("test_type", test_type);
                            Sharing.test_to_duration = "yes";
                            intent.putExtra("image_type", "parallel_practice");
                            startActivity(intent);
                            TestSelectionActivity.this.finish();
                        }
                    });

                    parallel_test_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (TestSelectionActivity.this, DurationSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "parallel_line_test";
                            intent.putExtra("test_type", test_type);
                            intent.putExtra("image_type", "parallel_line");
                            Sharing.test_to_duration = "yes";
                            startActivity(intent);
                            TestSelectionActivity.this.finish();
                        }
                    });

                    circular_motion_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (TestSelectionActivity.this, ImageSelectionForCircularMotionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "circular_motion_test";
                            intent.putExtra("test_type", test_type);
                            Sharing.test_to_duration = "yes";
                            startActivity(intent);
                            TestSelectionActivity.this.finish();
                        }
                    });
                    break;
                case R.id.navigation_static_tests:
                    viewPager.setCurrentItem(1);
                    test1_button = (Button) findViewById(R.id.test_selection_page1_test1_button);
                    test2_button = (Button) findViewById(R.id.test_selection_page1_test2_button);
                    /*
                    imageview11 = (ImageView) findViewById(R.id.test_selection_page1_imageview1);
                    imageview12 = (ImageView) findViewById(R.id.test_selection_page1_imageview2);
                    layoutParams = imageview11.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    layoutParams = imageview12.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    */

                    test1_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "static_full_background";
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
                            TestSelectionActivity.this.finish();
                        }
                    });

                    test2_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "static_corner_background";
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
                            TestSelectionActivity.this.finish();

                        }
                    });

                    break;
                case R.id.navigation_dynamic_tests:
                    viewPager.setCurrentItem(2);
                    test3_button = (Button) findViewById(R.id.test_selection_page2_test3_button);
                    test4_button = (Button) findViewById(R.id.test_selection_page2_test4_button);
                    /*
                    imageview21 = (ImageView) findViewById(R.id.test_selection_page2_imageview1);
                    imageview21 = (ImageView) findViewById(R.id.test_selection_page2_imageview2);

                    layoutParams = imageview21.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    layoutParams = imageview22.getLayoutParams();
                    layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
                    layoutParams.height = (int)(layoutParams.width / 3 * 2);
                    */

                    test3_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "dynamic_blank_background";
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
                            TestSelectionActivity.this.finish();

                        }
                    });

                    test4_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "dynamic_seasonal_background";
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
                            TestSelectionActivity.this.finish();

                        }
                    });

                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(TestSelectionActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(TestSelectionActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Sharing.test_to_duration = "";

        String [] mList = getResources().getStringArray(R.array.painter_width);
        adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        user_id = 0;
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = (ViewPager) findViewById(R.id.test_selection_viewpager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                menuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null)
                {
                    menuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
                return;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0 || state == 1)
                {
                    menuItem = navigation.getMenu().getItem(viewPager.getCurrentItem());
                    menuItem.setChecked(true);
                }
            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        LayoutInflater inflater = getLayoutInflater();
        view_practice = inflater.inflate(R.layout.test_selection_page0_layout, null);
        view_static = inflater.inflate(R.layout.test_selection_page1_layout, null);
        view_dynamic = inflater.inflate(R.layout.test_selection_page2_layout, null);

        viewList = new ArrayList<View>();
        viewList.add(view_practice);
        viewList.add(view_static);
        viewList.add(view_dynamic);

        pageAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem (ViewGroup container, int position, Object object)
            {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem (ViewGroup container, int position)
            {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pageAdapter);

        practice_button = (Button) view_practice.findViewById(R.id.test_selection_page0_test0_button);
        parallel_test_button = (Button) view_practice.findViewById(R.id.test_selection_page0_parallel_line_test_button);
        circular_motion_button = (Button) view_practice.findViewById(R.id.test_selection_page_circular_motion_test_button);
        test1_button = (Button) view_static.findViewById(R.id.test_selection_page1_test1_button);
        test2_button = (Button) view_static.findViewById(R.id.test_selection_page1_test2_button);
        test3_button = (Button) view_dynamic.findViewById(R.id.test_selection_page2_test3_button);
        test4_button = (Button) view_dynamic.findViewById(R.id.test_selection_page2_test4_button);

        /*
        imageview01 = (ImageView) view_practice.findViewById(R.id.test_selection_page0_imageview1);
        imageview02 = (ImageView) view_practice.findViewById(R.id.test_selection_page0_imageview2);
        imageview03 = (ImageView) view_practice.findViewById(R.id.test_selection_page0_imageview3);
        imageview11 = (ImageView) view_static.findViewById(R.id.test_selection_page1_imageview1);
        imageview12 = (ImageView) view_static.findViewById(R.id.test_selection_page1_imageview2);
        imageview21 = (ImageView) view_dynamic.findViewById(R.id.test_selection_page2_imageview1);
        imageview22 = (ImageView) view_dynamic.findViewById(R.id.test_selection_page2_imageview2);


        layoutParams = imageview01.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview02.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview03.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview11.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview12.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview21.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);
        layoutParams = imageview22.getLayoutParams();
        layoutParams.width = (int)(Sharing.device_width_in_pixels / 1.2) - 60;
        layoutParams.height = (int)(layoutParams.width / 3 * 2);

        */


        /*
        parallel_line_practice_width_spinner = (Spinner) view_practice.findViewById(R.id.test_selection_test0_width_spinner);
        parallel_line_practice_width_spinner.setAdapter(adapter);
        parallel_line_practice_width_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: painter_width = 5;break;
                    case 1: painter_width = 10;break;
                    case 2: painter_width = 15;break;
                    default: painter_width = 5;break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */


        // FIXED added a duration/width selection page for parallel line practice
        // FIXED change the test instruction for parallel line test
        // FIXED add a textview on test selection page for parallel line test


        practice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestSelectionActivity.this, DurationSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "parallel_line_practice";
                Sharing.painter_width = painter_width;
                intent.putExtra("test_type", test_type);
                Sharing.test_to_duration = "yes";
                intent.putExtra("image_type", "parallel_practice");
                startActivity(intent);
                TestSelectionActivity.this.finish();
            }
        });

        parallel_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (TestSelectionActivity.this, DurationSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "parallel_line_test";
                intent.putExtra("test_type", test_type);
                intent.putExtra("image_type", "parallel_line");
                Sharing.test_to_duration = "yes";
                startActivity(intent);
                TestSelectionActivity.this.finish();
            }
        });

        circular_motion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (TestSelectionActivity.this, ImageSelectionForCircularMotionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "circular_motion_test";
                intent.putExtra("test_type", test_type);
                Sharing.test_to_duration = "yes";
                startActivity(intent);
                TestSelectionActivity.this.finish();
            }
        });

        test1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "static_full_background";
                intent.putExtra("test_type", test_type);
                startActivity(intent);
                TestSelectionActivity.this.finish();

            }
        });

        test2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "static_corner_background";
                intent.putExtra("test_type", test_type);
                startActivity(intent);
                TestSelectionActivity.this.finish();

            }
        });

        test3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "dynamic_blank_background";
                intent.putExtra("test_type", test_type);
                startActivity(intent);
                TestSelectionActivity.this.finish();

            }
        });

        test4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "dynamic_seasonal_background";
                intent.putExtra("test_type", test_type);

                startActivity(intent);
                TestSelectionActivity.this.finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_selection, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_data_list)
        {
            Intent intent = new Intent(TestSelectionActivity.this, DataListActivity.class);
            intent.putExtra("user_id", String.valueOf(user_id));
            intent.putExtra("source", "test_selection");
            Sharing.redirect_source = "test_selection";
            Sharing.current_user_id = user_id;

            startActivity(intent);
            TestSelectionActivity.this.finish();



        }
        else if (id == R.id.action_switch_user)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(TestSelectionActivity.this);
            builder.setTitle(R.string.switch_user);
            builder.setCancelable(false);
            builder.setMessage(R.string.want_to_switch_user);
            builder.setPositiveButton(R.string.switch_user_upper, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (getNumber_of_test_in_total() == 0)
                    {
                        Sharing.test_selection_has_already_asked_file_issue = false;
                        Intent intent = new Intent(TestSelectionActivity.this, SettingPageActivity.class);
                        startActivity(intent);
                        TestSelectionActivity.this.finish();
                    }
                    else
                    {
                        Sharing.test_selection_has_already_asked_file_issue = true;
                        LayoutInflater inflater = LayoutInflater.from(TestSelectionActivity.this);
                        View view = inflater.inflate(R.layout.store_file_option_alertdialog, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(TestSelectionActivity.this);
                        builder.setTitle(R.string.store_data_file_before_switching_user);
                        builder.setCancelable(false);
                        builder.setView(view);

                        store_file_option_spinner = (Spinner) view.findViewById(R.id.store_data_file_alertdialog_spinner);

                        final String [] store_file_option_list = getResources().getStringArray(R.array.store_file_option);
                        store_file_option_adapter = new ArrayAdapter(TestSelectionActivity.this , android.R.layout.simple_spinner_dropdown_item, store_file_option_list);
                        store_file_option_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        store_file_option_spinner.setAdapter(store_file_option_adapter);

                        store_file_option_spinner.setSelection(0);
                        selected_store_file_option = 0;
                        store_file_option_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position)
                                {
                                    case 0: selected_store_file_option = 0; break;
                                    case 1: selected_store_file_option = 1; break;
                                    case 2: selected_store_file_option = 2; break;
                                    default: selected_store_file_option = 0; break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (selected_store_file_option == 0)
                                {
                                    Sharing.redirect_source = "switch_setting";
                                    Intent intent = new Intent (TestSelectionActivity.this, SendDataEmailActivity.class);

                                    startActivity(intent);
                                    TestSelectionActivity.this.finish();

                                }
                                else if (selected_store_file_option == 1)
                                {
                                    Sharing.redirect_source = "switch_setting";
                                    Intent intent = new Intent(TestSelectionActivity.this, StoreDataFileActivity.class);


                                    startActivity(intent);
                                    TestSelectionActivity.this.finish();

                                }
                                else if (selected_store_file_option == 2)
                                {
                                    Intent intent = new Intent (TestSelectionActivity.this, SettingPageActivity.class);
                                    startActivity(intent);
                                    TestSelectionActivity.this.finish();
                                }
                            }
                        });
                        builder.create();
                        builder.show();



                    }
                }
            });
            builder.setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here;
                }
            });
            builder.create();
            builder.show();
        }
        else if (id == R.id.action_trigger_setting)
        {
            LayoutInflater inflater = LayoutInflater.from(TestSelectionActivity.this);
            View view = inflater.inflate(R.layout.activity_test_selection_page_setting_alertdialog , null);
            initial_language = Sharing.language;
            initial_isScale = Sharing.isScale;

            AlertDialog.Builder builder = new AlertDialog.Builder(TestSelectionActivity.this);
            builder.setTitle(R.string.change_settings);
            builder.setCancelable(false);
            builder.setView(view);
            alertdialog_language_spinner = (Spinner) view.findViewById(R.id.setting_alertdialog_language_spinner);
            alertdialog_font_spinner = (Spinner) view.findViewById(R.id.setting_alertdialog_font_size_spinner);

            final String [] language_list = getResources().getStringArray(R.array.language_array);
            language_adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, language_list);
            language_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alertdialog_language_spinner.setAdapter(language_adapter);

            alertdialog_language_spinner.setSelection(0);

            switch (initial_language)
            {
                case "English": alertdialog_language_spinner.setSelection(0); break;
                case "Simplified Chinese": alertdialog_language_spinner.setSelection(1); break;
                case "Traditional Chinese": alertdialog_language_spinner.setSelection(2); break;
                case "Dutch": alertdialog_language_spinner.setSelection(3);break;
                case "French": alertdialog_language_spinner.setSelection(4);break;
                case "German": alertdialog_language_spinner.setSelection(5);break;
                case "Italian": alertdialog_language_spinner.setSelection(6);break;
                case "Japanese": alertdialog_language_spinner.setSelection(7);break;
                case "Portuguese": alertdialog_language_spinner.setSelection(8);break;
                case "Russian": alertdialog_language_spinner.setSelection(9);break;
                case "Spanish": alertdialog_language_spinner.setSelection(10);break;
                default: alertdialog_language_spinner.setSelection(0);break;
            }

            alertdialog_language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (position)
                    {
                        case 0: Sharing.language = "English";break;
                        case 1: Sharing.language = "Simplified Chinese";break;
                        case 2: Sharing.language = "Traditional Chinese";break;
                        case 3: Sharing.language = "Dutch";break;
                        case 4: Sharing.language = "French";break;
                        case 5: Sharing.language = "German";break;
                        case 6: Sharing.language = "Italian";break;
                        case 7: Sharing.language = "Japanese";break;
                        case 8: Sharing.language = "Portuguese";break;
                        case 9: Sharing.language = "Russian";break;
                        case 10: Sharing.language = "Spanish";break;
                        default: Sharing.language = "English";break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            final String [] fontsize_list = getResources().getStringArray(R.array.font_array);
            font_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fontsize_list);
            font_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alertdialog_font_spinner.setAdapter(font_adapter);

            alertdialog_font_spinner.setSelection(0);

            if (!initial_isScale)
            {
                alertdialog_font_spinner.setSelection(0);
            }
            else
            {
                alertdialog_font_spinner.setSelection(1);
            }

            alertdialog_font_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (position)
                    {
                        case 0: Sharing.isScale = false;break;
                        case 1: Sharing.isScale = true;break;
                        default: Sharing.isScale = false;break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            builder.setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!Sharing.language.equalsIgnoreCase(initial_language))
                    {
                        Intent intent = new Intent (TestSelectionActivity.this, TransferTestSelectionActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        startActivity(intent);
                        TestSelectionActivity.this.finish();
                    }
                    else if (Sharing.isScale != initial_isScale)
                    {
                        Intent intent = new Intent (TestSelectionActivity.this, TransferTestSelectionActivity.class);
                        intent.putExtra("user_id", String.valueOf(user_id));
                        startActivity(intent);
                        TestSelectionActivity.this.finish();
                    }
                    else
                    {
                        Toast.makeText(TestSelectionActivity.this, R.string.no_setting_changes, Toast.LENGTH_LONG).show();
                    }
                }
            });
            builder.setNegativeButton(R.string.discard_changes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here;
                    // null
                    // revert the initial values;
                    Toast.makeText(TestSelectionActivity.this, R.string.setting_discarded, Toast.LENGTH_LONG).show();
                    Sharing.language = initial_language;
                    Sharing.isScale = initial_isScale;
                }
            });
            builder.create();
            builder.show();


        }
        else if (id == R.id.action_user_info)
        {
            Intent intent = new Intent (TestSelectionActivity.this, PersonalInformationActivity.class);
            startActivity(intent);
            TestSelectionActivity.this.finish();
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
                Toast.makeText(this, R.string.press_again_to_switch_user, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(TestSelectionActivity.this);
                builder.setTitle(R.string.switch_user);
                builder.setCancelable(false);
                builder.setMessage(R.string.want_to_switch_user);
                builder.setPositiveButton(R.string.switch_user_upper, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getNumber_of_test_in_total() == 0)
                        {
                            Sharing.test_selection_has_already_asked_file_issue = false;
                            Intent intent = new Intent(TestSelectionActivity.this, SettingPageActivity.class);
                            startActivity(intent);
                            TestSelectionActivity.this.finish();
                        }
                        else
                        {
                            Sharing.test_selection_has_already_asked_file_issue = true;
                            LayoutInflater inflater = LayoutInflater.from(TestSelectionActivity.this);
                            View view = inflater.inflate(R.layout.store_file_option_alertdialog, null);
                            AlertDialog.Builder builder = new AlertDialog.Builder(TestSelectionActivity.this);
                            builder.setTitle(R.string.store_data_file_before_switching_user);
                            builder.setCancelable(false);
                            builder.setView(view);

                            store_file_option_spinner = (Spinner) view.findViewById(R.id.store_data_file_alertdialog_spinner);

                            final String [] store_file_option_list = getResources().getStringArray(R.array.store_file_option);
                            store_file_option_adapter = new ArrayAdapter(TestSelectionActivity.this , android.R.layout.simple_spinner_dropdown_item, store_file_option_list);
                            store_file_option_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            store_file_option_spinner.setAdapter(store_file_option_adapter);

                            store_file_option_spinner.setSelection(0);
                            selected_store_file_option = 0;
                            store_file_option_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    switch (position)
                                    {
                                        case 0: selected_store_file_option = 0; break;
                                        case 1: selected_store_file_option = 1; break;
                                        case 2: selected_store_file_option = 2; break;
                                        default: selected_store_file_option = 0; break;
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (selected_store_file_option == 0)
                                    {
                                        Sharing.redirect_source = "switch_setting";
                                        Intent intent = new Intent (TestSelectionActivity.this, SendDataEmailActivity.class);


                                        startActivity(intent);
                                        TestSelectionActivity.this.finish();

                                    }
                                    else if (selected_store_file_option == 1)
                                    {
                                        Sharing.redirect_source = "switch_setting";
                                        Intent intent = new Intent(TestSelectionActivity.this, StoreDataFileActivity.class);


                                        startActivity(intent);
                                        TestSelectionActivity.this.finish();

                                    }
                                    else if (selected_store_file_option == 2)
                                    {
                                        Intent intent = new Intent (TestSelectionActivity.this, SettingPageActivity.class);
                                        startActivity(intent);
                                        TestSelectionActivity.this.finish();
                                    }
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


