package com.sscc.todoapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyScreenViewModel : ViewModel() {
    val dataList = mutableStateListOf<User>()
    private val retrofit = RetrofitClient.client
    private val service: MockApi = retrofit.create(MockApi::class.java)

    init {
        getUserIdAndName()
    }

    fun getUserIdAndName() {
        viewModelScope.launch {
            runCatching {
                service.getUsers()
            }.onSuccess {
                Log.d("Viewmodel", "Success")
                it.forEach {
                    Log.d("Viewmodel", "ID: ${it.id}, Name: ${it.name}")
                    dataList.add(it)
                }
            }.onFailure {
                Log.e("Viewmodel", "Error: ${it.message}")
            }
        }
    }
}