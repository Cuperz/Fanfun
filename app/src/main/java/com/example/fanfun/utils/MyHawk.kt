package com.example.fanfun.utils

import com.orhanobut.hawk.Hawk
import java.io.File

const val HAWK_USERS = "hawkUsers"
const val HAWK_USER_TOKEN = "accessToken"
const val HAWK_USER_ID = "userAud"
const val HAWK_USER_PROFILE = "userProfile"

class User(var requestId: String? = null,
           var userName: String? = null,
           var userReason: String? = null,
           var userMessage: String? = null,
           var userPicture: String? = null,
           var requestDate: String? = null,
           var userVideos: ArrayList<String>? )

fun checkUserList(): Boolean{
    return Hawk.contains(HAWK_USERS)
}

fun getUserList(): ArrayList<User> {
    return if(checkUserList()){
        return Hawk.get(HAWK_USERS)
    }else{
        ArrayList()
    }

}

fun addUser(newUser: User){
    if (checkUserList()){
        val userList = getUserList()
        userList.add(newUser)
        Hawk.put(HAWK_USERS, userList)
    }else{
        val userList = ArrayList<User>()
        userList.add(newUser)
        Hawk.put(HAWK_USERS, userList)
    }
}

fun requestExist(requestId: String): Boolean{
    return if (checkUserList()){
        val userList = getUserList()
        userList.any { it.requestId == requestId }
    }else {
        false
    }
}

fun deleteUser(requestId: String){
    if (checkUserList()){
        val userList = getUserList()
        userList.removeAt(userList.indexOfFirst { it.requestId == requestId })
        Hawk.put(HAWK_USERS,userList)
    }
}

fun addUserVideo(requestId: String, videoPath: String){
    if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.requestId == requestId }].userVideos?.add(videoPath)
        Hawk.put(HAWK_USERS, userList)
    }
}

fun deleteUserVideo(requestId: String, videoPath: String){
    if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.requestId == requestId }].userVideos?.remove(videoPath)
        File(videoPath).delete()
        if (userList[userList.indexOfFirst { it.requestId == requestId }].userVideos.isNullOrEmpty()){
            deleteUser(requestId)
        }else{
            Hawk.put(HAWK_USERS, userList)
        }
    }
}

fun deleteUserAll(requestId: String){
    if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.requestId == requestId }].userVideos?.forEach {
            File(it).delete()
        }
        deleteUser(requestId)
    }
}

fun getUserVideos(requestId: String): ArrayList<String>? {
    return if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.requestId == requestId }].userVideos
    }else {
        ArrayList()
    }
}

fun deleteHawkData(){
    val userList = getUserList()
    userList.forEach { user ->
        val videos = user.userVideos
        videos?.forEach { path ->
            File(path).delete()
        }
    }
    Hawk.deleteAll()
}

