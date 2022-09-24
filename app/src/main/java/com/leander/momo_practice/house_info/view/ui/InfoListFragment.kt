package com.leander.momo_practice.house_info.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.leander.momo_practice.R
import com.leander.momo_practice.databinding.FragmentInfoListBinding
import com.leander.momo_practice.global.network.Api
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import com.leander.momo_practice.global.ui.BaseFragment
import com.leander.momo_practice.house_info.repository.HouseInfoRepository
import com.leander.momo_practice.house_info.view.adpater.InfoListAdapter
import com.leander.momo_practice.house_info.view.viewmodel.InfoListViewModel
import kotlinx.coroutines.launch

class InfoListFragment :
    BaseFragment<InfoListViewModel, FragmentInfoListBinding, HouseInfoRepository>() {

    override fun getViewModel() = InfoListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInfoListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        HouseInfoRepository(remoteDataSource.buildApi(Api::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseData()//取得遠端場館資料

        /*下拉刷新場館資料*/
        binding.srInfoList.setOnRefreshListener {
            binding.srInfoList.isRefreshing = false
            getHouseData()
        }
    }

    /**取得遠端場館資料*/
    private fun getHouseData() {
        lifecycleScope.launch {
            when (val houseInfoResponse = viewModel.getHouseInfo()) {
                is Resource.Success -> {
                    initData(houseInfoResponse.value.result.results)
//                    setFakeData()
                }
                else -> {
                    setFakeData()
                }
            }
        }
    }

    /**取得本地端假資料*/
    private fun setFakeData() {
        lifecycleScope.launch {
            initData(viewModel.getFakeHouseInfo(context))
        }
    }

    /**刷新資料*/
    private fun initData(data: List<HouseInfoResponse.Result.Result>) {
        binding.rvZoo.itemAnimator = DefaultItemAnimator()
        binding.rvZoo.adapter = InfoListAdapter(
            R.layout.recyclerview_zoo_house,
            data,
            onItemClick = {
                findNavController().navigate(
                    R.id.action_navigation_info_list_to_navigation_detail,
                    bundleOf("zooInfo" to it, "title" to it.eName)
                )
            }
        )
    }
}