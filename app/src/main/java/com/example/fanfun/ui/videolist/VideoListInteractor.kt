package com.example.fanfun.ui.videolist


import com.example.fanfun.network.ProfileResponse
import com.example.fanfun.utils.*
import com.orhanobut.hawk.Hawk
import java.util.ArrayList

class VideoListInteractor(val intOut: VideoListContract.InteractorOutput): VideoListContract.Interactor {

    override fun getVideos(requestId: String): ArrayList<String> {
        return getUserVideos(requestId)!!
    }

    override fun deleteVideo(requestId: String, path: String, position: Int) {
        deleteUserVideo(requestId,path)
        videoDeleted(requestId, position)
    }

    override fun getPhoto(): String? {
        return if (Hawk.contains(HAWK_USER_PROFILE)){
            val profile: ProfileResponse = Hawk.get(HAWK_USER_PROFILE)
            profile.photo
        }else{
            null
        }
    }

    private fun videoDeleted(requestId: String, position: Int){
        if (requestExist(requestId)){
            val update:ArrayList<User> = Hawk.get(HAWK_USERS)
            intOut.videoDeleted(update[0].userVideos, position)
        }else{
            intOut.userDeleted()
        }
    }
}