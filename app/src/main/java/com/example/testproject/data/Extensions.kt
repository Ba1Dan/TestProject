package com.example.testproject.data.api.response

import com.example.testproject.domain.entity.ResultData
import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.toResult(): Single<ResultData<T>> = this
    .map { ResultData.Success(it) as ResultData<T> }
    .onErrorReturn {
        ResultData.Error(it.message)
    }