package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.irs.ghani.caltax.R;

public class TaxCreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_credits);

        // Find the toolbar view inside the activity layout
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(mToolbar);
        //  toolbar.setTitle("Individual");
        getSupportActionBar().setTitle("Individual");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // mToolbar.setTitleTextAppearance(this, R.style.ToolbarTheme);

    }
}