package com.example.testproject.domain.entity

sealed class ResultData<out T> {
    class Success<T>(val data: T) : ResultData<T>()
    class Error(val message: String?) : ResultData<Nothing>()
}