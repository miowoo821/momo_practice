package com.leander.momo_practice.house_info.detail.repository

import android.content.Context
import com.leander.momo_practice.global.network.Api
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.global.repository.BaseRepository
import com.leander.momo_practice.global.utilities.GlobalFunction

/* Created on 2022/9/23 */

class DetailRepository(
    private val api: Api
) : BaseRepository() {
    /**取得遠端動物資料*/
    suspend fun getAnimalInfo(): Resource<AnimalInfoResponse> {
        return safeApiCall {
            api.getAnimalInfo()
        }
    }

    /**取得本地端假動物資料*/
    fun getFakeHouseInfo(context: Context?): List<AnimalInfoResponse.Result.Result> {
        return GlobalFunction.getResponseFromJsonStringAsset<AnimalInfoResponse>(
            context,
            "animal_info.json"
        ).result.results
    }
}