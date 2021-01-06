package com.example.fanfun.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.utils.deleteUserAll
import com.example.fanfun.utils.requestExist
import com.example.fanfun.utils.toRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

            mAPi.uploadVideo(idBody, request!!.id).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if (response.isSuccessful){
                        makeStatusNotification("Video enviado exitosamente", appContext)
                        deleteVideo(request, videoFile)
                    }else{
                        makeStatusNotification("Error al enviar video", appContext)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    makeStatusNotification("Falla al enviar video", appContext)
                }
            })
            return Result.success()
        }catch (e: Exception){
            makeStatusNotification("Error en worker", appContext)
            return Result.failure()
        }
    }

    fun makeStatusNotification(message: String, context: Context) {

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
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, "VERBOSE_NOTIFICATION")
        .setSmallIcon(R.drawable.ic_logo_fanfun_splash)
        .setContentTitle("Solicitud de video")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(1, builder.build())
    }

    fun deleteVideo(request: Request, videoFile: String){
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

}