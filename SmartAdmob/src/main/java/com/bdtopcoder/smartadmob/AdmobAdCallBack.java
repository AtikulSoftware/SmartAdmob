package com.bdtopcoder.smartadmob;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;

public interface AdmobAdCallBack {
    default void onAdClicked() {}

    default void onAdClosed() {}

    default void onAdFailedToLoad(LoadAdError loadAdError) {}

    default void onAdImpression() {}

    default void onAdLoaded() {}

    default void onAdOpened() {}

    // Interstitial Ad
    default void onAdLoaded(InterstitialAd interstitialAd){}
    default void onAdDismissedFullScreenContent(){}
    default void onAdFailedToShowFullScreenContent(AdError adError){}
    default void onAdShowedFullScreenContent(){}
    default void onAdDismissed(){}

    // Rewarded ad
    default void onUserEarnedReward(RewardItem rewardItem){}
    default void onAdLoaded(RewardedAd rewardedAd){}

    // Native Ad
    default void onNativeAdLoaded(NativeAd nativeAd){}

} // AdmobAdCallBack end here ============
