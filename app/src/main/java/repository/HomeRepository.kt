package repository

import remote.dto.OffersDto
import remote.services.HomeService
import javax.inject.Inject
import javax.inject.Singleton

interface HomeRepository {
    suspend fun getOffers(): List<OffersDto>
}

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val service: HomeService
): HomeRepository{
    override suspend fun getOffers(): List<OffersDto> {
        val result = service.getOffers().result?.map { it.mapToOffersDto() }
        return result ?: emptyList()
    }
}