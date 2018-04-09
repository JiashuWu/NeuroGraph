package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ImageSelectionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private View view_spiral;
    private View view_pentagon;

    private ArrayList<View> viewList;

    private PagerAdapter pageAdapter;

    private Button spiral_button;
    private Button pentagon_button;

    private int user_id;
    private String test_type = "";
    private String image_type = "";

    private long exitTime;

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



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_spiral:
                    viewPager.setCurrentItem(0);
                    spiral_button = (Button) findViewById(R.id.image_selection_spiral_button);
                    spiral_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Sharing.sharing_image = "spiral";
                            if (test_type.equalsIgnoreCase("static_full_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "spiral";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("static_corner_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "spiral";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "spiral";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "spiral";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                        }
                    });
                    return true;
                case R.id.navigation_pentagon:
                    viewPager.setCurrentItem(1);
                    pentagon_button = (Button) findViewById(R.id.image_selection_pentagon_button);
                    pentagon_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Sharing.sharing_image = "pentagon";
                            if (test_type.equalsIgnoreCase("static_full_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "pentagon";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("static_corner_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "pentagon";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "pentagon";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                            else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                            {
                                Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                                intent.putExtra("user_id", String.valueOf(user_id));
                                intent.putExtra("test_type", test_type);
                                image_type = "pentagon";
                                intent.putExtra("image_type", image_type);
                                startActivity(intent);
                            }
                        }
                    });
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            TextScaleUtilsLower.scaleTextSize(ImageSelectionActivity.this, Sharing.isScale);
        }
        else
        {
            TextScaleUtils.scaleTextSize(ImageSelectionActivity.this, Sharing.isScale);
        }
        init_theme();
        initLocaleLanguage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());
        test_type = getIntent().getStringExtra("test_type").toString();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = (ViewPager) findViewById(R.id.image_selection_viewpager);

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
        view_spiral = inflater.inflate(R.layout.image_selection_page1_layout, null);
        view_pentagon = inflater.inflate(R.layout.image_selection_page2_layout, null);

        viewList = new ArrayList<View>();
        viewList.add(view_spiral);
        viewList.add(view_pentagon);

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

        spiral_button = (Button) view_spiral.findViewById(R.id.image_selection_spiral_button);
        spiral_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sharing.sharing_image = "spiral";
                if (test_type.equalsIgnoreCase("static_full_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "spiral";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("static_corner_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "spiral";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "spiral";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "spiral";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
            }
        });

        pentagon_button = (Button) view_pentagon.findViewById(R.id.image_selection_pentagon_button);
        pentagon_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sharing.sharing_image = "pentagon";
                if (test_type.equalsIgnoreCase("static_full_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "pentagon";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("static_corner_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "pentagon";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("dynamic_blank_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "pentagon";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
                else if (test_type.equalsIgnoreCase("dynamic_seasonal_background"))
                {
                    Intent intent = new Intent(ImageSelectionActivity.this, DurationSelectionActivity.class);
                    intent.putExtra("user_id", String.valueOf(user_id));
                    intent.putExtra("test_type", test_type);
                    image_type = "pentagon";
                    intent.putExtra("image_type", image_type);
                    startActivity(intent);
                    ImageSelectionActivity.this.finish();
                }
            }
        });
    }

    // TODO OPTIONAL 3/4


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(ImageSelectionActivity.this, TestSelectionActivity.class);
            intent.putExtra("user_id", String.valueOf(user_id));
            startActivity(intent);
            ImageSelectionActivity.this.finish();
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
