package com.example.gsontest

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import android.R.array
import java.util.*
import java.util.Arrays.asList
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    var items = arrayListOf<Img>()
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener {
            val appkey = resources.getString(R.string.appkey)
            gsonconvert(ImgAsync().execute(appkey, et.text.toString()).get())
            val adap = ItemAdapter(this, items)
            list1.adapter = adap
        }
    }

    fun gsonconvert(jsonobj: String) {
        var gsonobj = Gson()
        //var img: Img = gsonobj.fromJson(jsonobj, Img::class.java)
        // val array = gsonobj.fromJson(jsonobj, Array<Img>::class.java)
        // var gsontype = object : TypeToken<List<Img>>(){}.type
        // val list = gsonobj.fromJson<List<Img>>(jsonobj, gsontype)
        val abcd = gsonobj.fromJson(jsonobj, Response::class.java)
        val list = abcd.documents
        items = list as ArrayList<Img>
    }

}
