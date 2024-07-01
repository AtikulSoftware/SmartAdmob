<p align="center">
  <a href="https://github.com/AtikulSoftware/SmartAdmob">
    <img src="https://raw.githubusercontent.com/AtikulSoftware/AtikulFiles/main/Smart%20Admob.png" alt="Logo" width="100" height="100">
  </a> 
  <br>
  <a href="https://youtu.be/92zC5RXHjk4?si=Er0KGYnWV-n3lilT"> Watch Video To Implementation Smart Admob Library </a>
</p>

<h1 align='center'>Smart Admob Ads</h1>

> 1. Add it in your root `settings.gradle` at the end of repositories
```
  maven { url 'https://jitpack.io' }
```
<br>

> Step 2. Add the dependency in `build.gradle`
```
 implementation 'com.github.AtikulSoftware:SmartAdmob:6.0'
 implementation 'com.google.android.gms:play-services-ads:23.2.0'
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
            public void onAdOpened() {

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

> How to use Native Ad ? `Native Templates` <a href="https://developers.google.com/admob/android/native/templates"> Download Native Templates  </a>
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

<br>

## OPEN AD
> 1. Create a Java Class Name `MyApplication.java` 
```
public class MyApplication extends OpenAdManager {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
```

> 2. Do some changes in `AndroidManifest.xml` inside application tag add name `android:name=".MyApplication"`
```
<application
  ...
  android:name=".MyApplication"
  ...
</application>
```

> 3. Create a Activity name `SplashScreen` 
```
public class SplashScreen extends AppCompatActivity {

    SplashManager splashManager;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // initialize
        tvResult = findViewById(R.id.tvResult);

        // set ad unit id
        AdmobAdUnit.ADMOB_OPEN_AD = "ca-app-pub-3940256099942544/9257395921";

        //If you want you can set splash screen time (Default Time: 5 seconds)
        AdmobConfig.COUNTER_TIME_MILLISECONDS = 5000;

        // for open ad
        splashManager = new SplashManager(this, new SplashOpenAdCallBack() {
            @Override
            public void startYourActivity() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }

            @Override
            public void loadingTimer(long time) {
                runOnUiThread(() -> tvResult.setText(String.valueOf(time)));
            }
        });
        splashManager.initialize();

    } // onCreate bundle end here ==========

} // public class end here =================
```

<table align="center">
    <tr>
        <th>MyApplication.java</th>
        <th>AndroidManifest.xml</th>
       <th>SplashScreen.java</th>
    </tr>
    <tr>
        <td><img width="200"
                src="https://raw.githubusercontent.com/AtikulSoftware/AtikulFiles/main/open%20ad/MyApplication.png"
                alt="MyApplication.java">
        </td>
        <td><img width="200"
                src="https://raw.githubusercontent.com/AtikulSoftware/AtikulFiles/main/open%20ad/AndroidManifest.png"
                alt="AndroidManifest.xml"></td>
       <td><img width="200"
                src="https://raw.githubusercontent.com/AtikulSoftware/AtikulFiles/main/open%20ad/SplashScreen.png"
                alt="SplashScreen.java"></td>
    </tr>
</table>

<br>

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
