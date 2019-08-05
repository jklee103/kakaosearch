package com.example.gsontest

import android.content.Context
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Handler

public class ImgThread(): Thread() {
    lateinit var mContext:Context
    lateinit var handler:Handler
    lateinit var service:ItemService

    var version:Int = 1
    lateinit var query:String

    constructor(handler:Handler, mContext:Context, query: String):this() {
        this.mContext = mContext
        this.query = query
        this.handler = handler
    }

    override fun run() {
        super.run()
        var client:Retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com").addConverterFactory(
            GsonConverterFactory.create()).build();
        //val service: ItemService = client.create(ItemService)
        //var call : Call<TestResponse> = service.getlist("83cf3835a94dddb87b6cd9c5839b6022", query)//key옮겨야함
    }
}