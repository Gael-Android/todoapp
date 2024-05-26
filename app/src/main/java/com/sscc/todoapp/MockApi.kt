package com.sscc.todoapp

import retrofit2.http.GET

interface MockApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}