package com.example.fanfun.ui.sent

import com.example.fanfun.model.Request
import com.example.fanfun.network.NetworkManager
import com.example.fanfun.network.Result
import com.example.fanfun.network.VideoListResponse
import java.util.ArrayList

class SentInteractor(val intOut: SentContract.InteractorOutput): SentContract.Interactor {

    override fun getSentList(): ArrayList<Request> {

            val sentList: ArrayList<Request> = ArrayList()
            NetworkManager.getVideos(object : Result<VideoListResponse> {

                override fun onSuccess(response: VideoListResponse) {
                    response.data?.forEach {
                        if (it.state == "sent"){
                            sentList.add(it)
                        }
                    }
                }

                override fun onError(code: Int, message: String) {
                }

                override fun onFailure(message: String) {
                }
            })
        return sentList
    }
}
