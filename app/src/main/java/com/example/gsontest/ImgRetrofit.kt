package com.example.gsontest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Header

class ImgRetrofit(ak: String, query: String, count: String) {
    var retrofit: Retrofit = Builder()
        .baseUrl("https://dapi.kakao.com")
        .client(requestHeader)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(ItemService::class.java)

    var response = service.getlist(ak, query, count, 10.toString())

    private val requestHeader: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(interceptor).build()
        }

}