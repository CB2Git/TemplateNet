package com.template.net.http.api

import com.template.net.http.BaseResponse
import com.template.net.http.api.entity.HomeChapterInfo
import com.template.net.http.api.entity.HomeChapterPageInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *======================
 * author: 14790
 * date  : 2022/3/16
 *======================
 **/
interface WanAndroidApi {

    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page:String): Observable<BaseResponse<HomeChapterPageInfo>>
}