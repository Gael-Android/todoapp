package com.sscc.todoapp

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataList = remember {
                mutableStateListOf<User>()
            }

            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getUserIdAndName(dataList)
                    CustomLazyColumn(dataList)
                }
            }
        }
    }
}


fun getUserIdAndName(dataList: MutableList<User>) {
    val retrofit = RetrofitClient.client
    val service = retrofit.create(MockApi::class.java)
    val users = service.getUsers()

    users.enqueue(object : Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if (response.isSuccessful) {
                val users = response.body()
                users?.forEach {
                    Log.d("MainActivity", "ID: ${it.id}, Name: ${it.name}")
                    dataList.add(it)
                }
            }
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            Log.e("MainActivity", "Error: ${t.message}")
        }
    })
}
