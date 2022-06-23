package com.irs.ghani.caltax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.irs.ghani.caltax.individual.IndividualSalary;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mNext;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNext = findViewById(R.id.btn_home_next);
        mNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_home_next:
            {
                intent = new Intent(MainActivity.this , IndividualSalary.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void openActivity(Intent intent)
    {
        startActivity(intent);
    }
}