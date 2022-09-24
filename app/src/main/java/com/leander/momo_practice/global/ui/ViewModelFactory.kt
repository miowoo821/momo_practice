package com.leander.momo_practice.global.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leander.momo_practice.global.repository.BaseRepository
import com.leander.momo_practice.house_info.detail.more.repository.AnimalInfoDetailRepository
import com.leander.momo_practice.house_info.detail.more.view.viewmodel.MoreDetailViewModel
import com.leander.momo_practice.house_info.detail.repository.DetailRepository
import com.leander.momo_practice.house_info.detail.view.viewmodel.DetailViewModel
import com.leander.momo_practice.house_info.repository.HouseInfoRepository
import com.leander.momo_practice.house_info.view.viewmodel.InfoListViewModel


/* Created on 2022/9/23 */

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(InfoListViewModel::class.java) -> InfoListViewModel(repository as HouseInfoRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository as DetailRepository) as T
            modelClass.isAssignableFrom(MoreDetailViewModel::class.java) -> MoreDetailViewModel(repository as AnimalInfoDetailRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}