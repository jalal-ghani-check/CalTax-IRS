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

import com.irs.ghani.caltax.R;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.ProgressBarAnimation;

public class IndividualBusinessActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;
    Intent intent;
    ProgressBar progressBar;
    TextView mTextViewProgress;
    TextView mTextViewProgressRemaining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_individual_business);
        setViews();
        setListeners();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startTopBar();
    }

    private void startTopBar() {

        //ProgressBar Animation

        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, (float) Helper.getProgress());
        anim.setDuration(1000);
        progressBar.startAnimation(anim);

        //ProgressBar Animation Text Value
        ValueAnimator animator = ValueAnimator.ofInt(0, (int) Helper.getProgress());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                mTextViewProgress.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        animator.start();

        //ProgressBar Remaining Value
        mTextViewProgressRemaining.setText(Helper.currentScreensSelection + "/" + Helper.totalScreensSelection);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Helper.currentScreensSelection > 1)
            Helper.currentScreensSelection--;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setViews()
    {
        mToolbar =  findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualBusiness_next);
        mTextViewProgressRemaining = findViewById(R.id.textView_individualBusiness_remainigProgress);
        progressBar = findViewById(R.id.progressBar_individualBusiness);
        mTextViewProgress = findViewById(R.id.textView_individualBusiness_progressValue);

        intent = new Intent(IndividualBusinessActivity.this , IndividualCapitalGainActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListeners()
    {
        mNext.setOnClickListener(view -> {
            Helper.currentScreensSelection++;
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(IndividualBusinessActivity.this);
            startActivity(intent, options.toBundle());
        });
    }


    public void setAnimation() {

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(400);
        slide.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(slide);
    }
}