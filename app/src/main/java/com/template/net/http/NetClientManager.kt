package com.template.net.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络请求的管理者
 *======================
 * author: chenbing
 * date  : 2022/3/16
 *======================
 **/
class NetClientManager {

    companion object {
        fun <T> instance(api: Class<T>): T {
            return RetrofitManager.instance.create(api)
        }
    }
}


private class RetrofitManager {
    companion object {

        const val BASE_URL:String = "https://www.wanandroid.com/"

        //使用懒加载保证单例模式安全
        val instance: Retrofit by lazy {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(OkhttpManager.instance)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }
}


private class OkhttpManager {

    companion object {
        //使用类加载机制保证单例模式安全
        val instance: OkHttpClient = Holder.holder
    }

    private object Holder {
        val holder = OkHttpClient()
            .newBuilder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
}