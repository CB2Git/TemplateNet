package com.template.net.http

/**
 * 响应的基础包装
 *======================
 * author: 14790
 * date  : 2022/3/16
 *======================
 **/
open class BaseResponse<T>(val data: T?, val errorCode: Int, val errorMsg: String) {

    /**
     * 这个地方允许使用不同的实现判断是否请求成功
     */
    open fun isSuccess(): Boolean {
        return errorCode == 0 && data != null
    }
}