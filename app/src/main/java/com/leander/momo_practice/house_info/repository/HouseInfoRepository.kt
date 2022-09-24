package com.leander.momo_practice.house_info.repository

import android.content.Context
import com.leander.momo_practice.global.network.Api
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import com.leander.momo_practice.global.repository.BaseRepository
import com.leander.momo_practice.global.utilities.GlobalFunction


/* Created on 2022/9/23 */

class HouseInfoRepository(
    private val api: Api
) : BaseRepository() {

    /**取得遠端場館資料*/
    suspend fun getHouseInfo(): Resource<HouseInfoResponse> {
        return safeApiCall {
            api.getHouseInfo()
        }
    }

    /**取得本地端假資料*/
    fun getFakeHouseInfo(context: Context?): List<HouseInfoResponse.Result.Result> {
        return GlobalFunction.getResponseFromJsonStringAsset<HouseInfoResponse>(
            context,
            "house_info.json"
        ).result.results
    }
}