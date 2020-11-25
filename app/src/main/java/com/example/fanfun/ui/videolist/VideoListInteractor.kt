package com.example.fanfun.ui.videolist


import com.example.fanfun.utils.*
import com.orhanobut.hawk.Hawk
import java.util.ArrayList

class VideoListInteractor(val intOut: VideoListContract.InteractorOutput): VideoListContract.Interactor {

    override fun getVideos(userId: String): ArrayList<String> {
        return getUserVideos(userId)!!
    }

    override fun deleteVideo(userId: String, path: String, position: Int) {
        deleteUserVideo(userId,path)
        videoDeleted(userId, position)
    }

    private fun videoDeleted(userId: String, position: Int){
        if (userExist(userId)){
            val update:ArrayList<User> = Hawk.get(HAWK_USERS)
            intOut.videoDeleted(update[0].userVideos, position)
        }else{
            intOut.userDeleted()
        }

    }
}