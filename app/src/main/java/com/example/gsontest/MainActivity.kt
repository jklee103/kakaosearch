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
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.Arrays.asList
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CustomScroll.onLoadMore {
    var items = arrayListOf<Img>()
    var count = 1
    lateinit var adap: ItemAdapter
    lateinit var myscroll: CustomScroll
    lateinit var appkey: String
    var mainHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adap = ItemAdapter(this, items)
        list1.adapter = adap
        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list1.layoutManager = layoutManager
        appkey = resources.getString(R.string.appkey)
        myscroll = CustomScroll(this)
        myscroll.setLoaded()

        list1.addOnScrollListener(myscroll)

        bt.setOnClickListener {
            gsonconvert(ImgAsync().execute(appkey, et.text.toString(), count.toString()).get())

            adap.replaceAll(getNewData())
        }
    }

    fun getNewData(): ArrayList<Img> {//TODO 데이터 클릭이벤트에서 옮기기
        Log.d("getNewData()", "currentPage : $count")
        if (items.size != 0) {
            gsonconvert(ImgAsync().execute(appkey, et.text.toString(), count.toString()).get())
            return items
        } else
            return (arrayListOf<Img>())
    }

    override fun onLoadMore() {
        count++
        adap.addprogress()
        adap.notifyItemInserted(list1.layoutManager!!.itemCount)
        list1.smoothScrollToPosition(list1.layoutManager!!.itemCount)
        mainHandler.postDelayed({
            adap.removeprogress()
            val newdata = getNewData()
            adap.addAll(newdata)
            myscroll.setLoaded()
        }, 2000)
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
