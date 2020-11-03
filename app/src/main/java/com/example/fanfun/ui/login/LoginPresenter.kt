package com.example.fanfun.ui.login

class LoginPresenter(activity: LoginActivity): LoginContract.Presenter, LoginContract.InteractorOutput {
    var mView: LoginContract.View = activity
    var mRouter: LoginContract.Router = LoginRouter(activity)
    var mInteractor: LoginContract.Interactor = LoginInteractor(this)

}