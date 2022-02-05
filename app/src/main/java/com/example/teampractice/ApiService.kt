package com.example.teampractice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/retrofit/get")
    fun getFunc(@Query("data") data: String): Call<ResponseBody>
}