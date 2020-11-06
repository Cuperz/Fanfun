package com.example.fanfun.ui.sent

class SentPresenter(fragment: SentFragment): SentContract.Presenter, SentContract.InteractorOutput {
    var mView: SentContract.View = fragment
    var mRouter: SentContract.Router = SentRouter(fragment)
    var mInteractor: SentContract.Interactor = SentInteractor(this)
}