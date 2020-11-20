package com.example.fanfun.utils

import android.util.Log
import com.orhanobut.hawk.Hawk

const val HAWK_USERS = "hawkUsers"

class User(var userId: String? = null, var userName: String? = null, var userReason: String? = null, var userPicture: String? = null, var userVideos: ArrayList<String>? )

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

fun userExist(userId: String): Boolean{
    return if (checkUserList()){
        val userList = getUserList()
        userList.any { it.userId == userId }
    }else {
        false
    }
}

fun deleteUser(userId: String){
    if (checkUserList()){
        val userList = getUserList()
        userList.removeAt(userList.indexOfFirst { it.userId == userId })
        Hawk.put(HAWK_USERS,userList)
    }
}

fun addUserVideo(userId: String, videoPath: String){
    if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.userId == userId }].userVideos?.add(videoPath)
        Hawk.put(HAWK_USERS, userList)
    }
}

fun deleteUserVideo(userId: String, videoPath: String){
    if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.userId == userId }].userVideos?.remove(videoPath)
    }
}

fun getUserVideos(userId: String): ArrayList<String>? {
    return if (checkUserList()){
        val userList = getUserList()
        userList[userList.indexOfFirst { it.userId == userId }].userVideos
    }else {
        ArrayList()
    }
}

