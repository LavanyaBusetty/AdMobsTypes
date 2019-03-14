package com.example.lenovo.admobs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_rewarded.*

/**
 * Created by Lavanya on 3/5/2019.
 * Copyright Indyzen Inc, @2019
 * https://developers.google.com/admob/android/rewarded-video
 * https://www.youtube.com/watch?v=_frcK8lYnX8
 */
//Rewarded video ads are full-screen video ads that users have the option of watching in full in exchange for in-app rewards.
class RewardedVideoAdsActivity : AppCompatActivity(), RewardedVideoAdListener,View.OnClickListener {

    private lateinit var rewardedVideoAd: RewardedVideoAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarded)
        //initializing with appId use original id while publishing in play store
        MobileAds.initialize(applicationContext, "ca-app-pub-3940256099942544~3347511713")
        //A RewardedVideoAd object can be retrieved
        //Use an activity context to get the rewarded video instance
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        listeners()
        //Load ad in onCreate to allow videos to be preloaded.
        loadRewardedVideoAd()
    }

    private fun listeners() {
        //This listener is automatically notified of events from all the networks you're mediating
        rewardedVideoAd.rewardedVideoAdListener = this
        btnLoadRewardedVideo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnLoadRewardedVideo->{
                //If ad is ready play the ad else display hte message
                if (rewardedVideoAd.isLoaded){
                    rewardedVideoAd.show()
                }
                else toast("Ad is not loaded yet")
            }
        }
    }
    override fun onRewardedVideoAdClosed() {
        //loading ad when ad is closed so tht next time it comes without delay as it is already loaded
        loadRewardedVideoAd()
    }
    //Loading ad
    private fun loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().addTestDevice("59E4EBEA446C89A7DB9E4B23F8FB1F04").build())
    }

    override fun onRewardedVideoAdLeftApplication() {
        toast("onRewardedVideoAdLeftApplication")
    }

    override fun onRewardedVideoAdLoaded() {
        //triggers when the video is loaded
        toast("onRewardedVideoAdLoaded")
    }

    override fun onRewardedVideoAdOpened() {
        //triggers when the video is opened
        toast("onRewardedVideoAdOpened")
    }

    override fun onRewardedVideoCompleted() {
        //triggers when video completes
        toast("onRewardedVideoCompleted")
    }

    //Notifies reward data to user for watching a video through the onRewarded()
    override fun onRewarded(reward: RewardItem) {
        tvRewarded.text =getString(R.string.rewarded_coins)
    }

    override fun onRewardedVideoStarted() {
        toast("onRewardedVideoStarted")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        toast("onRewardedVideoAdFailedToLoad")
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // forwarding the parent Activity's lifecycle events to the RewardedVideoAd object so that it follows the same
    override fun onPause() {
        rewardedVideoAd.pause(this)
        super.onPause()
    }

    override fun onResume() {
        rewardedVideoAd.resume(this)
        super.onResume()
    }

    override fun onDestroy() {
        rewardedVideoAd.destroy(this)
        super.onDestroy()
    }
}