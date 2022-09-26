package com.example.test


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.zepto.R


class WindowWebActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_web)

        val url = "https://teamtest.co.in/matrimonial/personal_details.php"

        val webview = findViewById<View>(R.id.myWebView) as WebView
        //next line explained below
        //next line explained below
        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(url)
    }
}