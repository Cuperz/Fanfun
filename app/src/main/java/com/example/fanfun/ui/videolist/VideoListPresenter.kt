package com.example.fanfun.ui.videolist

import com.example.fanfun.model.Request
import java.util.ArrayList

class VideoListPresenter(activity: VideoListActivity): VideoListContract.Presenter, VideoListContract.InteractorOutput {

    var mView: VideoListContract.View = activity
    var mRouter: VideoListContract.Router = VideoListRouter(activity)
    var mInteractor: VideoListContract.Interactor = VideoListInteractor(this)

    override fun getVideos(requestId: String): ArrayList<String> {
        return mInteractor.getVideos(requestId)
    }

    override fun toWatchVideo(path: String, request: Request) {
        mRouter.toWatchVideo(path, request)
    }

    override fun deleteVideo(requestId: String, path: String,position: Int) {
        mInteractor.deleteVideo(requestId,path, position)
    }

    override fun toHome() {
        mRouter.toHome()
    }

    override fun getPhoto(): String? {
        return mInteractor.getPhoto()
    }

    override fun videoDeleted(userVideos: ArrayList<String>?,position: Int) {
        mView.videoDeleted(userVideos, position)
    }

    override fun userDeleted() {
        mView.userDeleted()
    }
}