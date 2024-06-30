package com.bdtopcoder.smartadmob;

public interface SplashOpenAdCallBack {
    default void loadingTimer(long time){}

    default void loadingDone(boolean isDone){}

    default void startYourActivity(){}
}
