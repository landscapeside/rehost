package com.landside.rehost

data class SomeDto(
  val name: String?,
  val age: Int?,
  val avatar: String?,
  val others: List<Other?>?
) {
  data class Other(
    val someField: String?
  )
}