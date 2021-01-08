package com.example.fanfun.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.ui.success.SuccessActivity
import com.example.fanfun.utils.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception

class VideoWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    private val mAPi: API by lazy { RestClient.instanceAPI }

    override fun doWork(): Result {

        val request = inputData.getString("request")?.toRequest()
        val videoFile = inputData.getString("path")

        val appContext = applicationContext
        try {
            val file = File(videoFile!!)
            val requestBody: RequestBody = file.asRequestBody("*/*".toMediaTypeOrNull())

            val idBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("video",file.name,requestBody)
                .build()

            val uploadRequest = mAPi.uploadVideo(idBody, request!!.id).execute()

            if (uploadRequest.isSuccessful) {
                makeStatusNotification("Video enviado exitosamente", appContext, FROM_SUCCESS)
                deleteVideo(request, videoFile)
            }else{
                makeStatusNotification("Falla al enviar video", appContext, FROM_ERROR)
                saveVideo(request,videoFile)
            }


            return Result.success()
        }catch (e: Exception){
            makeStatusNotification("No se pudo enviar el video", appContext, FROM_ERROR)
            return Result.failure()
        }
    }

    private fun makeStatusNotification(message: String, context: Context, result: Int) {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
    // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = "Fanfun"
            val description = "Fanfun"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("VERBOSE_NOTIFICATION", name, importance)
            channel.description = description

            // Add the channel
            notificationManager?.createNotificationChannel(channel)
        }

        val intent: Intent = if(result == FROM_SUCCESS) {
                Intent(applicationContext, SuccessActivity::class.java).apply {
                    putExtra("from", FROM_SUCCESS)
                }
            }else{
                Intent(applicationContext, SuccessActivity::class.java).apply {
                    putExtra("from", FROM_ERROR)
                }
            }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        // Create the notification
        val builder = NotificationCompat.Builder(context, "VERBOSE_NOTIFICATION")
            .setSmallIcon(R.drawable.ic_logo_fanfun_splash)
            .setContentTitle("Solicitud de video")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(LongArray(0))


        // Show the notification
        notificationManager?.notify(1, builder.build())
    }

    private fun deleteVideo(request: Request, videoFile: String){
        if (requestExist(request.id)){
            deleteUserAll(request.id)
            try {
                File(videoFile).delete()
            }catch (e:Exception){

            }
        }else{
            File(videoFile).delete()
        }
    }

    private fun saveVideo(request: Request?, videoFile: String?) {
        if(!requestExist(request!!.id)) {
            addUser(User(request.id, request.user.name, request.reason, request.message, request.user.picture, request.recibedAt,arrayListOf(videoFile!!)))
        }else{
            addUserVideo(request.id,videoFile!!)
        }
    }

}