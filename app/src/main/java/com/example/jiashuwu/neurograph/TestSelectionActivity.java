package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.Locale;

public class TestSelectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private View view_practice;
    private View view_static;
    private View view_dynamic;

    private ArrayList<View> viewList;

    private PagerAdapter pageAdapter;

    private Button practice_button;
    private Button parallel_test_button;
    private Button test1_button;
    private Button test2_button;
    private Button test3_button;
    private Button test4_button;

    private Spinner parallel_line_practice_width_spinner;

    private int user_id;

    private String test_type = "";

    private long exitTime;

    private int painter_width = 0;

    private ArrayAdapter adapter;

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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_practice_tests:
                    viewPager.setCurrentItem(0);
                    practice_button = (Button) findViewById(R.id.test_selection_page0_test0_button);
                    parallel_test_button = (Button) findViewById(R.id.test_selection_page0_parallel_line_test_button);
                    parallel_line_practice_width_spinner = (Spinner) findViewById(R.id.test_selection_test0_width_spinner);
                    String [] mList = getResources().getStringArray(R.array.painter_width);
                    adapter = new ArrayAdapter(TestSelectionActivity.this , android.R.layout.simple_spinner_dropdown_item, mList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

                    practice_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TestSelectionActivity.this, ParallelLinePracticeActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "parallel_line_practice";
                            Sharing.painter_width = painter_width;
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
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
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.navigation_static_tests:
                    viewPager.setCurrentItem(1);
                    test1_button = (Button) findViewById(R.id.test_selection_page1_test1_button);
                    test2_button = (Button) findViewById(R.id.test_selection_page1_test2_button);

                    test1_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "static_full_background";
                            intent.putExtra("test_type", test_type);
                            startActivity(intent);
                        }
                    });

                    test2_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "static_corner_background";
                            intent.putExtra("test_type", test_type);
                            // TODO FIX
                            startActivity(intent);

                        }
                    });

                    break;
                case R.id.navigation_dynamic_tests:
                    viewPager.setCurrentItem(2);
                    test3_button = (Button) findViewById(R.id.test_selection_page2_test3_button);
                    test4_button = (Button) findViewById(R.id.test_selection_page2_test4_button);

                    test3_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "dynamic_blank_background";
                            intent.putExtra("test_type", test_type);
                            // TODO FIX
                            startActivity(intent);

                        }
                    });

                    test4_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                            intent.putExtra("user_id", String.valueOf(user_id));
                            test_type = "dynamic_seasonal_background";
                            intent.putExtra("test_type", test_type);
                            // TODO FIX
                            startActivity(intent);

                        }
                    });

                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(TestSelectionActivity.this, Sharing.isScale);
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        test1_button = (Button) view_static.findViewById(R.id.test_selection_page1_test1_button);
        test2_button = (Button) view_static.findViewById(R.id.test_selection_page1_test2_button);
        test3_button = (Button) view_dynamic.findViewById(R.id.test_selection_page2_test3_button);
        test4_button = (Button) view_dynamic.findViewById(R.id.test_selection_page2_test4_button);
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


        practice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestSelectionActivity.this, ParallelLinePracticeActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "parallel_line_practice";
                Sharing.painter_width = painter_width;
                intent.putExtra("test_type", test_type);
                startActivity(intent);
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
                startActivity(intent);
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

            }
        });

        test2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "static_corner_background";
                intent.putExtra("test_type", test_type);
                // TODO FIX
                startActivity(intent);

            }
        });

        test3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "dynamic_blank_background";
                intent.putExtra("test_type", test_type);
                // TODO FIX
                startActivity(intent);

            }
        });

        test4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestSelectionActivity.this, ImageSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                test_type = "dynamic_seasonal_background";
                intent.putExtra("test_type", test_type);
                // TODO FIX
                startActivity(intent);
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
            // TODO FIX
            startActivity(intent);
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
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                System.exit(0);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


