package com.example.unblockapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.core.app.NotificationCompat
import java.io.IOException

class SmartproxyVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null
    private val channelId = "VPN_SERVICE_CHANNEL"
    private val notificationId = 1

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.i("SmartproxyVpnService", "VPN Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startVpn()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnectVpn()
        Log.i("SmartproxyVpnService", "VPN Service Destroyed")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "VPN Service",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "VPN Service Notification Channel"
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Unblock VPN")
            .setContentText("VPN is connected and protecting your connection")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun startVpn() {
        try {
            val builder = Builder()
            builder.setSession("UnblockApp VPN")
                .addAddress("10.0.0.2", 24)
                .addRoute("0.0.0.0", 0)
                .addDnsServer("8.8.8.8")
                .addDnsServer("8.8.4.4")
                .setConfigureIntent(
                    PendingIntent.getActivity(
                        this, 0,
                        Intent(this, HomeActivity::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                )
                .setBlocking(false)

            vpnInterface = builder.establish()
            
            if (vpnInterface != null) {
                startForeground(notificationId, createNotification())
                Log.i("SmartproxyVpnService", "VPN Started Successfully")
            } else {
                Log.e("SmartproxyVpnService", "Failed to establish VPN interface")
                stopSelf()
            }
        } catch (e: Exception) {
            Log.e("SmartproxyVpnService", "Error starting VPN", e)
            stopSelf()
        }
    }

    private fun disconnectVpn() {
        try {
            vpnInterface?.close()
            vpnInterface = null
            stopForeground(true)
            Log.i("SmartproxyVpnService", "VPN Disconnected")
        } catch (e: IOException) {
            Log.e("SmartproxyVpnService", "Error disconnecting VPN", e)
        }
    }
}
