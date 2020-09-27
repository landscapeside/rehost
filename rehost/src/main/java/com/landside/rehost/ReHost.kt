package com.landside.rehost

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit

object ReHost {

  private var builder: Retrofit.Builder? = null
  internal val cases: MutableList<ConfCase> = mutableListOf()
  internal var currentCase: Int = 0
  private val retrofitCache: MutableMap<String, Retrofit> = mutableMapOf()

  fun init(
    context: Context,
    retrofitBuilder: Retrofit.Builder
  ) {
    builder = retrofitBuilder
    val hostsType = object : TypeToken<List<ConfCase>>() {}.type
    val hosts: List<ConfCase> = JSONS.parseObject(
        FileReader.requestMockString(
            context,
            "hosts.json"
        ),
        hostsType
    ) ?: throw IllegalStateException("hosts.json is lost!")
    if (hosts.isEmpty()) {
      throw IllegalStateException("host configuration is empty")
    }
    hosts.forEach {
      val tempUrls = (0 until 10).map { "" }
          .toMutableList()
      it.urls.forEachIndexed { index, s ->
        tempUrls[index] = s
      }
      it.urls = tempUrls
    }
    cases.clear()
    cases.addAll(hosts)
    if (cases.isNotEmpty()) {
      cases.first()
          .urls.forEach {
        if (it.isNotEmpty()) {
          retrofitCache[it] = retrofitBuilder.baseUrl(it)
              .build()
        }
      }
    }
  }

  fun openBoard(context: Context) {
    if (context !is FragmentActivity) {
      throw IllegalArgumentException("argument must inherit FragmentActivity")
    }
    ConfCaseDialog(context).show()
  }

  fun <T> createApi0(clazz: Class<T>): T {
    return createApi(0, clazz)
  }

  fun <T> createApi1(clazz: Class<T>): T {
    return createApi(1, clazz)
  }

  fun <T> createApi2(clazz: Class<T>): T {
    return createApi(2, clazz)
  }

  fun <T> createApi3(clazz: Class<T>): T {
    return createApi(3, clazz)
  }

  fun <T> createApi4(clazz: Class<T>): T {
    return createApi(4, clazz)
  }

  fun <T> createApi5(clazz: Class<T>): T {
    return createApi(5, clazz)
  }

  fun <T> createApi6(clazz: Class<T>): T {
    return createApi(6, clazz)
  }

  fun <T> createApi7(clazz: Class<T>): T {
    return createApi(7, clazz)
  }

  fun <T> createApi8(clazz: Class<T>): T {
    return createApi(8, clazz)
  }

  fun <T> createApi9(clazz: Class<T>): T {
    return createApi(9, clazz)
  }

  private fun <T> createApi(
    idx: Int,
    clazz: Class<T>
  ): T {
    if (cases[currentCase].urls[idx].isEmpty()) {
      throw IllegalStateException("lack url configuration within [${cases[currentCase].name}]")
    }
    if (cases[currentCase].urls[idx].isNotEmpty() && !retrofitCache.containsKey(
            cases[currentCase].urls[idx]
        )
    ) {
      retrofitCache[cases[currentCase].urls[idx]] =
        builder?.baseUrl(cases[currentCase].urls[idx])?.build()
            ?: throw IllegalStateException("retrofit builder is null,call init in application")
    }
    return retrofitCache[cases[currentCase].urls[idx]]?.create(clazz)
        ?: throw IllegalStateException(
            "can not find retrofit instance with url[${cases[currentCase].urls[idx]}]"
        )
  }
}