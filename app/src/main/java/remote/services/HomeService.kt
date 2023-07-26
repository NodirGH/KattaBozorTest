package remote.services

import remote.KattaBozorUrl
import remote.responses.HomeResponse
import retrofit2.http.GET

interface HomeService {

    @GET(KattaBozorUrl.OFFER)
    suspend fun getOffers(): HomeResponse
}