package com.bdtopcoder.smartadmob;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;

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

        try {

            new AdmobAd(this, new AdmobAdCallBack() {
                @Override
                public void onAdClicked() {

                }

                @Override
                public void onAdClosed() {

                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {

                }

                @Override
                public void onAdImpression() {

                }

                @Override
                public void onAdLoaded() {

                }

                @Override
                public void onAdOpened() {

                }

            }).initializeAdmobAd().loadBanner(findViewById(R.id.bannerAd));

        } catch (Exception e) {
            Log.d("AtikulTest", "DeadObjectException");

        }

        AdmobAdUnit.ADMOB_INTERSTITIAL_AD = "ca-app-pub-3940256099942544/1033173712";
        findViewById(R.id.fullScreenAd).setOnClickListener(v -> {
            new AdmobAd(this, new AdmobAdCallBack() {
                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    Toast.makeText(MainActivity.this, "onAdFailedToLoad", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Toast.makeText(MainActivity.this, "onAdFailedToShowFullScreenContent", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdDismissed() {
                    startActivity(new Intent(MainActivity.this,SplashScreen.class));
                }
            }).loadAdmobInterstitialAd().showAdmobInterstitial(true);
        });

    }
}