package com.example.teampractice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //http://localhost:3000/audio/:trackID/?data=""

    @GET("/audio/:trackID") //뒤에 추가되는 url 엔드포인트
    fun getFunc(@Query("data") data: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/audio") //음원파일, 가수이름, 노래제목
    fun postFunc(@Field("data") data: String): Call<ResponseBody>

    @DELETE("/audio/:trackID")
    fun deleteFunc(@Path("data") id: String): Call<ResponseBody>
}
