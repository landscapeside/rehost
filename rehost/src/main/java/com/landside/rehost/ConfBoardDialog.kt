package com.landside.rehost

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.conf_board.btn_close
import kotlinx.android.synthetic.main.conf_board.btn_save
import kotlinx.android.synthetic.main.conf_board.confs

class ConfBoardDialog(context: Context,val editConfIdx:Int) : Dialog(context, R.style.confBoardDialog) {

  lateinit var adapter: ConfAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle()
    setContentView(R.layout.conf_board)
    btn_close.setOnClickListener {
      dismiss()
    }
    btn_save.setOnClickListener {
      dismiss()
      ReHost.cases[editConfIdx].urls.clear()
      ReHost.cases[editConfIdx].urls.addAll(adapter.urls)
    }
    adapter = ConfAdapter(context)
    ReHost.cases[editConfIdx].urls.forEachIndexed { index, s ->
      adapter.urls[index] = s
    }
    confs.layoutManager = LinearLayoutManager(context)
    confs.adapter = adapter
  }

  private fun setStyle() {
    val window = window
    //设置dialog在屏幕底部
    window!!.setGravity(Gravity.CENTER)
    //设置dialog弹出时的动画效果，从屏幕底部向上弹出
    window.decorView.setPadding(0, 0, 0, 0)
    //获得window窗口的属性
    val lp = window.attributes
    //设置窗口宽度为充满全屏
    lp.width = WindowManager.LayoutParams.MATCH_PARENT
    //设置窗口高度为包裹内容
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    //将设置好的属性set回去
    window.attributes = lp
    setCanceledOnTouchOutside(false)
  }
}