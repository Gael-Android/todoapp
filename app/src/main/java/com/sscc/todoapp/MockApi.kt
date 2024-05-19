package com.sscc.todoapp

import retrofit2.Call
import retrofit2.http.GET

interface MockApi {
    @GET("users")
    fun getUsers(): Call<List<User>>
}