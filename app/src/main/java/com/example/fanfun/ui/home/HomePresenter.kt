package com.example.fanfun.ui.home

class HomePresenter(activity: HomeActivity): HomeContract.Presenter, HomeContract.InteractorOutput {
    var mView: HomeContract.View = activity
    var mInteractor: HomeContract.Interactor = HomeInteractor(this)
    var mRouter: HomeContract.Router = HomeRouter(activity)

    override fun toProfile() {
        mRouter.toProfile()
    }

    override fun getPhoto(): String? {
        return mInteractor.getPhoto()
    }
}