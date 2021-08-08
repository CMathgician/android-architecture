package com.example.myapplication.networking.owners

import com.google.gson.annotations.SerializedName

data class OwnerSchema(

    @field:SerializedName("profile_image")
    val profileImage: String? = null,

    @field:SerializedName("account_id")
    val accountId: Int? = null,

    @field:SerializedName("user_type")
    val userType: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("reputation")
    val reputation: Int? = null,

    @field:SerializedName("display_name")
    val displayName: String? = null,

    @field:SerializedName("accept_rate")
    val acceptRate: Int? = null
)
