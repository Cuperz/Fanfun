package com.example.fanfun.model

import com.google.gson.annotations.SerializedName


class ListVideo(var time: String? = null, var path: String)

class Request(
        val id: String,
        @SerializedName("user_id") val userId: String,
        @SerializedName("famous_id") val famousId: String,
        val message: String,
        @SerializedName("opportunity") val reason: String,
        val url: String,
        val status: String,
        val state: String
)

