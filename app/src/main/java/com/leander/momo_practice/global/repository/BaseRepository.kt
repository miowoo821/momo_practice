package com.leander.momo_practice.global.repository

import com.leander.momo_practice.global.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/* Created on 2022/9/23 */

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
       apiCall: suspend ()-> T
    ) : Resource<T>{
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable:Throwable){
                when(throwable){
                    /*是否http錯誤*/
                    is HttpException ->{
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else ->{
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}