package com.leander.momo_practice.house_info.detail.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.leander.momo_practice.R
import com.leander.momo_practice.databinding.FragmentDetailBinding
import com.leander.momo_practice.global.network.Api
import com.leander.momo_practice.global.network.Resource
import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import com.leander.momo_practice.global.ui.BaseFragment
import com.leander.momo_practice.house_info.detail.adapter.MoreInfoListAdapter
import com.leander.momo_practice.house_info.detail.repository.DetailRepository
import com.leander.momo_practice.house_info.detail.view.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding, DetailRepository>() {
    override fun getViewModel() = DetailViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        DetailRepository(remoteDataSource.buildApi(Api::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAnimalData()//取得遠端動物資料

        val info = arguments?.get("zooInfo") as HouseInfoResponse.Result.Result
        initData(info)
    }

    /**取得遠端動物資料*/
    private fun getAnimalData() {
        lifecycleScope.launch {
            when (val animalInfoResponse = viewModel.getAnimalInfo()) {
                is Resource.Success -> {
                    initAnimalData(animalInfoResponse.value.result.results)
//                    setFakeData()
                }
                else -> {
                    setFakeData()
                }
            }
        }
    }

    /**取得本地端假動物資料*/
    private fun setFakeData() {
        lifecycleScope.launch {
            initAnimalData(viewModel.getFakeAnimalInfo(context))
        }
    }

    /**刷新資料*/
    private fun initData(info: HouseInfoResponse.Result.Result) {
        binding.apply {
            tContent.text = info.eInfo
            tClosed.text = info.eMemo.ifEmpty { getString(R.string.no_closed_info) }
            tArea.text = info.eCategory

            Glide.with(imageView)
                .load(info.ePicUrl)
                .into(imageView)

        }
    }

    /**刷新動物資料*/
    private fun initAnimalData(animalList: List<AnimalInfoResponse.Result.Result>) {
        binding.rvMore.adapter = MoreInfoListAdapter(
            R.layout.recyclerview_zoo_house,
            animalList,
            onItemClick = {
                findNavController().navigate(
                    R.id.action_navigation_detail_to_navigation_more_detail,
                    bundleOf("animalInfo" to it, "title" to it.aNameCh)
                )
            }
        )
    }
}