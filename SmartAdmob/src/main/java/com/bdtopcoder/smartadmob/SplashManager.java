package com.bdtopcoder.smartadmob;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SplashManager {

    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    private long secondsRemaining;
    private SplashOpenAdCallBack splashOpenAdCallBack;
    private Activity context;

    public SplashManager(Activity context, SplashOpenAdCallBack splashOpenAdCallBack) {
        this.context = context;
        this.splashOpenAdCallBack = splashOpenAdCallBack;
    }


    public void initialize() {
        createTimer(AdmobConfig.COUNTER_TIME_MILLISECONDS);

        googleMobileAdsConsentManager = GoogleMobileAdsConsentManager.getInstance(context.getApplicationContext());
        googleMobileAdsConsentManager.gatherConsent(
                context,
                consentError -> {
                    if (consentError != null) {
                       // Log.w(LOG_TAG, String.format("%s: %s", consentError.getErrorCode(), consentError.getMessage()));
                    }

                    if (googleMobileAdsConsentManager.canRequestAds()) {
                        initializeMobileAdsSdk();
                    }

                    if (secondsRemaining <= 0) {
                        startMainActivity();
                    }
                });

        if (googleMobileAdsConsentManager.canRequestAds()) {
            initializeMobileAdsSdk();
        }
    }

    private void createTimer(long time) {
        CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1;
                splashOpenAdCallBack.loadingTimer(secondsRemaining);
            }

            @Override
            public void onFinish() {
                secondsRemaining = 0;
                splashOpenAdCallBack.loadingDone(true);

                Application application = (Application) context.getApplicationContext();
                ((OpenAdManager) application).showAdIfAvailable(
                        context,
                        new OpenAdManager.OnShowAdCompleteListener() {
                            @Override
                            public void onShowAdComplete() {
                                if (googleMobileAdsConsentManager.canRequestAds()) {
                                    startMainActivity();
                                }
                            }
                        });
            }
        };
        countDownTimer.start();
    }

    private void initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context);

        // Load an ad.
        Application application = (Application) context.getApplicationContext();
        ((OpenAdManager) application).loadAd(context);
    }

    private void startMainActivity() {
        splashOpenAdCallBack.startYourActivity();
    }
}

