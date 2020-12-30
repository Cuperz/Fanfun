package com.example.fanfun.ui.videoresult

import com.example.fanfun.model.Request
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

    override fun sendVideo(request: Request,videoFile: String) {

        val file = File(videoFile)
        val requestBody: RequestBody = file.asRequestBody("*/*".toMediaTypeOrNull())

        val idBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("video",file.name,requestBody)
                .build()


        mAPi.uploadVideo(idBody, request.id).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful){
                    intOut.onVideoSent(request,videoFile)
                }else{
                    intOut.onVideoError(request, videoFile)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                intOut.onVideoError(request, videoFile)
            }
        })

    }

    override fun deleteVideo(request: Request, videoFrom: Int, videoFile: String?) {
        when (videoFrom){
            FROM_CAMERA -> File(videoFile!!).delete()
            FROM_SKETCH -> deleteUserVideo(request.id, videoFile!!)
            else -> return
        }
        intOut.videoDeleted(videoFrom,request)
    }

}