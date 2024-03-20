package com.sample.fdelamora.samplearch

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sample.fdelamora.samplearch.common.resources.AppDrawable
import com.sample.fdelamora.samplearch.debug.DebugActivity
import timber.log.Timber
import timber.log.debug

class DebugSampleArchitectureApplication : SampleArchitectureApplication() {

    override fun onCreate() {
        super.onCreate()

        createDebugNotificationChannel()
        showDebugSettingsNotification()
    }

    private fun createDebugNotificationChannel() {
        val channelDebug = NotificationChannel(
            CHANNEL_ID,
            "Debug",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Provides access to debug screen"
            setSound(null, null)
        }

        // Register channel with the system
        val notificationManager: NotificationManagerCompat =
            NotificationManagerCompat.from(applicationContext)

        if (channelDebug !in notificationManager.notificationChannels) {
            notificationManager.createNotificationChannel(channelDebug).also {
                Timber.debug { "Created Notification Channel: ${channelDebug.name}" }
            }
        }
    }

    private fun showDebugSettingsNotification() {
        val notifyIntent = Intent(this, DebugActivity::class.java)
        val notifyPendingIntent = PendingIntent.getActivity(
            this,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(AppDrawable.ic_debug_notification)
            .setContentTitle("Debug options")
            .setContentText("Open debug screen")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(false)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val CHANNEL_ID = "debug_options_channel"
        const val NOTIFICATION_ID = 9999
    }
}
