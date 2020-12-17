package com.example.fanfun.ui.login

class LoginPresenter(activity: LoginActivity): LoginContract.Presenter, LoginContract.InteractorOutput {
    var mView: LoginContract.View = activity
    var mRouter: LoginContract.Router = LoginRouter(activity)
    var mInteractor: LoginContract.Interactor = LoginInteractor(this)


    override fun onLogin() {
        mRouter.toHome()
    }

    override fun validateLogin(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            mInteractor.doLogin(email,password)
        }else{
            mView.onLoginError()
        }
    }

    override fun toWebLink() {
        mRouter.toWebLink()
    }

    override fun onError() {
        mView.onLoginError()
    }

    override fun onLoginSuccess() {
        onLogin()
    }

}