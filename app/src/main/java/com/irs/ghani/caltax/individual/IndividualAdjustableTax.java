package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.irs.ghani.caltax.R;

public class IndividualAdjustableTax extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_adjustable_tax);
        setViews();

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IndividualAdjustableTax.this, "Summary page design pending" , Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setViews()
    {
        mToolbar =  findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualAdjustableTax_next);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}