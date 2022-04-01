package com.template.net.http

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer

import io.reactivex.rxjava3.disposables.Disposable

/**
 * 对Consumer的包装
 */
abstract class BaseObserve<T> : Observer<BaseResponse<T>> {

    open fun onSuccess(data: T?) {

    }

    open fun onFail(e: @NonNull Throwable?) {

    }

    override fun onComplete() {}

    final override fun onSubscribe(d: @NonNull Disposable?) {}
    final override fun onNext(tBaseResponse: @NonNull BaseResponse<T>) {
        if (tBaseResponse.isSuccess()) {
            onSuccess(tBaseResponse.data)
        } else {
            onFail(null)
        }
    }

    final override fun onError(e: @NonNull Throwable?) {
        onFail(e)
    }

}