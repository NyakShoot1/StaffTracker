package com.nyakshoot.stafftrackersimplenavigation.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.nyakshoot.stafftrackersimplenavigation.R
import okhttp3.*
import java.io.FileOutputStream
import java.io.InputStream

// Notification channel ID
private const val CHANNEL_ID = "file_download_channel"

// Notification ID
private const val NOTIFICATION_ID = 1

fun saveFile(
    body: ResponseBody?,
    pathWhereYouWantToSaveFile: String,
    fileName: String,
    documentUrl: String,
    context: Context
): String {

    val contentType = getContentTypeOnly(documentUrl)
    Log.d("penis", contentType)
    var input: InputStream? = null
    try {
        input = body!!.byteStream()
        //val file = File(getCacheDir(), "cacheFileAppeal.srl")
        val fos = FileOutputStream("$pathWhereYouWantToSaveFile/$fileName.$contentType")
        fos.use { output ->
            val buffer = ByteArray(4 * 8096) // or other buffer size
            var read: Int
            while (input.read(buffer).also { read = it } != -1) {
                output.write(buffer, 0, read)
            }
            output.flush()
        }

        showDownloadNotification(context, fileName)

        return pathWhereYouWantToSaveFile
    } catch (e: Exception) {
        Log.e("saveFile", e.toString())
    } finally {
        input?.close()
    }
    return ""
}

fun getContentTypeOnly(documentUrl : String): String {
    val parts = documentUrl.split("/") // Разделить строку по "/"
    val fileName = parts.last() // Получить последний элемент
    return fileName.split(".").last()
}

// Show a notification indicating the file download
private fun showDownloadNotification(context: Context, fileName: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Create a notification channel (required for Android 8.0 and above)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(CHANNEL_ID, "File Download", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    // Create the notification
    val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("File Downloaded")
        .setContentText("The file $fileName has been downloaded.")
        .setSmallIcon(R.drawable.ic_download)

    // Show the notification
    notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
}