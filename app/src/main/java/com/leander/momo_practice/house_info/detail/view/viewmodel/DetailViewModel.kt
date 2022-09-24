package com.leander.momo_practice.house_info.detail.view.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.house_info.detail.repository.DetailRepository

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {
    /**取得遠端動物資料*/
    suspend fun getAnimalInfo(): Resource<AnimalInfoResponse> {
        return repository.getAnimalInfo()
    }

    /**取得本地端假動物資料*/
    fun getFakeAnimalInfo(context: Context?): List<AnimalInfoResponse.Result.Result> {
        return repository.getFakeHouseInfo(context)
    }
}