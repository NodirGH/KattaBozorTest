package com.example.kattabozortest.home

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import remote.dto.OffersDto
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) :
    BaseViewModel(){

    var offers = MutableLiveData<List<OffersDto>>()


        fun getOffers(){
            vmScope.loadingLaunch {
                val result = homeUseCase.getOffers()
                offers.postValue(result)
            }
        }
}