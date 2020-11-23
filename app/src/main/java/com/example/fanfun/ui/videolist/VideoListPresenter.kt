package com.example.fanfun.ui.videolist

import java.util.ArrayList

class VideoListPresenter(activity: VideoListActivity): VideoListContract.Presenter, VideoListContract.InteractorOutput {

    var mView: VideoListContract.View = activity
    var mRouter: VideoListContract.Router = VideoListRouter(activity)
    var mInteractor: VideoListContract.Interactor = VideoListInteractor(this)

    override fun getVideos(userId: String): ArrayList<String> {
        return mInteractor.getVideos(userId)
    }

    override fun toWatchVideo(path: String) {
        mRouter.toWatchVideo(path)
    }

    override fun deleteVideo(userId: String, path: String) {
        mInteractor.deleteVideo(userId,path)
    }

    override fun videoDeleted(userVideos: ArrayList<String>?) {
        mView.videoDeleted(userVideos)
    }

    override fun userDeleted() {
        mRouter.toHome()
    }
}