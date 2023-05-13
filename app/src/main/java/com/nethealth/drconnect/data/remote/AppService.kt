package com.nethealth.drconnect.data.remote

import com.nethealth.drconnect.vo.Jwt
import com.nethealth.drconnect.vo.JwtBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AppService {

    @POST("JwtToken")
    suspend fun jwtToken(
        @Header("Bearer") value: String,
        @Body body: JwtBody?
    ): Response<Jwt>

}