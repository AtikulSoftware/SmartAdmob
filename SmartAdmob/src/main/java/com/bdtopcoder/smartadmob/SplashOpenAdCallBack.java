package com.bdtopcoder.smartadmob;

public interface SplashOpenAdCallBack {
    void loadingTimer(long time);

    void loadingDone(boolean isDone);

    void startYourActivity();
}
