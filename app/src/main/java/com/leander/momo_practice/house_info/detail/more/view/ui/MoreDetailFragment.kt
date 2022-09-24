package com.leander.momo_practice.house_info.detail.more.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.leander.momo_practice.R
import com.leander.momo_practice.databinding.FragmentMoreDetailBinding
import com.leander.momo_practice.global.network.Api
import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.global.ui.BaseFragment
import com.leander.momo_practice.house_info.detail.more.repository.AnimalInfoDetailRepository
import com.leander.momo_practice.house_info.detail.more.view.viewmodel.MoreDetailViewModel

class MoreDetailFragment : BaseFragment<MoreDetailViewModel, FragmentMoreDetailBinding, AnimalInfoDetailRepository>() {

    override fun getViewModel() = MoreDetailViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoreDetailBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AnimalInfoDetailRepository(remoteDataSource.buildApi(Api::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /**刷新資料*/
    private fun initData(){
        val info = arguments?.get("animalInfo") as AnimalInfoResponse.Result.Result

        binding.apply {
            tName.text = getString(R.string.animal_name, info.aNameCh, info.aNameEn, info.aNameLatin)
            tAnimalHouse.text = getString(R.string.animal_house, info.aLocation)
            tFeature.text = getString(R.string.animal_feature, info.aFeature)
            tBehavior.text = getString(R.string.animal_behavior, info.aBehavior)
            tHabitat.text = getString(R.string.animal_habitat, info.aDistribution, info.aHabitat)
            tDiet.text = getString(R.string.animal_diet, info.aDiet)
            tClass.text = getString(R.string.animal_class, info.aPhylum, info.aClass, info.aOrder, info.aFamily)
            tUpdate.text = getString(R.string.animal_update, info.aUpdate)
            tConservation.text = getString(R.string.animal_conservation, info.aConservation)
            tCrisis.text = getString(R.string.animal_crisis, info.aCrisis.ifEmpty { getString(R.string.none) })


            Glide.with(imageView)
                .load(info.aPic01Url)
                .into(imageView)
        }
    }
}