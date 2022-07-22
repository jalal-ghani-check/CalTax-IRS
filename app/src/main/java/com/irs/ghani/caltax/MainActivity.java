package com.irs.ghani.caltax;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.irs.ghani.caltax.individual.IndividualAdjustableTax;
import com.irs.ghani.caltax.individual.IndividualBusinessActivity;
import com.irs.ghani.caltax.individual.IndividualCapitalGainActivity;
import com.irs.ghani.caltax.individual.IndividualPropertyActivity;
import com.irs.ghani.caltax.individual.IndividualSalary;
import com.irs.ghani.caltax.individual.TaxCreditsActivity;
import com.irs.ghani.caltax.util.Helper;
import com.irs.ghani.caltax.util.TaxModelHelper;

public class MainActivity extends AppCompatActivity {

    Button mNext;
    Intent intent;

    CheckBox mSalary;
    CheckBox mCapitalGain;
    CheckBox mProperty;
    CheckBox mOtherSources;
    CheckBox mBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_main);
        setViews();
        setListeners();


    }

    @Override
    protected void onResume() {
        super.onResume();
       // Helper.reset();
    }

    private void setViews() {
        mNext = findViewById(R.id.btn_home_next);
        mSalary = findViewById(R.id.checkboxSalary);
        mCapitalGain = findViewById(R.id.checkboxCapitalGain);
        mProperty = findViewById(R.id.checkBoxProperty);
        mOtherSources = findViewById(R.id.checkBoxOtherSources);
        mBusiness = findViewById(R.id.checkBoxBusiness);

    }

    private void setListeners() {
        mNext.setOnClickListener(view -> {
            decideActivity();
        });

        mSalary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TaxModelHelper.isIndividualSalary = true;
                    TaxModelHelper.totalScreensSelection++;
                } else {
                    TaxModelHelper.isIndividualSalary = false;
                    TaxModelHelper.totalScreensSelection--;
                }
            }
        });
        mProperty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TaxModelHelper.isIndividualProperty = true;
                    TaxModelHelper.totalScreensSelection++;
                } else {
                    TaxModelHelper.isIndividualProperty = false;
                    TaxModelHelper.totalScreensSelection--;
                }
            }
        });
        mBusiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TaxModelHelper.isIndividualBusiness = true;
                    TaxModelHelper.totalScreensSelection++;
                } else {
                    TaxModelHelper.isIndividualBusiness = false;
                    TaxModelHelper.totalScreensSelection--;
                }
            }
        });
        mCapitalGain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TaxModelHelper.isIndividualCapitalGain = true;
                    if (!TaxModelHelper.isIndividualOtherSources)
                        TaxModelHelper.totalScreensSelection++;
                } else {
                    TaxModelHelper.isIndividualCapitalGain = false;
                    if (!TaxModelHelper.isIndividualOtherSources)
                        TaxModelHelper.totalScreensSelection--;
                }
            }
        });
        mOtherSources.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TaxModelHelper.isIndividualOtherSources = true;
                    if (!TaxModelHelper.isIndividualCapitalGain)
                        TaxModelHelper.totalScreensSelection++;
                } else {
                    TaxModelHelper.isIndividualOtherSources = false;
                    if (!TaxModelHelper.isIndividualCapitalGain)
                        TaxModelHelper.totalScreensSelection--;
                }
            }
        });
    }

    private void decideActivity() {

        if (TaxModelHelper.isIndividualSalary) {
            intent = new Intent(MainActivity.this, IndividualSalary.class);
        }
        else if (TaxModelHelper.isIndividualProperty) {
            intent = new Intent(MainActivity.this, IndividualPropertyActivity.class);
        }
        else if (TaxModelHelper.isIndividualBusiness) {
            intent = new Intent(MainActivity.this, IndividualBusinessActivity.class);
        }
        else if (TaxModelHelper.isIndividualCapitalGain || TaxModelHelper.isIndividualOtherSources) {
            intent = new Intent(MainActivity.this, IndividualCapitalGainActivity.class);
        }
        else if (TaxModelHelper.totalScreensSelection < 4) {
            Toast.makeText(MainActivity.this, "Kindly Select atleast 1 Type of income", Toast.LENGTH_LONG).show();
            return;
        }
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void setAnimation() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(400);
        slide.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(slide);
    }
}