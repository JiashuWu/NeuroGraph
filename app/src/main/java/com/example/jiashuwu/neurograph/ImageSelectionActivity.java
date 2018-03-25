package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        TextScaleUtils.scaleTextSize(ImageSelectionActivity.this, Sharing.isScale);
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
    }

    // TODO OPTIONAL 3/4
    /*

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
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    */

}
