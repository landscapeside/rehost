package com.landside.rehost

data class ConfCase(
  val name: String = "",
  var urls: MutableList<String> = (0 until 10).map { "" }.toMutableList()
)