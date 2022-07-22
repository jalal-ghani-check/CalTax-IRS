package com.irs.ghani.caltax.individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.irs.ghani.caltax.R;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.ProgressBarAnimation;
import com.irs.ghani.caltax.util.TaxModelHelper;

public class IndividualBusinessActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;
    Intent intent;
    ProgressBar progressBar;
    TextView mTextViewProgress;
    TextView mTextViewProgressRemaining;

    EditText mTotalGrossProfit;
    EditText mLessAdmissibleDeduction;
    EditText mTaxableIncome;

    String taxableIncomeFromBusiness = "";


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
        mTextViewProgressRemaining.setText(TaxModelHelper.currentScreensSelection + "/" + TaxModelHelper.totalScreensSelection);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (TaxModelHelper.currentScreensSelection > 1)
            TaxModelHelper.currentScreensSelection--;
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
        mTotalGrossProfit = findViewById(R.id.editText_individualBusiness_GrossProfit);
        mLessAdmissibleDeduction = findViewById(R.id.editText_individualBusiness_admissibleDeduction);
        mTaxableIncome = findViewById(R.id.editText_individualBusiness_taxableIncome);

        intent = new Intent(IndividualBusinessActivity.this , IndividualCapitalGainActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListeners() {

        mTotalGrossProfit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromBusiness = Integer.toString(calculateTaxableIncomeFromBusiness());
                    mTaxableIncome.setText(taxableIncomeFromBusiness);
                } else {
                  //  mTotalGrossProfit.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mLessAdmissibleDeduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromBusiness = Integer.toString(calculateTaxableIncomeFromBusiness());
                    mTaxableIncome.setText(taxableIncomeFromBusiness);
                } else {
                    //  mTotalGrossProfit.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mNext.setOnClickListener(view -> {
            TaxModelHelper.currentScreensSelection++;
            decideActivity();
        });
    }

    private void decideActivity() {

        if (TaxModelHelper.isIndividualCapitalGain || TaxModelHelper.isIndividualOtherSources) {
            intent = new Intent(IndividualBusinessActivity.this, IndividualCapitalGainActivity.class);
        } else {
            intent = new Intent(IndividualBusinessActivity.this, IndividualDeductableAllowance.class);
        }

        if (Integer.parseInt(taxableIncomeFromBusiness) > 0) {

            TaxModelHelper.setTaxableIncomeFromBusiness(Integer.parseInt(taxableIncomeFromBusiness));
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(IndividualBusinessActivity.this);
            startActivity(intent, options.toBundle());
        } else {
            Toast.makeText(IndividualBusinessActivity.this, "Total Gross Profit must be greater than Less Admissible Deduction", Toast.LENGTH_LONG).show();
        }

    }


    public void setAnimation() {

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(400);
        slide.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(slide);
    }

    private int calculateTaxableIncomeFromBusiness() {
        int totalGrossProfit = Integer.parseInt(mTotalGrossProfit.getText().toString());
        int lessAdmissibleDeduction = Integer.parseInt(mLessAdmissibleDeduction.getText().toString());
        int totalAmount = totalGrossProfit - lessAdmissibleDeduction;


        return totalAmount > 0 ? totalAmount : -1;
    }
}