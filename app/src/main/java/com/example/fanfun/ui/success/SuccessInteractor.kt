package com.example.fanfun.ui.success

import com.example.fanfun.model.Request
import com.example.fanfun.utils.deleteUserAll
import com.example.fanfun.utils.requestExist
import java.io.File
import java.lang.Exception

class SuccessInteractor(var intOut: SuccessContract.InteractorOutput): SuccessContract.Interactor {

    override fun deleteVideos(request: Request, videoFile: String) {
        if (requestExist(request.id)){
            deleteUserAll(request.id)
            try {
                File(videoFile).delete()
            }catch (e:Exception){

            }
        }else{
            File(videoFile).delete()
        }
    }
}