package com.example.fanfun.model

import com.google.gson.annotations.SerializedName

class Request(
        val id: String,
        @SerializedName("user_id") val userId: String? = null,
        @SerializedName("famous_id") val famousId: String? = null,
        val message: String? = null,
        @SerializedName("opportunity") val reason: String? = null,
        val url: String? = null,
        val status: String? = null,
        val state: String? = null,
        val user: UserInfo,
        @SerializedName("created_at") val recibedAt: String? = null,
        @SerializedName("updated_at") val sendAt: String? = null
)

class UserInfo(
        val name: String,
        val lastname: String? = null,
        val picture: String? = null
)

