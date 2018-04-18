package com.example.jiashuwu.neurograph;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PersonalInformationEditPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_edit_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO WHEN KEY BACK, ASK USER WHETHER TO DISCARD THE CHANGES;
        // TODO INITIALLY, SET THE USER INFO AS INITIAL VALUE OF EACH EDITTEXT, RADIOBUTTON AND SPINNER
        // TODO SAME AS INFORMATIONCOLLECTION PAGE, WE NEED TO DECIDE WHETHER THE INPUTS ARE VALID
        // TODO ALSO CHECK WHETHER THE REGISTRATION CODE IS UNIQUE OR NOT;
        // TODO WE NEED TO HAVE TWO BUTTONS FIRST ONE IS FINISH, THE SECOND ONE IS DISCARD
        // TODO WE ALSO NEED TO HAVE TWO ALERTDIALOGS
        // TODO THE FIRST ONE IS THE CONFIRMATION ONE, WHICH CONTAINS ALL THE USER INFORMATIONS LIKE LUCKYDAYS
        // TODO THE SECOND ONE ASK THE USER WHETHER THEY CONFIRM TO DISCARD ALL THE CHANGES;

        // TODO FINALLY WE NEED TO ADD THE REGISTRATION CODE INTO FILES AND README INFORMATION;



    }
}
