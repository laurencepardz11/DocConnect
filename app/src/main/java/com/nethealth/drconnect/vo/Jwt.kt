package com.nethealth.drconnect.vo

import com.google.gson.annotations.SerializedName


data class Jwt(
  @SerializedName("access_token")
  val accessToken: String? = "",

  @SerializedName("token_type")
  val tokenType: String? = "",

  @SerializedName("expires_in")
  val expiresIn: Int? = 0,

  @SerializedName("refresh_token")
  val refreshToken: String? = "",

  @SerializedName("as:client_id")
  val clientId: String? = "",

  @SerializedName("username")
  val username: String? = "",

  @SerializedName(".issued")
  val issued: String? = "",

  @SerializedName(".expires")
  val expires: String? = ""
)

data class JwtBody(
  var username: String? = "",
  var password: String? = "",
  var clientId: String? = "",
  var apiKey: String? = "",
  var refreshToken: String? = ""
)