package com.nethealth.drconnect.repo


import com.nethealth.drconnect.data.remote.AppService
import com.nethealth.drconnect.data.remote.Resource
import com.nethealth.drconnect.vo.JwtBody
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: AppService) {

    fun getJwtToken(body: JwtBody) = flow {
        val TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3RAbmV0LWhlYWx0aC5jb20uYXUiLCJuYW1laWQiOiIwNmIzYjAyMS01Y2Q3LTRkNjAtYWY4Ny05ZWY2ZjI0NzY1NjkiLCJyb2xlIjoiVXNlciIsIm5iZiI6MTU5Njk2MDA3MiwiZXhwIjoxNTk3MDQ2NDcyLCJpYXQiOjE1OTY5NjAwNzIsImlzcyI6IioubmV0LWhlYWx0aC5jb20uYXUiLCJhdWQiOiJuZXQtaGVhbHRoLmNvbS5hdSJ9.S38B5aJ4fmYZr4AcmLalHKPJjkWXvmxExS_Zr1XsnmY"

        emit(Resource.loading())
        val response = api.jwtToken(TOKEN, body)

        if (response.isSuccessful && response.body() != null){
            emit(Resource.Success(response.body()!!))
        }else {
            emit(Resource.Error(response.errorBody()))
        }
    }
}