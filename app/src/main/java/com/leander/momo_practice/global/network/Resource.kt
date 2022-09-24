package com.leander.momo_practice.global.network

import okhttp3.ResponseBody


/* Created on 2022/9/23 */

sealed class Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}