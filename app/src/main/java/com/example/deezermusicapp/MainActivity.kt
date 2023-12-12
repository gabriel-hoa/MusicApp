package com.example.deezermusicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.chromium.base.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/search?q=eminem")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData= retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<List<MyData>?> {

            override fun onResponse(call: Call<List<MyData>?>, response: Response<List<MyData>?>) {
                val dataList= response.body()
                val textView= findViewById<TextView>(R.id.helloText)
                textView.text= dataList.toString()
                Log.d("TAG: onResponse", "onResponse:" + response.body())

            }

            override fun onFailure(call: Call<List<MyData>?>, t: Throwable) {
                Log.d("TAG: onFailure", "onFailure:" + t.message)
            }
        })


    }
}

private fun <T> Call<T>.enqueue(callback: Callback<T?>) {

}
