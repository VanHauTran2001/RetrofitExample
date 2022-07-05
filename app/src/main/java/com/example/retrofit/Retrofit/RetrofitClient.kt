package com.example.retrofit.Retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var outInstanse : Retrofit?=null
    val instance : Retrofit
        get() {
            if (outInstanse==null){
                outInstanse = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return outInstanse!!
        }
}