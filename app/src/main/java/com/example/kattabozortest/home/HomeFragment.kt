package com.example.kattabozortest.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.kattabozortest.databinding.FragmentHomeBinding
import com.example.kattabozortest.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import remote.dto.OffersDto
import utils.observe
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by activityViewModels()

    @Inject
    lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getOffers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvHome.adapter = adapter
//        observe(viewModel.offers, ::onOffersLoaded)
    }

    private fun onOffersLoaded(offers: List<OffersDto>){
        adapter.submitList(offers)
    }
}