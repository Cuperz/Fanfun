package com.example.fanfun.utils

import java.text.SimpleDateFormat
import java.util.*


fun getPendingDate(rawDate: String): String{
    val today = Calendar.getInstance().time


    val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.'SSSSSS", Locale.US)
    val todayString= formatter.format(today)
    val requestDate: Date? = formatter.parse(rawDate)
    val todayDate: Date? = formatter.parse(todayString)

    val diff = todayDate!!.time - requestDate!!.time
    val seconds: Long = diff / 1000
    val minutes = seconds / 60
    val hours = (minutes / 60) +15
    val days = (hours / 24).toInt()

    return if (days > 0){
        if (days == 1){
            "Recibido hace 1 día"
        }else {
            "Recibido hace $days días"
        }
    }else{
        "Recibido recientemente"
    }

}

fun getSentDate(rawDate: String): String{
    val today = Calendar.getInstance().time

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.'SSSSSS", Locale.getDefault())
    val todayString= formatter.format(today)
    val requestDate: Date? = formatter.parse(rawDate)
    val todayDate: Date? = formatter.parse(todayString)

    val diff = todayDate!!.time - requestDate!!.time
    val seconds: Long = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60 +15
    val days = (hours / 24).toInt()

    return if (days > 0){
        if (days == 1){
            "Enviado hace 1 día"
        }else {
            "Enviado hace $days días"
        }
    }else{
        "Enviado recientemente"
    }
}