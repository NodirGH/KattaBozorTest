package com.example.kattabozortest.home

import remote.dto.OffersDto
import repository.HomeRepository
import javax.inject.Inject

interface HomeUseCase {
    suspend fun getOffers(): List<OffersDto>
}

class HomeUseCaseImpl @Inject constructor(private val repo: HomeRepository) :
    HomeUseCase {
    override suspend fun getOffers(): List<OffersDto> {
        return repo.getOffers()
    }

}