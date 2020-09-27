package com.landside.rehost

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.btn_clean
import kotlinx.android.synthetic.main.activity_main.btn_open_board
import kotlinx.android.synthetic.main.activity_main.btn_test
import kotlinx.android.synthetic.main.activity_main.tv_log
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    handler = object :Handler(){
      override fun handleMessage(msg: Message) {
        tv_log.text = (msg.obj as String)
      }
    }
    btn_open_board.setOnClickListener {
      ReHost.openBoard(this)
    }
    btn_clean.setOnClickListener {
      logBuffer = StringBuffer("")
      tv_log.text = ""
    }
    btn_test.setOnClickListener {
      ReHost.createApi0(Api::class.java)
          .getSomeData()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe()
      ReHost.createApi1(Api::class.java)
          .getSomeData()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe()
      ReHost.createApi2(Api::class.java)
          .getSomeData()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe()
      ReHost.createApi3(Api::class.java)
          .getSomeData()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe()
      ReHost.createApi4(Api::class.java)
          .getSomeData()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe()
    }
  }

  companion object {

    lateinit var handler: Handler

    var logBuffer: StringBuffer = StringBuffer("日志")

    val logger = HttpLoggingInterceptor.Logger {
      appendLog(it)
    }

    fun appendLog(log:String){
      logBuffer.appendln(log)
      handler.sendMessage(handler.obtainMessage(0, logBuffer.toString()))
    }
  }
}
