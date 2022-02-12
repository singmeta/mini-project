package com.example.teampractice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/audio/:trackID")
    fun getFunc(@Query("/:trackID") data: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/audio")
    fun postFunc(@Field("/") data: String): Call<ResponseBody>

    @DELETE("/audio/:trackID")
    fun deleteFunc(@Path("/:trackID") id: String): Call<ResponseBody>

}