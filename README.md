<p align="center">
  <a href="https://github.com/AtikulSoftware/SmartAdmob">
    <img src="https://raw.githubusercontent.com/AtikulSoftware/AtikulFiles/main/Smart%20Admob.png" alt="Logo" width="100" height="100">
  </a> 
  <br>
  <a href="https://github.com/AtikulSoftware/SmartAdmob"> Watch Video To Implementation Smart Admob Library </a>
</p>

<h1 align='center'>Smart Admob Ads</h1>

> 1. Add it in your root `settings.gradle` at the end of repositories
```
  maven { url 'https://jitpack.io' }
```
<br>

> Step 2. Add the dependency in `build.gradle`
```
 implementation 'com.github.AtikulSoftware:SmartAdmob:2.0'
 implementation 'com.google.android.gms:play-services-ads:22.6.0'
```

<br>

> 3. Ad Some Permissions inside `AndroidManifest.xml`
```
<!-- Ad And Internet Permission -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="com.google.android.gms.permission.AD_ID"/>

<!-- Admob Meta Data (inside the application tag) -->
<meta-data
  android:name="com.google.android.gms.ads.APPLICATION_ID"
  android:value="ca-app-pub-3940256099942544~3347511713"/>
```

<br>

> 4. How To Set Ad Unit ID ?
```
AdmobAdUnit.ADMOB_BANNER_AD = "ca-app-pub-3940256099942544/9214589741";
AdmobAdUnit.ADMOB_INTERSTITIAL_AD = "ca-app-pub-3940256099942544/1033173712";
AdmobAdUnit.ADMOB_REWARDED_AD = "ca-app-pub-3940256099942544/5224354917";
AdmobAdUnit.ADMOB_NATIVE_AD = "ca-app-pub-3940256099942544/2247696110";
```

## BANNER AD

> How to use Admob Banner Ad ?

`Add AdView to the layout`
```
XML CODE (Layout) -
  <LinearLayout
        android:id="@+id/bannerAd"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_alignParentBottom="true"/>


JAVA CODE -
new AdmobAd(this, new AdmobAdCallBack() {
            @Override
            public void onAdClicked() {
                AdmobAdCallBack.super.onAdClicked();
            }
        }).initializeAdmobAd().loadBanner(findViewById(R.id.bannerAd));
```

<br>

> [!NOTE]
> To further customize the behavior of your ad, you can hook onto a number of events in the ad's lifecycle: loading, opening, closing, and so on. You can listen for these events through the AdListener class.

<details>
<summary>To use an AdListener with `AdView`, call the `setAdListener()` method: ? </summary>
<br>
  
```
  
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
            public void adOpened() {

            }

        }).initializeAdmobAd().loadBanner(findViewById(R.id.bannerAd));
```
</details>

<br>

## INTERSTITIAL AD

> How to use Interstitial Ad ?
```
  new AdmobAd(this, new AdmobAdCallBack() {

  }).loadAdmobInterstitialAd().showAdmobInterstitial(true);
```

> [!NOTE]
> The FullScreenContentCallback handles events related to displaying your InterstitialAd. Before showing InterstitialAd, make sure to set the callback:

<details>
<summary>Set the `FullScreenContentCallback` ?</summary>
<br>
  
```
    new AdmobAd(this, new AdmobAdCallBack() {
        @Override
        public void onAdClicked() {
            
        }

        @Override
        public void onAdFailedToLoad(LoadAdError loadAdError) {
            
        }

        @Override
        public void onAdFailedToShowFullScreenContent(AdError adError) {
            
        }

        @Override
        public void onAdImpression() {
            
        }

        @Override
        public void onAdShowedFullScreenContent() {
            
        }

        @Override
        public void onAdDismissed() {
            // go to another activity
        }
    }).loadAdmobInterstitialAd().showAdmobInterstitial(true);
```
</details>

<br>

## REWARDED AD

> How to use Rewarded Ad ? 
```
  new AdmobAd(this, new AdmobAdCallBack() {
  
  }).loadAdmobRewardedAd().showAdmobRewardedAd(true);
```

> [!NOTE]
> The FullScreenContentCallback handles events related to displaying your RewardedAd. Before you show your RewardedAd, make sure to set the callback like so:

<details>
<summary>Set the FullScreenContentCallback ? </summary>
<br>

```
    new AdmobAd(this, new AdmobAdCallBack() {
    @Override
    public void onAdDismissed() {

    }

    @Override
    public void onAdClicked() {

    }

    @Override
    public void onAdDismissedFullScreenContent() {

    }

    @Override
    public void onAdFailedToShowFullScreenContent(AdError adError) {

    }

    @Override
    public void onAdImpression() {

    }

    @Override
    public void onAdShowedFullScreenContent() {

    }

    @Override
    public void onAdFailedToLoad(LoadAdError loadAdError) {

    }

    @Override
    public void onUserEarnedReward(RewardItem rewardItem) {

    }

}).loadAdmobRewardedAd().showAdmobRewardedAd(true);
```
</details>

<br>

## NATIVE AD

> How to use Native Ad ? `Native Templates`
```
XML CODE - 
    <com.google.android.ads.nativetemplates.TemplateView
        android:layout_below="@id/RewordedAd"
        android:visibility="gone"
        android:id="@+id/my_template"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:gnt_template_type="@layout/gnt_small_template_view" />


JAVA CODE-
 TemplateView templateView = findViewById(R.id.my_template);
        new AdmobAd(this, new AdmobAdCallBack() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                AdmobAdCallBack.super.onNativeAdLoaded(nativeAd);
                templateView.setVisibility(View.VISIBLE);
            }
        }).initializeAdmobAd().loadAdmobNativeAd(templateView);
```

## GDPR MESSAGE

```
 GDPR gdpr = new GDPR(this);
 gdpr.setGDPR();
```

## AD ON / OFF

```
 AdmobAdUnit.ADMOB_AD_IS_ON = false;
```

## License

Distributed under the MIT License. See [LICENSE]([https://github.com/AtikulSoftware/smart-slider/blob/main/LICENSE.md](https://github.com/AtikulSoftware/SmartAdmob/blob/main/LICENSE)) for more information.

## Authors

* **Md Atikul Islam** - *Android Software Developer* - [Md Atikul Islam](https://github.com/AtikulSoftware) - *Easy & Quick Solution*

<h1 align="center">Thank You ❤️</h1>
