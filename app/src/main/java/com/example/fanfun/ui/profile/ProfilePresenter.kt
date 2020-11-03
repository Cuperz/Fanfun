package com.example.fanfun.ui.profile

class ProfilePresenter(activity: ProfileActivity):ProfileContract.Presenter, ProfileContract.InteractorOutput {
    var mView: ProfileContract.View = activity
    var mRouter: ProfileContract.Router = ProfileRouter(activity)
    var mInteractor: ProfileContract.Interactor = ProfileInteractor(this)

}