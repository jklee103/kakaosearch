package com.example.gsontest

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

public interface ItemService{
    @GET("/v2/search/image")
    fun getlist(
        @Header("Authorization") Authorization: String,
        @Query("query") query: String,
        @Query("page") count: String,
        @Query("size") size: String
    ): Call<Response?>
}