package com.leander.momo_practice.house_info.view.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import com.leander.momo_practice.house_info.repository.HouseInfoRepository

class InfoListViewModel(
    private val repository: HouseInfoRepository
) : ViewModel() {

    /**取得遠端場館資料*/
    suspend fun getHouseInfo(): Resource<HouseInfoResponse> {
        return repository.getHouseInfo()
    }

    /**取得本地端假場館資料*/
    fun getFakeHouseInfo(context: Context?): List<HouseInfoResponse.Result.Result> {
        return repository.getFakeHouseInfo(context)
    }
}