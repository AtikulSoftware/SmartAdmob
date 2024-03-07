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

        GDPR gdpr = new GDPR(this);
        gdpr.setGDPR();

        // Banner Ad
        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.adLayout));





    }
}