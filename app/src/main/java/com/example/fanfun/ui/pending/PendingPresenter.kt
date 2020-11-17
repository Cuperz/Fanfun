package com.example.fanfun.ui.pending

class PendingPresenter(fragment: PendingFragment): PendingContract.Presenter, PendingContract.InteractorOutput {
    var mView: PendingContract.View = fragment
    var mRouter: PendingContract.Router = PendingRouter(fragment)
    var mInteractor: PendingContract.Interactor = PendingInteractor(this)

    override fun toRecord() {
        mRouter.toRecord()
    }
}