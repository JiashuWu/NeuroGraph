package com.example.jiashuwu.neurograph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntroductionPageActivity extends AppCompatActivity {

    private TextView introduction_textview;
    private TextView introduction_content_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextScaleUtils.scaleTextSize(IntroductionPageActivity.this, Sharing.isScale);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);

        introduction_textview = (TextView) findViewById(R.id.introduction_page_centre_textview);
        introduction_content_textview = (TextView) findViewById(R.id.introduction_page_content_textview);

        // ANIMATION NUMBER 1
        introduction_textview.setText("Hello");
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 3.0f);
        alphaAnimation.setDuration(3000);
        introduction_textview.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // ANIMATION NUMBER 2
                introduction_textview.setText("Hello");
                AlphaAnimation alphaAnimation1 = new AlphaAnimation(3.0f, 0.0f);
                alphaAnimation1.setDuration(2000);
                introduction_textview.setAnimation(alphaAnimation1);
                alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // ANIMATION NUMBER 3
                        introduction_textview.setText("Welcome to Neurograph");
                        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 3.0f);
                        alphaAnimation2.setDuration(3000);
                        introduction_textview.setAnimation(alphaAnimation2);
                        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                // ANIMATION NUMBER 4
                                introduction_textview.setText("Welcome to Neurograph");
                                AlphaAnimation alphaAnimation3 = new AlphaAnimation(3.0f, 0.0f);
                                alphaAnimation3.setDuration(2000);
                                introduction_textview.setAnimation(alphaAnimation3);
                                alphaAnimation3.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        introduction_textview.setText("");
                                        // ANIMATION NUMBER 5
                                        introduction_content_textview.setText(getResources().getString(R.string.introduction_content));
                                        AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 6.0f);
                                        alphaAnimation4.setDuration(2000);
                                        introduction_content_textview.setAnimation(alphaAnimation4);
                                        alphaAnimation4.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {

                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {

                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
