package com.example.jiashuwu.neurograph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class TutorialsActivity extends AppCompatActivity {

    private ArrayList<String> title;
    private ArrayList<String> description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

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





    }
}
