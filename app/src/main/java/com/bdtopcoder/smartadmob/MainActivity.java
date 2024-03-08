package com.bdtopcoder.smartadmob;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set admob ad unit id
        AdmobAdUnit.ADMOB_BANNER_AD = "ca-app-pub-3940256099942544/9214589741";

        // gdpr msg
        GDPR gdpr = new GDPR(this);
        gdpr.setGDPR();

        AdmobAd admobAd = new AdmobAd(this);

        // sdk initialize
        admobAd.initializeAdmobAd();

        // Banner Ad
        admobAd.loadBanner(findViewById(R.id.adLayout));


    }
}