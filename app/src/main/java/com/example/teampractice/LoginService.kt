package com.example.teampractice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
        @FormUrlEncoded
        @POST("/member/login")
        fun loginFun(@Field("id" ) id: String, @Field("pw" ) pw: String): Call<ResponseBody>

}