package com.example.unblockapp

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdMobHelper(private val context: Context) {

    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    fun initialize() {
        MobileAds.initialize(context)
    }

    fun loadBannerAd(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    fun loadInterstitialAd(adUnitId: String) {
        interstitialAd = InterstitialAd(context)
        interstitialAd?.adUnitId = adUnitId
        interstitialAd?.loadAd(AdRequest.Builder().build())
    }

    fun showInterstitialAd(activity: Activity, onAdClosed: () -> Unit) {
        if (interstitialAd != null && interstitialAd!!.isLoaded) {
            interstitialAd?.adListener = object : AdListener() {
                override fun onAdClosed() {
                    onAdClosed()
                    loadInterstitialAd(interstitialAd!!.adUnitId)
                }
            }
            interstitialAd?.show()
        } else {
            onAdClosed()
        }
    }

    fun loadRewardedAd(adUnitId: String) {
        RewardedAd.load(context, adUnitId, AdRequest.Builder().build(), object : RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                rewardedAd = null
            }
        })
    }

    fun showRewardedAd(activity: Activity, onUserEarnedReward: () -> Unit, onAdClosed: () -> Unit) {
        if (rewardedAd != null) {
            rewardedAd?.show(activity) {
                onUserEarnedReward()
            }
            rewardedAd?.fullScreenContentCallback = object : com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    onAdClosed()
                    rewardedAd = null
                    loadRewardedAd(rewardedAd?.adUnitId ?: "")
                }
            }
        } else {
            onAdClosed()
        }
    }
}
