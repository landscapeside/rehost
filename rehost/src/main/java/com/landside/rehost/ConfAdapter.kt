package com.landside.rehost

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_host_conf.view.et_conf
import kotlinx.android.synthetic.main.item_host_conf.view.tv_conf_index

class ConfAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  val inflater: LayoutInflater = LayoutInflater.from(context)

  val urls: MutableList<String> = (0 until 10).map { "" }.toMutableList()

  class ConfViewHolder(val container: View) : RecyclerView.ViewHolder(container)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder =
    ConfViewHolder(inflater.inflate(R.layout.item_host_conf, parent, false))

  override fun getItemCount(): Int = 10

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    (holder as ConfViewHolder).run {
      container.tv_conf_index.text = "$position"
      container.et_conf.tag = position
      container.et_conf.addTextChangedListener(
          onTextChanged = { text, _, _, _ ->
            urls[(container.et_conf.tag as Int)] = text.toString()
          }
      )
      container.et_conf.setText(urls[(container.et_conf.tag as Int)])
    }
  }
}