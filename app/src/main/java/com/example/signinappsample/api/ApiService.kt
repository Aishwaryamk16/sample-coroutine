package com.example.signinappsample.api

import com.example.signinappsample.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}