package com.example.gsontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.example.gsontest.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    val size = ObservableField<String>()
    val datetime = ObservableField<String>()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_back -> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        var binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.activity=this
        val intent = intent
        var doc = intent.getStringExtra("doc_url")
        //wv_detail.loadUrl(doc)
        wv_detail.apply {
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }
            }

            loadUrl(doc)
        }
        Log.d("detail", doc)
//        tv_size.text = "${intent.getStringExtra("width")} X ${intent.getStringExtra("height")}"
//        tv_datetime.text = intent.getStringExtra("datetime").substring(0, 10)
        size.set("${intent.getStringExtra("width")} X ${intent.getStringExtra("height")}")
        datetime.set(intent.getStringExtra("datetime").substring(0, 10))
        bt_back.setOnClickListener(this)
    }

}
