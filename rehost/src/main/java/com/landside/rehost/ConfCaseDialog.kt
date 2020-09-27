package com.landside.rehost

import android.app.AlertDialog.Builder
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.conf_case.btn_add
import kotlinx.android.synthetic.main.conf_case.btn_close
import kotlinx.android.synthetic.main.conf_case.cases

class ConfCaseDialog(
  context: Context
) : Dialog(context, R.style.confBoardDialog) {

  lateinit var adapter: CaseAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle()
    setContentView(R.layout.conf_case)
    btn_close.setOnClickListener {
      dismiss()
    }
    btn_add.setOnClickListener {
      val editText = EditText(context)
      Builder(context).setTitle("新增配置")
          .setView(editText)
          .setPositiveButton("确定") { _, _ ->
            ReHost.cases.add(ConfCase(name = editText.text.toString()))
            adapter.notifyDataSetChanged()
          }
          .create()
          .show()
    }
    adapter = CaseAdapter(context) {
      ConfBoardDialog(context, it).show()
    }
    cases.layoutManager = LinearLayoutManager(context)
    cases.adapter = adapter
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