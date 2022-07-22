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

import com.irs.ghani.caltax.MainActivity;
import com.irs.ghani.caltax.R;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.ProgressBarAnimation;
import com.irs.ghani.caltax.util.TaxModelHelper;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Properties;

public class IndividualSalary extends AppCompatActivity {

    Toolbar mToolbar;
    Button mNext;
    Intent intent;
    TextView mTextViewProgressRemaining;
    EditText mSalary;
    EditText mSalaryExempt;
    TextView mTaxableSalary;
    double taxableSalary ;


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
        mSalary = findViewById(R.id.editText_individualSalary_salary);
        mSalaryExempt = findViewById(R.id.editText_individualSalary_ExemptSalary);
        mTaxableSalary = findViewById(R.id.editText_individualSalary_TaxableSalary);
      //  mTaxableSalary.setEnabled(false);
        intent = new Intent(IndividualSalary.this, IndividualPropertyActivity.class);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Individual");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextViewProgressRemaining.setText(TaxModelHelper.currentScreensSelection + "/" + TaxModelHelper.totalScreensSelection);
    }

    private void setListeners() {

        mNext.setOnClickListener(view -> {
            TaxModelHelper.currentScreensSelection++;
            decideActivity();
        });

        mSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    mTaxableSalary.setText(calculateTaxableSalary());
                } else {
                    mSalary.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSalaryExempt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    mTaxableSalary.setText(calculateTaxableSalary());
                } else {
                    mSalaryExempt.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private String calculateTaxableSalary() {

        double salary = Double.parseDouble(mSalary.getText().toString());
        double exemptSalary = Double.parseDouble(mSalaryExempt.getText().toString());
        taxableSalary = salary - exemptSalary;
        return taxableSalary > 0 ? Helper.convertExponentToReadableString(taxableSalary) : "-1";
    }

    private void decideActivity() {

        if (TaxModelHelper.isIndividualProperty) {
            intent = new Intent(IndividualSalary.this, IndividualPropertyActivity.class);
        } else if (TaxModelHelper.isIndividualBusiness) {
            intent = new Intent(IndividualSalary.this, IndividualBusinessActivity.class);
        } else if (TaxModelHelper.isIndividualCapitalGain || TaxModelHelper.isIndividualOtherSources) {
            intent = new Intent(IndividualSalary.this, IndividualCapitalGainActivity.class);
        } else {
            intent = new Intent(IndividualSalary.this, IndividualDeductableAllowance.class);
        }

        if (taxableSalary > 0) {

            TaxModelHelper.setTaxableSalary(taxableSalary);
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(IndividualSalary.this);
            startActivity(intent, options.toBundle());
        } else {
            Toast.makeText(IndividualSalary.this, "Salary must be greater than Exempt Salary", Toast.LENGTH_LONG).show();
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


}