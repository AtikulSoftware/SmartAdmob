package com.bdtopcoder.smartadmob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    SplashManager splashManager;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvTime = findViewById(R.id.tvTime);

        AdmobAdUnit.ADMOB_OPEN_AD = "ca-app-pub-3940256099942544/9257395921";
        splashManager = new SplashManager(SplashScreen.this, new SplashOpenAdCallBack() {
            @Override
            public void loadingTimer(long time) {
                runOnUiThread(() -> tvTime.setText(String.valueOf(time)));
            }

            @Override
            public void loadingDone(boolean isDone) {
                Toast.makeText(SplashScreen.this, "Done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void startYourActivity() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        splashManager.initialize();

    } // OnCreate method end here ========



} // public class end here ===============