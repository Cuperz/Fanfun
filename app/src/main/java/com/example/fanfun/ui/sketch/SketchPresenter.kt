package com.example.fanfun.ui.sketch

import com.example.fanfun.model.Model
import com.example.fanfun.utils.User
import java.util.ArrayList

class SketchPresenter(fragment: SketchFragment): SketchContract.Presenter, SketchContract.InteractorOutput {
    var mView: SketchContract.View = fragment
    var mRouter: SketchContract.Router = SketchRouter(fragment)
    var mInteractor: SketchContract.Interactor = SketchInteractor(this)

    override fun toVideoList() {
        mRouter.toVideoList()
    }

    override fun getVideoAmount(): Int {
        return mInteractor.getVideoAmount()
    }

    override fun getList(): ArrayList<User> {
        return mInteractor.getList()
    }
}