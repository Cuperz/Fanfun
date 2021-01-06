package com.example.fanfun.ui.videoupload

import androidx.work.WorkInfo
import com.example.fanfun.model.Request

class VideoUploadPresenter(activity: VideoUploadActivity): VideoUploadContract.Presenter, VideoUploadContract.InteractorOutput {

    val mView: VideoUploadContract.View = activity
    val mRouter: VideoUploadContract.Router = VideoUploadRouter(activity)
    val mInteractor: VideoUploadContract.Interactor = VideoUploadInteractor(this, activity)

    override fun videoUpload(request: Request, videoFile: String?) {
        mInteractor.uploadVideo(request, videoFile)
    }

    override fun checkUpload(): WorkInfo.State {
        return mInteractor.checkUpload()
    }

    override fun videoSucceded(request: Request?, videoFile: String?) {
        mInteractor.vidoSucceded(request, videoFile)
    }

    override fun videoFailed(request: Request?, videoFile: String?) {
        mInteractor.videoFailed(request, videoFile)
    }

    override fun toError(request: Request, videoFile: String) {
        mRouter.toError(request, videoFile)
    }

    override fun toSuccess(request: Request, videoFile: String?) {
        mRouter.toSuccess(request,videoFile!!)
    }
}