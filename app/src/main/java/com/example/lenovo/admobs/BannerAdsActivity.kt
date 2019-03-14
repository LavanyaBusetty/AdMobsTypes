package com.example.lenovo.admobs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_banner.*

/**
 * Created by Lavanya on 3/5/2019.
 * Copyright Indyzen Inc, @2019
 * https://developers.google.com/admob/android/banner
 * https://www.youtube.com/watch?v=w7muIkMYE_A
 */
//Banner ads are rectangular image or text ads that occupy a spot within an app's layout.
class BannerAdsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        //Initialize the add
        //For testing use some sample app id instead of creating original appId
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        //build the adRequest and for testing ads use addTestDevice with respective device id which comes after running in logcat searching with tag
        // adRequest which is terms and conditions of AdMob while using it as demo purpose
        val adRequest = AdRequest.Builder().addTestDevice("59E4EBEA446C89A7DB9E4B23F8FB1F04").build()
        //specify adSize and adUnitId before loading ad either programmatically or in xml
        //Loading Banner Ad
        adView.loadAd(adRequest)
    }
}