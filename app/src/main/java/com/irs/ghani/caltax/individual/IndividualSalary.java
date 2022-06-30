package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.irs.ghani.caltax.MainActivity;
import com.irs.ghani.caltax.R;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.ProgressBarAnimation;

import java.util.Properties;

public class IndividualSalary extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;
    Intent intent;
    TextView mTextViewProgressRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_individual_salary);
        setViews();
        setListeners();


    }

    private void setViews() {
        mToolbar = findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualSalary_next);
        mTextViewProgressRemaining = findViewById(R.id.textView_individualSalary_remainingProgress);
        intent = new Intent(IndividualSalary.this, IndividualPropertyActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextViewProgressRemaining.setText(Helper.currentScreensSelection+"/"+Helper.totalScreensSelection);
    }

    private void setListeners() {
        mNext.setOnClickListener(view -> {
            Helper.currentScreensSelection++;
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(IndividualSalary.this);
            startActivity(intent, options.toBundle());
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setAnimation() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(400);
        slide.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(slide);
    }


}