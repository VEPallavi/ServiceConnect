package com.Servicehubconnect.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import java.lang.Exception


class WebViewActivity : AppCompatActivity(){
    var screenType: String?= null
    var url: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        try {
            screenType = intent.getStringExtra("screenType")
            url = intent.getStringExtra("url")!!
        }catch (e: Exception){
            e.printStackTrace()
        }

        tv_title.text = screenType
        loadWebview()
        setOnClickListener()
    }

    private fun loadWebview() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url)
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener {
            finish()
        }
    }

}