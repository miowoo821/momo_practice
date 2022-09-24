package com.leander.momo_practice.global.network

import com.leander.momo_practice.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/* Created on 2022/9/23 */

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://data.taipei/api/"
    }

    fun <Api> buildApi(
        api : Class<Api>
    ) :Api {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also {client ->
                if (BuildConfig.DEBUG){
                    val log = HttpLoggingInterceptor()
                    log.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(log)
                }


            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}