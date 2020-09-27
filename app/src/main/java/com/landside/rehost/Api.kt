package com.landside.rehost

import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET

interface Api {
  @GET("api1")
  fun getSomeData(): Observable<ResponseBody>
}