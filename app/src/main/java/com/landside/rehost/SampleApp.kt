package com.landside.rehost

import android.app.Application
import com.google.gson.Gson
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.reset()
        RxJavaPlugins.setErrorHandler {
            MainActivity.appendLog(it.localizedMessage)
        }
        ReHost.init(
            this,
            BuildConfig.DEBUG,
            getRetrofitBuilder()
        )
    }

    private fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(genOkClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
    }

    private fun genOkClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        builder.hostnameVerifier { hostname, session -> true }
        val loggingInterceptor =
            HttpLoggingInterceptor(
                MainActivity.logger
            )
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }
}