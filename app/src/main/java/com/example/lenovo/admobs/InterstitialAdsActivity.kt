package com.example.lenovo.admobs

import android.content.Intent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.MobileAds

import kotlinx.android.synthetic.main.activity_interstitial.*

/**
 * Created by Lavanya on 3/5/2019.
 * Copyright Indyzen Inc, @2019
 * https://developers.google.com/admob/android/interstitial
 * https://www.youtube.com/watch?v=wuSabL9-KY0
 */

//Normally this will appear while navigating between two screens i.e
// on navigating from one screen to another screen if interstitial ad is ready it will display
//and if we close the ad it displays the screen it was navigated to
class InterstitialAdsActivity : AppCompatActivity(), View.OnClickListener {

    //instance of interstitial ad
    private lateinit var interstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)
        listeners()
        //initializing with appId use original id while publishing in play store
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713")
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        interstitialAd = newInterstitialAd()
        //Loading interstitial add by building it
        loadInterstitial()
    }

    private fun listeners() {
        btnLoadInterstitialAd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnLoadInterstitialAd -> {
                showInterstitial()
            }
        }
    }

    private fun newInterstitialAd(): InterstitialAd {
        return InterstitialAd(this).apply {
            //sample id use real id from adMob to publish in playstore
            adUnitId = getString(R.string.interstitial_ad_unit_id)
            //listener for ad
            adListener = object : AdListener() {

                override fun onAdLoaded() {
                    //do something after ad is loaded
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    Toast.makeText(applicationContext, "Failed to load the ad", Toast.LENGTH_SHORT).show()
                }

                override fun onAdClosed() {
                    //navigate to the destination screen of button click if ad is closed
                    startActivity(Intent(applicationContext, TransitedActivity::class.java))
                    finish()
                    loadInterstitial()
                }
            }
        }
    }

    //checking as interstitial ad will not load directly
    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (interstitialAd.isLoaded) {
            interstitialAd.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
        }
    }

    //Loading Ad remove addTestDevice while publishing in play store
    private fun loadInterstitial() {
        val adRequest = AdRequest.Builder().addTestDevice("59E4EBEA446C89A7DB9E4B23F8FB1F04")
                //Sets the request agent string to identify the ad request's origin,  to denote the platform from which the ad request originated
                .setRequestAgent("android_studio:ad_template")
                .build()
        interstitialAd.loadAd(adRequest)
    }
}
