package com.example.fanfun.ui.pending

import com.example.fanfun.model.Request
import java.util.ArrayList

class PendingPresenter(fragment: PendingFragment): PendingContract.Presenter, PendingContract.InteractorOutput {
    var mView: PendingContract.View = fragment
    var mRouter: PendingContract.Router = PendingRouter(fragment)
    var mInteractor: PendingContract.Interactor = PendingInteractor(this)

    override fun toRecord(userId: String) {
        mRouter.toRecord(userId)
    }

    override fun getPendingList(): ArrayList<Request> {
        return mInteractor.getPendingList()
    }
}