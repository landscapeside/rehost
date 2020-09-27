package com.landside.rehost

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_conf_case.view.btn_edit
import kotlinx.android.synthetic.main.item_conf_case.view.ck_conf_case
import kotlinx.android.synthetic.main.item_conf_case.view.tv_conf_case

class CaseAdapter(context: Context,val onItemEdit:(Int)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  val inflater: LayoutInflater = LayoutInflater.from(context)

  class CaseViewHolder(val container: View) : RecyclerView.ViewHolder(container)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder =
    CaseViewHolder(inflater.inflate(R.layout.item_conf_case, parent, false))

  override fun getItemCount(): Int = ReHost.cases.size

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    (holder as CaseViewHolder).run {
      container.tv_conf_case.text = ReHost.cases[position].name
      container.btn_edit.setOnClickListener {
        onItemEdit(position)
      }
      container.ck_conf_case.isChecked = position == ReHost.currentCase
      container.ck_conf_case.tag = position
      container.ck_conf_case.setOnClickListener {
        val checkPos = it.tag as Int
        if (checkPos == ReHost.currentCase) {
          (it as CheckBox).isChecked = true
        } else {
          ReHost.currentCase = checkPos
          notifyDataSetChanged()
        }
      }
    }
  }
}