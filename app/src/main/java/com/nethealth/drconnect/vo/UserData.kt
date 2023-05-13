package com.nethealth.drconnect.vo

// UserData class for storing user data
data class UserData(
    val access_token:String? = "",
    val token_type:String? = "",
    val expires_in:Long? = 0L,
    val refresh_token:String? = "",
    val client_id:String? = "",
    val username:String? = "",
    val issued:String? = "",
    val expires:String? = ""
)