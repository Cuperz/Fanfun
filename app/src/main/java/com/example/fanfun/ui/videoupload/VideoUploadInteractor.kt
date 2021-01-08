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
import java.util.*

class VideoUploadInteractor(val intOut: VideoUploadContract.InteractorOutput, val activity: VideoUploadActivity): VideoUploadContract.Interactor {

    private val workManager = WorkManager.getInstance(activity)
    private var uuid: UUID? = null


    override fun uploadVideo(request: Request, videoFile: String?) {

        val videoData = Data.Builder()
        videoData.putString("request", request.toJson())
        videoData.putString("path", videoFile)

        val videoWorker = OneTimeWorkRequest.Builder(VideoWorker::class.java).addTag("upload").setInputData(videoData.build()).build()
        uuid = videoWorker.id
        workManager.enqueue(videoWorker)

    }

    override fun checkUpload(): WorkInfo.State {
        return workManager.getWorkInfoById(uuid!!).get().state
    }

}