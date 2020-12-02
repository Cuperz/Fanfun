package com.example.fanfun.ui.pending

import com.example.fanfun.model.Request
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.Result
import com.example.fanfun.network.VideoListResponse
import java.util.ArrayList

class PendingInteractor(val intOut: PendingContract.InteractorOutput): PendingContract.Interactor {

    override fun getPendingList(): ArrayList<Request> {
        val pendingList: ArrayList<Request> = ArrayList()
        NetworkManager.getVideos(object : Result<VideoListResponse>{

            override fun onSuccess(response: VideoListResponse) {
                response.data?.forEach {
                    if (it.state == "pending"){
                        pendingList.add(it)
                    }
                }
            }

            override fun onError(code: Int, message: String) {
            }

            override fun onFailure(message: String) {
            }
        })
        return pendingList
    }

}