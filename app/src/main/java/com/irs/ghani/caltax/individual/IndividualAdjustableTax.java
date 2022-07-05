package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.irs.ghani.caltax.R;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.ProgressBarAnimation;

public class IndividualAdjustableTax extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;

    ProgressBar progressBar;
    TextView mTextViewProgress;
    TextView mTextViewProgressRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_adjustable_tax);
        setViews();

        mNext.setOnClickListener(view -> Toast.makeText(IndividualAdjustableTax.this, "Summary page design pending" , Toast.LENGTH_LONG).show());

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


    private void setViews()
    {
        mToolbar =  findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualAdjustableTax_next);
        progressBar = findViewById(R.id.progressBar_individualTaxCredits);
        mTextViewProgress = findViewById(R.id.textView_individualTaxCredits_progressValue);
        mTextViewProgressRemaining = findViewById(R.id.textView_individualTaxCredits_remainingProgress);

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