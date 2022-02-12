package com.example.teampractice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivityLog"
    private val URL = "http://192.168.35.171:3000"
    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService
    private lateinit var btn_get: Button

    private lateinit var mWebView: WebView
    private lateinit var mWebSettings: WebSettings


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstInit()
        btn_get.setOnClickListener(this)

        //웹뷰
        mWebView = findViewById(R.id.WebView)
        mWebView!!.webViewClient = WebViewClient()
        mWebSettings = mWebView!!.settings
        mWebSettings!!.javaScriptEnabled = true
        mWebView!!.loadUrl("10.0.2.2:3000")
    }


    private fun firstInit() {
        btn_get = findViewById<View>(R.id.btn_get) as Button
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java);
    }

    override fun onClick(v: View) {
        when (v. id) {
            R.id.btn_get -> {

                val call_get : Call<ResponseBody> = service.getFunc("get data")

                call_get.enqueue(object : Callback<ResponseBody> {
                    //응답 성공시
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(applicationContext, "error = " + response.code().toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    //응답 실패시
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

            R.id.btn_post -> {
                val call_post : Call<ResponseBody> = service.postFunc("post data")
                call_post.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

            R.id.btn_delete -> {
                val call_delete : Call<ResponseBody> = service.deleteFunc("board")
                call_delete.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            else -> {}
        }

    }

}