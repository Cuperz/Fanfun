package com.example.fanfun.ui.videolist

class VideoListPresenter(activity: VideoListActivity): VideoListContract.Presenter, VideoListContract.InteractorOutput {

    var mView: VideoListContract.View = activity
    var mRouter: VideoListContract.Router = VideoListRouter(activity)
    var mInteractor: VideoListContract.Interactor = VideoListInteractor(this)
}