package remote.responses

import com.squareup.moshi.Json
import remote.dto.OffersDto

data class HomeResponse(
    @Json(name = "offers") val result: List<ProductInfo>?
)


data class AttributeDetails( //TODO should find solution to list
    @Json(name = "name") val name: String?,
    @Json(name = "value") val value: String?

)

data class ImageDetails(
    @Json(name = "width") val width: Int?,
    @Json(name = "height") val height: Int?,
    @Json(name = "url") val url: String?
)

data class ProductInfo(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val productName: String?,
    @Json(name = "brand") val brand: String?,
    @Json(name = "category") val category: String?,
    @Json(name = "merchant") val merchant: String?,
    @Json(name = "attributes") val attributes: List<AttributeDetails>?,
    @Json(name = "image") val image: ImageDetails?
){
    fun mapToOffersDto(): OffersDto{
        return OffersDto(
            id = id ?: -1,
            productName = productName ?: "",
            brand = brand ?: "",
            category = category ?: "",
            merchant = merchant ?: "",
            imageWidth = image?.width ?: -1,
            imageHeight = image?.height ?: -1,
            imageUrl = image?.url ?: "",
        )
    }
}
