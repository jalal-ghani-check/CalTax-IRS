package com.irs.ghani.caltax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        //            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
        //            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
        //                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //                setExitTransitionAndActivity(intent);
        handler.postDelayed(this::startLocalActivity, 4000);

    }

    private void startLocalActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}