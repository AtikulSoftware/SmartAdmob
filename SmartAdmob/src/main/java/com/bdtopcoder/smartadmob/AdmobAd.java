package com.bdtopcoder.smartadmob;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

/*
* MD Atikul Islam
* Website - https://www.bdtopcoder.xyz/
* YouTube - https://www.youtube.com/@AwesomeDesigner
* Copyright © AtikulSoftware
*/


public class AdmobAd {
    Activity activity;
    AdmobAdCallBack admobAdCallBack;

    public AdmobAd(Activity activity, AdmobAdCallBack admobAdCallBack) {
        this.activity = activity;
        this.admobAdCallBack = admobAdCallBack;
    }

    public AdmobAd(Activity activity) {
        this.activity = activity;
    }

    public AdmobAd() {
    }

    public AdmobAd initializeAdmobAd() {
        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
            MobileAds.initialize(activity, initializationStatus -> {
            });
        }
        return this;
    } // initializeAdmobAd Ad

    public void loadBanner(LinearLayout bannerLayout) {
        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);

            float density = outMetrics.density;

            float adWidthPixels = bannerLayout.getWidth();

            if (adWidthPixels == 0) {
                adWidthPixels = outMetrics.widthPixels;
            }

            int adWidth = (int) (adWidthPixels / density);
            AdSize adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);

            // banner ad
            AdmobAdUnit.adView = new AdView(activity);
            AdmobAdUnit.adView.setAdListener(new AdListener() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    admobAdCallBack.onAdClicked();
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    admobAdCallBack.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    admobAdCallBack.onAdFailedToLoad(loadAdError);
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    admobAdCallBack.onAdImpression();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    admobAdCallBack.onAdImpression();
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                    admobAdCallBack.onAdOpened();
                }
            });

            AdmobAdUnit.adView.setAdSize(adSize);

            AdmobAdUnit.adView.setAdUnitId(AdmobAdUnit.ADMOB_BANNER_AD);
            bannerLayout.removeAllViews();
            bannerLayout.addView(AdmobAdUnit.adView);

            AdRequest adRequest = new AdRequest.Builder().build();
            AdmobAdUnit.adView.loadAd(adRequest);
        }

    } // loadBanner  end here ======

    public AdmobAd loadAdmobInterstitialAd() {
        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AdmobAdUnit.ADMOB_INTERSTITIAL_AD, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    AdmobAdUnit.interstitialAd = null;
                    admobAdCallBack.onAdFailedToLoad(loadAdError);
                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    AdmobAdUnit.interstitialAd = interstitialAd;
                    admobAdCallBack.onAdLoaded(interstitialAd);
                }
            });
        }
        return this;
    } // loadInterstitialAds end here ==========

    public void showAdmobInterstitial(boolean isAdLoad) {
        if (AdmobAdUnit.ADMOB_AD_IS_ON) {
            if (AdmobAdUnit.interstitialAd != null) {
                AdmobAdUnit.interstitialAd.show(activity);
                AdmobAdUnit.interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        admobAdCallBack.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();

                        if (isAdLoad) {
                            AdmobAdUnit.interstitialAd = null;
                            loadAdmobInterstitialAd();
                        }

                        admobAdCallBack.onAdDismissed();
                        admobAdCallBack.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        admobAdCallBack.onAdDismissed();
                        admobAdCallBack.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        admobAdCallBack.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        admobAdCallBack.onAdShowedFullScreenContent();
                    }
                });

            } else {
                admobAdCallBack.onAdDismissed();
            }

        } else {
            admobAdCallBack.onAdDismissed();
        }

    } // showAdmobInterstitial end here =========


    public AdmobAd loadAdmobRewardedAd() {
      if (AdmobAdUnit.ADMOB_AD_IS_ON){
          AdRequest adRequest = new AdRequest.Builder().build();
          RewardedAd.load(activity, AdmobAdUnit.ADMOB_REWARDED_AD,
                  adRequest, new RewardedAdLoadCallback() {
                      @Override
                      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                          AdmobAdUnit.rewardedAd = null;
                          admobAdCallBack.onAdFailedToLoad(loadAdError);
                      }

                      @Override
                      public void onAdLoaded(@NonNull RewardedAd ad) {
                          AdmobAdUnit.rewardedAd = ad;
                          admobAdCallBack.onAdLoaded(ad);
                      }
                  });
      }

        return this;
    } // loadAdmobRewardedAd end here ========

    public void showAdmobRewardedAd(boolean isAdLoad){
        if (AdmobAdUnit.ADMOB_AD_IS_ON){
            if (AdmobAdUnit.rewardedAd != null){
                AdmobAdUnit.rewardedAd.show(activity, rewardItem -> admobAdCallBack.onUserEarnedReward(rewardItem));

                AdmobAdUnit.rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        admobAdCallBack.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();

                        if (isAdLoad){
                            AdmobAdUnit.rewardedAd = null;
                            loadAdmobRewardedAd();
                        }

                        admobAdCallBack.onAdDismissed();
                        admobAdCallBack.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        admobAdCallBack.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        admobAdCallBack.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        admobAdCallBack.onAdShowedFullScreenContent();
                    }
                });
            } else {
                admobAdCallBack.onAdDismissed();
            }

        } else {
            admobAdCallBack.onAdDismissed();
        }

    } // showAdmobRewardedAd end here ========

    public void loadAdmobNativeAd(TemplateView templateView){
        if (AdmobAdUnit.ADMOB_AD_IS_ON){
            MobileAds.initialize(activity);
            AdLoader adLoader = new AdLoader.Builder(activity, AdmobAdUnit.ADMOB_NATIVE_AD)
                    .forNativeAd(nativeAd -> {
                        templateView.setNativeAd(nativeAd);
                        admobAdCallBack.onNativeAdLoaded(nativeAd);
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        }
    } // loadAdmobNativeAd end here =============


} // AdmobAd end here ==============
