package com.leander.momo_practice.global.network

import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import retrofit2.http.GET


/* Created on 2022/9/23 */

interface Api {

    /**取得遠端場館資料*/
    @GET("v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getHouseInfo(): HouseInfoResponse

    /**取得遠端動物資料*/
    @GET("v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalInfo(): AnimalInfoResponse
}