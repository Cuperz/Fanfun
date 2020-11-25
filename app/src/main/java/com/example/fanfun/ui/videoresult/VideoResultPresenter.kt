package com.example.fanfun.ui.videoresult

import com.example.fanfun.utils.FROM_CAMERA
import com.example.fanfun.utils.FROM_SKETCH

class VideoResultPresenter(activity: VideoResultActivity): VideoResultContract.Presenter, VideoResultContract.InteractorOutput {

    var mView: VideoResultContract.View = activity
    var mRouter: VideoResultContract.Router = VideoResultRouter(activity)
    var mInteractor: VideoResultContract.Interactor = VideoResultInteractor(this)

    override fun toCamera() {
        mRouter.toCamera()
    }

    override fun sendVideo(videoFile: String) {
        mInteractor.sendVideo(videoFile)

    }

    override fun toHome() {
        mRouter.toHome()
    }

    override fun deleteVideo(userId: String, videoFrom: Int, videoFile: String?) {
        mInteractor.deleteVideo(userId,videoFrom,videoFile)
    }

    override fun onVideoSent() {
        mRouter.toSuccess()
    }

    override fun videoDeleted(videoFrom: Int, userId: String) {
        when(videoFrom) {
            FROM_CAMERA -> mRouter.toCamera()
            FROM_SKETCH -> mRouter.toVideoList(userId)
        }
    }
}