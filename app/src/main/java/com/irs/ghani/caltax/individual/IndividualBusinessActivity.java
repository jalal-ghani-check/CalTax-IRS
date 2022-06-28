package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.irs.ghani.caltax.R;

public class IndividualBusinessActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business);

        // Find the toolbar view inside the activity layout
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(mToolbar);
        //  toolbar.setTitle("Individual");
        getSupportActionBar().setTitle("Individual");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button mNext = findViewById(R.id.btn_individualBusiness_next);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualBusinessActivity.this , IndividualCapitalGainActivity.class);
                startActivity(intent);
            }
        });

    }
}