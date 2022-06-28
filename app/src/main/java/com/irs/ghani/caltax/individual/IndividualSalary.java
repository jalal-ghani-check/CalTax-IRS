package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.irs.ghani.caltax.MainActivity;
import com.irs.ghani.caltax.R;

import java.util.Properties;

public class IndividualSalary extends AppCompatActivity{

    Toolbar mToolbar;
    Button mNext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_individual_salary);
        setViews();
        setListeners();


    }

    private void setViews()
    {
         mToolbar =  findViewById(R.id.toolbar);
         mNext = findViewById(R.id.btn_individualSalary_next);
         intent = new Intent(IndividualSalary.this , IndividualPropertyActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListeners()
    {
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > 20) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(IndividualSalary.this);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
           // getWindow().setEnterTransition(slide);
        }
    }


}