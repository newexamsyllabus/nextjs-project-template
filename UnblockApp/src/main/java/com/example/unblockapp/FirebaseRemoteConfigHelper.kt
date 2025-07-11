package com.example.unblockapp

import android.content.Context
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class FirebaseRemoteConfigHelper(context: Context) {

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600) // 1 hour cache
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                "vpn_enabled" to false,
                "ads_enabled" to true
            )
        )
        fetchRemoteConfig()
    }

    private fun fetchRemoteConfig() {
        remoteConfig.fetchAndActivate()
    }

    fun isVpnEnabled(): Boolean {
        return remoteConfig.getBoolean("vpn_enabled")
    }

    fun isAdsEnabled(): Boolean {
        return remoteConfig.getBoolean("ads_enabled")
    }
}
