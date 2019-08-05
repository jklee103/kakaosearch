package com.example.gsontest

import android.os.AsyncTask
import android.util.Log
import okhttp3.HttpUrl
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

class ImgAsync: AsyncTask<String, String, String>(){
    override fun doInBackground(vararg params: String): String {
        var str ="0"
        var req ="apple"
        req = params[1]
        lateinit var ak:String
        lateinit var jsonobj:JSONObject
        try {
            val text = URLEncoder.encode(params[0], "UTF-8")
            val apiURL = "https://dapi.kakao.com/v2/search/image"
            var urlbuilder:HttpUrl.Builder=HttpUrl.parse(apiURL)!!.newBuilder()
            urlbuilder.addQueryParameter("query", req)
            urlbuilder.addQueryParameter("size", 10.toString())


            //val url = URL(apiURL)
            val url = URL(urlbuilder.build().toString())
            Log.d("async",urlbuilder.build().toString())
            val con = url.openConnection() as HttpURLConnection
            //val con = URL(urlbuilder.build()) as HttpURLConnection
            con.setRequestMethod("GET")
            ak= params[0]
            Log.d("async_ak", ak)
            con.setRequestProperty("Authorization", "KakaoAK "+ak);//헤더맞나?

            con.connect()
            str=convertInputStreamToString(con.inputStream)
            jsonobj=JSONObject(str)
        } catch (e: Exception) {
            println(e)
        }

        return jsonobj.toString()

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        Log.d("abcd", result)

    }

    @Throws(IOException::class, UnsupportedEncodingException::class)
    fun convertInputStreamToString(stream: InputStream): String {
        var reader: Reader? = null
        reader = InputStreamReader(stream, "UTF-8")
        val buffer = CharArray(100000)
        reader!!.read(buffer)
        return String(buffer)
    }

}
