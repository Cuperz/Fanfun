package com.example.fanfun.ui.pending

import com.example.fanfun.model.Request
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.RequestListResponse
import com.example.fanfun.network.Result
import com.example.fanfun.network.VideoListResponse
import com.example.fanfun.utils.requestExist
import java.util.ArrayList

class PendingInteractor(val intOut: PendingContract.InteractorOutput): PendingContract.Interactor {

    override fun getList() {
        val sentList: ArrayList<Request> = ArrayList()
        NetworkManager.getRequestList(object : Result<RequestListResponse> {

            override fun onSuccess(response: RequestListResponse) {
                response.videos?.forEach {
                    if (it.state == "PENDING" && !requestExist(it.id)){
                        it.name = "Nombre de Prueba"
                        sentList.add(it)
                    }
                }
                intOut.listResult(sentList)
            }

            override fun onError(code: Int, message: String) {
                intOut.listResult(sentList)
            }

            override fun onFailure(message: String) {
                intOut.listResult(sentList)
            }
        })
    }

}