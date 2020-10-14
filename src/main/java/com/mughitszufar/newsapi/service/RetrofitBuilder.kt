package com.mughitszufar.newsapi.service

import com.mughitszufar.newsapi.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit


object RetrofitBuilder{
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadlines::class.java)
}


interface TopHeadlines{
    @Headers("Authorization:c05668bea1b440bcbc65d9ed8466dfbf")
    @GET("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}