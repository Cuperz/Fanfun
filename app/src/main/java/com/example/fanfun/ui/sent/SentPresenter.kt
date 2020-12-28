package com.example.fanfun.ui.sent

import com.example.fanfun.model.Request
import java.util.ArrayList

class SentPresenter(fragment: SentFragment): SentContract.Presenter, SentContract.InteractorOutput {
    var mView: SentContract.View = fragment
    var mRouter: SentContract.Router = SentRouter(fragment)
    var mInteractor: SentContract.Interactor = SentInteractor(this)


    override fun playVideo(url: String) {
        mRouter.playVideo(url)
    }

    override fun getList() {
        mInteractor.getList()
    }

    override fun listResult(sentList: ArrayList<Request>) {
        mView.listResult(sentList)
    }
}