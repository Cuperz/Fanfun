package com.example.fanfun.ui.sketch

import com.example.fanfun.model.Request
import com.example.fanfun.utils.User
import java.util.ArrayList

class SketchPresenter(fragment: SketchFragment): SketchContract.Presenter, SketchContract.InteractorOutput {
    var mView: SketchContract.View = fragment
    var mRouter: SketchContract.Router = SketchRouter(fragment)
    var mInteractor: SketchContract.Interactor = SketchInteractor(this)

    override fun toVideoList(request: Request) {
        mRouter.toVideoList(request)
    }

    override fun getVideoAmount(requestId: String): Int {
        return mInteractor.getVideoAmount(requestId)
    }

    override fun getList(): ArrayList<User> {
        return mInteractor.getList()
    }

    override fun toCamera(request: Request) {
        mRouter.toCamera(request)
    }
}