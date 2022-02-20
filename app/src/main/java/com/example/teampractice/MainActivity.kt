package com.example.teampractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var retrofit: Retrofit
    private lateinit var loginService: LoginService
    private val TAG = "MainActivitylog"
    private lateinit var btn_login: Button
    private lateinit var edt_id: EditText
    private lateinit var edt_pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstInit()
        btn_login.setOnClickListener (this)

    }

    private fun firstInit() {
        btn_login = findViewById(R.id.btn_login)
        edt_id = findViewById(R.id.edt_id)
        edt_pass = findViewById(R.id.edt_pass)
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.35.247:3006")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        loginService = retrofit.create(LoginService::class.java)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_login -> {
                val id = edt_id.text.toString()
                var pw = edt_pass.text.toString()
                val call_post : Call<ResponseBody> = loginService.loginFun(id, pw)
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
                                var intent = Intent(this@MainActivity, SecondActivity::class.java)
                                startActivity(intent)

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "비밀번호가 일치하지 않습니다.",
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
        }
    }

}