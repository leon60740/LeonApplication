package com.example.leonapplication.webviewdemo

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.lang.ref.WeakReference

class WebViewDemoActivity : AppCompatActivity() {

  @VisibleForTesting
  lateinit var webViewManager: WebViewManager
  lateinit var webViewClient: MyWebViewClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.d("activity onCreate")
    activityRef = WeakReference(this)
    window.setBackgroundDrawable(ColorDrawable(Color.CYAN))
    enableEdgeToEdge()

    webViewManager = WebViewManager()
    val httpClient = provideHttpClient()
    webViewClient = MyWebViewClient(httpClient)

    Looper.getMainLooper().queue.addIdleHandler {
      initWebView()
      false
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun initWebView() {
    val webView = webViewManager.getWebView(this@WebViewDemoActivity)
    webView.settings.javaScriptEnabled = true
    webView.webViewClient = webViewClient
    setContentView(webView)
    webViewManager.loadUrl("https://antfu.me")
    // webViewManager.loadUrl("https://publicobject.com/helloworld.txt")
  }

  override fun onDestroy() {
    super.onDestroy()
    activityRef.clear()
  }
}