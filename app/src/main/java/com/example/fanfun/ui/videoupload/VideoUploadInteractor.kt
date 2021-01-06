package com.example.fanfun.ui.videoupload

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.fanfun.model.Request
import com.example.fanfun.network.VideoWorker
import com.example.fanfun.utils.*
import java.io.File
import java.lang.Exception

class VideoUploadInteractor(val intOut: VideoUploadContract.InteractorOutput, val activity: VideoUploadActivity): VideoUploadContract.Interactor {

    private val workManager = WorkManager.getInstance(activity)


    override fun uploadVideo(request: Request, videoFile: String?) {

        val videoWorker = OneTimeWorkRequest.Builder(VideoWorker::class.java).addTag("upload")
        val videoData = Data.Builder()
        videoData.putString("request", request.toJson())
        videoData.putString("path", videoFile)
        videoWorker.setInputData(videoData.build())
        workManager.enqueue(videoWorker.build())
    }

    override fun checkUpload(): WorkInfo.State {
        return workManager.getWorkInfosByTag("upload").get()[0].state
    }

    override fun vidoSucceded(request: Request?, videoFile: String?) {
        if (requestExist(request!!.id)){
            deleteUserAll(request.id)
            try {
                File(videoFile!!).delete()
            }catch (e: Exception){

            }
        }else{
            File(videoFile!!).delete()
        }
        intOut.toSuccess(request, videoFile)

    }

    override fun videoFailed(request: Request?, videoFile: String?) {
        if(!requestExist(request!!.id)) {
            addUser(User(request.id, request.user.name, request.reason, request.message, request.user.picture, arrayListOf(videoFile!!)))
        }else{
            addUserVideo(request.id,videoFile!!)
        }
        intOut.toError(request, videoFile)
    }
}