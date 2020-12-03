package com.example.fanfun.ui.sketch

import com.example.fanfun.utils.User
import java.util.ArrayList

class SketchPresenter(fragment: SketchFragment): SketchContract.Presenter, SketchContract.InteractorOutput {
    var mView: SketchContract.View = fragment
    var mRouter: SketchContract.Router = SketchRouter(fragment)
    var mInteractor: SketchContract.Interactor = SketchInteractor(this)

    override fun toVideoList(userId: String) {
        mRouter.toVideoList(userId)
    }

    override fun getVideoAmount(userId: String): Int {
        return mInteractor.getVideoAmount(userId)
    }

    override fun getList(): ArrayList<User> {
        return mInteractor.getList()
    }

    override fun toCamera(userId: String) {
        mRouter.toCamera(userId)
    }
}