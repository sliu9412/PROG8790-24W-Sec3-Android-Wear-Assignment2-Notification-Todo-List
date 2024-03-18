package com.example.siyuliuassignment2.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.example.siyuliuassignment2.R
import kotlin.random.Random


class MyNotificationManager(private val context: Context) {

    init {
        this.createChannel()
    }

    private fun createChannel() {
        val channel = NotificationChannel(
            channelID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

    companion object {
        private const val channelID = "channelID"
        private const val channelName = "channelName"

        fun pushExpirationNotification(context: Context, title: String, content: String) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                "https://www.example.com/due".toUri(),
                context,
                MainActivity::class.java
            )
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)

            val notificationBuilder =
                NotificationCompat.Builder(context, this.channelID)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(title)
                    .setContentText(content)
                    .addAction(R.drawable.notification_icon, "Open", pendingIntent)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent().apply {
                    action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
                startActivity(context, intent, null)
            } else {
                NotificationManagerCompat.from(context)
                    .notify(Random.nextInt(), notificationBuilder.build())
            }
        }
    }
}