package com.example.lenovo.admobs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_ad.*

/**
 * Created by Lavanya on 3/5/2019.
 * Copyright Indyzen Inc, @2019
 */
class AdActivity:AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad)
        listeners()
    }

    private fun listeners() {
        btnBannerAd.setOnClickListener(this)
        btnInterstitialAd.setOnClickListener(this)
        btnRewardedVideoAd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnBannerAd->{
                startActivity(Intent(this,BannerAdsActivity::class.java))
            }
            btnInterstitialAd->{
                startActivity(Intent(this,InterstitialAdsActivity::class.java))
            }
            btnRewardedVideoAd->{
                startActivity(Intent(this,RewardedVideoAdsActivity::class.java))
            }
        }
    }

}