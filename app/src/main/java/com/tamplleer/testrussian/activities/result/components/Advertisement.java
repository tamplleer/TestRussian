package com.tamplleer.testrussian.activities.result.components;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.SharedPreference;

public class Advertisement {
    private static RewardedVideoAd mAd;
    private Context context;
    private ImageButton mButton;
    private SharedPreference sharedPreference;



    public Advertisement(Context context) {
        this.context = context;
        mButton = ((Activity) context).findViewById(R.id.advertisement_button);
        mButton.setEnabled(false);
        mButton.setVisibility(View.INVISIBLE);
        sharedPreference = new SharedPreference(context);
        MobileAds.initialize(context, "ca-app-pub-8909727970839097/9184677267");
        mAd = MobileAds.getRewardedVideoAdInstance(context);
        setAdvertisementListener();
        mAd.loadAd("ca-app-pub-8909727970839097/9184677267",
                new AdRequest.Builder()
                        .addTestDevice("90B58081B8CCAA1A4130F5271615A282")
                        .build());

    }

    public void show() {
        mAd.show();
    }



    private void setAdvertisementListener() {
        mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                mButton.setEnabled(true);
                mButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onRewardedVideoAdOpened() {
            }

            @Override
            public void onRewardedVideoStarted() {
            }

            @Override
            public void onRewardedVideoAdClosed() {
                mButton.setEnabled(false);
                sharedPreference.saveEndGame(false);
                Toast.makeText(context, "Вы больше не увидите рекламму!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
            }

            @Override
            public void onRewardedVideoCompleted() {
            }
        });
    }

    //todo make new class for adds

}