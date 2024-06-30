package com.bdtopcoder.smartadmob;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class OpenAdManager extends Application implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    AppOpenAdManager appOpenAdManager;
    Activity currentActivity;


    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = new AppOpenAdManager();
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        appOpenAdManager.showAdIfAvailable(currentActivity);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
    }

    public void loadAd(@NonNull Activity activity) {
        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
            appOpenAdManager.loadAd(activity);
        }
    }

    public void showAdIfAvailable(
            @NonNull Activity activity, @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    private class AppOpenAdManager {

        String LOG_TAG = "AppOpenAdManager";
        // String AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921";

        GoogleMobileAdsConsentManager googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(getApplicationContext());
        boolean isLoadingAd = false;
        boolean isShowingAd = false;

        long loadTime = 0;


        public AppOpenAdManager() {
        }

        private void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context,
                    AdmobAdUnit.ADMOB_OPEN_AD,
                    request,
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            AdmobAdUnit.openAd = ad;
                            isLoadingAd = false;
                            loadTime = (new Date()).getTime();
                            // Log.d(LOG_TAG, "onAdLoaded.");
                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            isLoadingAd = false;
                            // Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                        }
                    });

        }

        private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
            long dateDifference = (new Date()).getTime() - loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        private boolean isAdAvailable() {
            return AdmobAdUnit.openAd != null && wasLoadTimeLessThanNHoursAgo(4);
        }

        private void showAdIfAvailable(@NonNull final Activity activity) {
            showAdIfAvailable(
                    activity,
                    new OnShowAdCompleteListener() {
                        @Override
                        public void onShowAdComplete() {

                        }
                    });
        }

        private void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {

            if (AdmobAdUnit.ADMOB_AD_IS_ON) {
                if (isShowingAd) {
                    //  Log.d(LOG_TAG, "The app open ad is already showing.");
                    return;
                }

                if (!isAdAvailable()) {
                    // Log.d(LOG_TAG, "The app open ad is not ready yet.");
                    onShowAdCompleteListener.onShowAdComplete();
                    if (googleMobileAdsConsentManager.canRequestAds()) {
                        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
                            loadAd(currentActivity);
                        }

                    }
                    return;
                }

                Log.d(LOG_TAG, "Will show ad.");

                AdmobAdUnit.openAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                AdmobAdUnit.openAd = null;
                                isShowingAd = false;
                                // Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");

                                onShowAdCompleteListener.onShowAdComplete();
                                if (googleMobileAdsConsentManager.canRequestAds()) {
                                    if (AdmobAdUnit.ADMOB_AD_IS_ON) {
                                        loadAd(activity);
                                    }

                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                AdmobAdUnit.openAd = null;
                                isShowingAd = false;
                                // Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());

                                onShowAdCompleteListener.onShowAdComplete();
                                if (googleMobileAdsConsentManager.canRequestAds()) {
                                    if (AdmobAdUnit.ADMOB_AD_IS_ON) {
                                        loadAd(activity);
                                    }

                                }
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Log.d(LOG_TAG, "onAdShowedFullScreenContent.");
                            }
                        });

                isShowingAd = true;
                AdmobAdUnit.openAd.show(activity);
            } else {
                onShowAdCompleteListener.onShowAdComplete();
            }
        }
    } // AppOpenAdManager end here =======

} // public class end here ================
