package com.example.fanfun.ui.sketch

class SketchPresenter(fragment: SketchFragment): SketchContract.Presenter, SketchContract.InteractorOutput {
    var mView: SketchContract.View = fragment
    var mRouter: SketchContract.Router = SketchRouter(fragment)
    var mInteractor: SketchContract.Interactor = SketchInteractor(this)
}