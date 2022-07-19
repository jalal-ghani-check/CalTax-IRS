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
import android.util.Log;
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

public class IndividualPropertyActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;
    Intent intent;

    ProgressBar progressBar;
    TextView mTextViewProgress;
    TextView mTextViewProgressRemaining;

    EditText mRentReceived;
    EditText mAmountNotAdjustableAgainstRent;
    EditText mFortifiedDeposit;
    EditText mTotalDeduction;
    EditText mTaxableIncomeFromProperty;

    String rentReceived = "";
    String amountNotAdjustable = "";
    String fortifiedDeposit = "";
    String totalDeduction = "";
    String taxableIncomeFromProperty = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_individual_property);
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

    private void setViews() {
        mToolbar = findViewById(R.id.toolbar);
        mNext = findViewById(R.id.btn_individualProperty_next);
        progressBar = findViewById(R.id.progressBar);
        mTextViewProgress = findViewById(R.id.textView_individualProperty_progressValue);
        mTextViewProgressRemaining = findViewById(R.id.textView_individualProperty_remainingProgress);
        mRentReceived = findViewById(R.id.editText_individualProperty_rentReceived);
        mAmountNotAdjustableAgainstRent = findViewById(R.id.editText_individualProperty_AdjustableRent);
        mFortifiedDeposit = findViewById(R.id.editText_individualProperty_contractForSale);
        mTotalDeduction = findViewById(R.id.editText_individualProperty_totalDeduction);
        mTaxableIncomeFromProperty = findViewById(R.id.editText_individualProperty_totalIncomeTax);

        intent = new Intent(IndividualPropertyActivity.this, IndividualBusinessActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListeners() {

        mRentReceived.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromProperty = Integer.toString(calculateTaxableIncomeFromProperty());
                    mTaxableIncomeFromProperty.setText(taxableIncomeFromProperty);
                } else {
                    mTaxableIncomeFromProperty.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mAmountNotAdjustableAgainstRent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromProperty = Integer.toString(calculateTaxableIncomeFromProperty());
                    mTaxableIncomeFromProperty.setText(taxableIncomeFromProperty);
                } else {
                    mTaxableIncomeFromProperty.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mFortifiedDeposit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromProperty = Integer.toString(calculateTaxableIncomeFromProperty());
                    mTaxableIncomeFromProperty.setText(taxableIncomeFromProperty);
                } else {
                    mTaxableIncomeFromProperty.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mTotalDeduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    taxableIncomeFromProperty = Integer.toString(calculateTaxableIncomeFromProperty());
                    mTaxableIncomeFromProperty.setText(taxableIncomeFromProperty);
                } else {
                    mTaxableIncomeFromProperty.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mNext.setOnClickListener(view -> {
            Helper.currentScreensSelection++;
            decideActivity();
        });
    }

    private void decideActivity() {

       if (Helper.isIndividualBusiness) {
            intent = new Intent(IndividualPropertyActivity.this, IndividualBusinessActivity.class);
        } else if (Helper.isIndividualCapitalGain || Helper.isIndividualOtherSources) {
            intent = new Intent(IndividualPropertyActivity.this, IndividualCapitalGainActivity.class);
        } else {
            intent = new Intent(IndividualPropertyActivity.this, IndividualDeductableAllowance.class);
        }

        if (Integer.parseInt(taxableIncomeFromProperty) > 0) {

            Helper.setTaxableIncomeFromProperty(Integer.parseInt(taxableIncomeFromProperty));
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(IndividualPropertyActivity.this);
            startActivity(intent, options.toBundle());
        } else {
            Toast.makeText(IndividualPropertyActivity.this, "Property income must be greater than Total Deduction", Toast.LENGTH_LONG).show();
        }

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

    private int calculateTaxableIncomeFromProperty() {
        int rentReceived = Integer.parseInt(mRentReceived.getText().toString());
        int amountNotAdjustable = Integer.parseInt(mAmountNotAdjustableAgainstRent.getText().toString());
        int fortifiedDeposit = Integer.parseInt(mFortifiedDeposit.getText().toString());
        int totalDeduction = Integer.parseInt(mTotalDeduction.getText().toString());
        int totalAmount = ((rentReceived + amountNotAdjustable + fortifiedDeposit) - totalDeduction);


        return totalAmount > 0 ? totalAmount : -1;
    }


}