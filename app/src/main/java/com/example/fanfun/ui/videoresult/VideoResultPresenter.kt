package com.example.fanfun.ui.videoresult

class VideoResultPresenter(activity: VideoResultActivity): VideoResultContract.Presenter, VideoResultContract.InterawctorOutput {

    var mView: VideoResultContract.View = activity
    var mRouter: VideoResultContract.Router = VideoResultRouter(activity)
    var mInteractor: VideoResultContract.Interactor = VideoResultInteractor(this)
}