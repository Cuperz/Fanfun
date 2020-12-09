package com.example.fanfun.ui.videostream

class VideoStreamPresenter(activity: VideoStreamActivity): VideoStreamContract.Presenter, VideoStreamContract.InteractorOutput {

    val mView: VideoStreamContract.View = activity
    val mRouter: VideoStreamContract.Router = VideoStreamRouter(activity)
    val mInteractor: VideoStreamContract.Interactor = VideoStreamInteractor(this)
}