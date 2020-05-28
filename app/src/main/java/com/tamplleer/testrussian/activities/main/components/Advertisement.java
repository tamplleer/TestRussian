package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.utils.AppRater;

public class Advertisement {
    private AdRequest adRequest;
    private AdView mAdView;
    private Context context;


    public Advertisement(Context context) {
        this.context = context;
        AppRater.app_launched(context);
        MobileAds.initialize(context, "ca-app-pub-8909727970839097~4345378585");
        mAdView = ((Activity) context).findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice("90B58081B8CCAA1A4130F5271615A282")
                .build();

    }

    public void show() {
        mAdView.loadAd(adRequest);
    }
}
