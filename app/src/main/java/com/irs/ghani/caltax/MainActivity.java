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
import android.widget.Toast;

import com.irs.ghani.caltax.individual.IndividualAdjustableTax;
import com.irs.ghani.caltax.individual.IndividualBusinessActivity;
import com.irs.ghani.caltax.individual.IndividualCapitalGainActivity;
import com.irs.ghani.caltax.individual.IndividualPropertyActivity;
import com.irs.ghani.caltax.individual.IndividualSalary;
import com.irs.ghani.caltax.individual.TaxCreditsActivity;
import com.irs.ghani.caltax.util.Helper;

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
    }

    private void decideActivity() {
        Helper.isIndividualSalary = mSalary.isChecked();
        Helper.isIndividualProperty = mProperty.isChecked();
        Helper.isIndividualCapitalGain = mCapitalGain.isChecked();
        Helper.isIndividualOtherSources = mOtherSources.isChecked();
        Helper.isIndividualBusiness = mBusiness.isChecked();

        if (Helper.isIndividualSalary)
            intent = new Intent(MainActivity.this, IndividualSalary.class);
        else if (Helper.isIndividualBusiness)
            intent = new Intent(MainActivity.this, IndividualBusinessActivity.class);
        else if (Helper.isIndividualCapitalGain)
            intent = new Intent(MainActivity.this, IndividualCapitalGainActivity.class);
        else if (Helper.isIndividualOtherSources)
            intent = new Intent(MainActivity.this, IndividualCapitalGainActivity.class);
        else if (Helper.isIndividualProperty)
            intent = new Intent(MainActivity.this, IndividualPropertyActivity.class);
        else {
            Toast.makeText(MainActivity.this, "Kindly Select At-least 1 Type of income", Toast.LENGTH_LONG).show();
            return;
        }
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
        startActivity(intent, options.toBundle());
    }

    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
        }
    }
}