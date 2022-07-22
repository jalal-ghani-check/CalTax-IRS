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
import com.irs.ghani.caltax.util.TaxModelHelper;

public class TaxCreditsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_tax_credits);
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
        mTextViewProgressRemaining.setText(TaxModelHelper.currentScreensSelection + "/" + TaxModelHelper.totalScreensSelection);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (TaxModelHelper.currentScreensSelection > 1)
            TaxModelHelper.currentScreensSelection--;
    }


    private void setViews()
    {
        mToolbar =  findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualTaxCredits_next);
        progressBar = findViewById(R.id.progressBar_individualTaxCredits);
        mTextViewProgress = findViewById(R.id.textView_individualTaxCredits_progressValue);
        mTextViewProgressRemaining = findViewById(R.id.textView_individualTaxCredits_remainingProgress);
        intent = new Intent(TaxCreditsActivity.this , IndividualAdjustableTax.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListeners()
    {
        mNext.setOnClickListener(view -> {
            TaxModelHelper.currentScreensSelection++;
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(TaxCreditsActivity.this);
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