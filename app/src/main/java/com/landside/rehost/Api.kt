package com.landside.rehost

import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
  @GET("lbapi/cms/getcmsdata")
  fun getSomeData(
    @Query("pageId") pageId: String = "375"
  ): Observable<SomeDto>
}