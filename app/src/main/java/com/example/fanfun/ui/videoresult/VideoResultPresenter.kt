package com.example.fanfun.ui.videoresult

class VideoResultPresenter(activity: VideoResultActivity): VideoResultContract.Presenter, VideoResultContract.InterawctorOutput {

    var mView: VideoResultContract.View = activity
    var mRouter: VideoResultContract.Router = VideoResultRouter(activity)
    var mInteractor: VideoResultContract.Interactor = VideoResultInteractor(this)

    override fun toCamera() {
        mRouter.toCamera()
    }

    override fun sendVideo(mVideoFile: String?) {
        mInteractor.sendVideo(mVideoFile)

    }

    override fun toHome() {
        mRouter.toHome()
    }

    override fun onVideoSent() {
        mRouter.toSuccess()
    }
}