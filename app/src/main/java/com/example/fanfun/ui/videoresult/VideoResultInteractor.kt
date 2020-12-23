package com.example.fanfun.ui.videoresult

import com.example.fanfun.network.API
import com.example.fanfun.network.BaseResponse
import com.example.fanfun.network.RestClient
import com.example.fanfun.utils.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class VideoResultInteractor(var intOut: VideoResultContract.InteractorOutput): VideoResultContract.Interactor {

    private val mAPi: API by lazy { RestClient.instanceAPI }

    override fun sendVideo(videoFile: String) {

        val file = File(videoFile)
        //val userId: String = Hawk.get(HAWK_USER_ID)

        val requestBody: RequestBody = file.asRequestBody("*/*".toMediaTypeOrNull())

       /*val idBody: RequestBody = MultipartBody.Builder()
               .setType(MultipartBody.FORM)
               .addFormDataPart("user_id","cd39909c-5499-4e1f-96fd-77f8842dd867")
               .addFormDataPart("famous_id","9d18ddcd-cc84-411a-9090-4fee27d045a3")
               .addFormDataPart("opportunity","Greetings!")
               .addFormDataPart("target","Dad")
               .addFormDataPart("message","probado subida de video")
               .addFormDataPart("video",file.name,requestBody)
               .build()*/

        val idBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("video",file.name,requestBody)
                .build()


        mAPi.uploadVideo(idBody, "bf1b0f94-2dab-46fa-8a8c-ac11b3b5cc1c").enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful){
                    intOut.onVideoSent()
                }else{
                    intOut.videoFailed()
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                intOut.videoFailed()
            }
        })

    }

    override fun deleteVideo(userId: String, videoFrom: Int, videoFile: String?) {
        when (videoFrom){
            FROM_CAMERA -> File(videoFile!!).delete()
            FROM_SKETCH -> deleteUserVideo(userId, videoFile!!)
            else -> return
        }
        intOut.videoDeleted(videoFrom,userId)
    }

}