package com.leander.momo_practice.global.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.leander.momo_practice.global.network.RemoteDataSource
import com.leander.momo_practice.global.repository.BaseRepository


/* Created on 2022/9/23 */

abstract class BaseFragment<VM:ViewModel, B:ViewBinding, R:BaseRepository> : Fragment(){
    protected lateinit var viewModel: VM
    protected lateinit var binding:B

    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B
    abstract fun getFragmentRepository() : R
}