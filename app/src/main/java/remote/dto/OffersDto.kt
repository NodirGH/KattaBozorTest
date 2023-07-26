package remote.dto

import java.util.jar.Attributes

data class OffersDto(
    val id: Int = -1,
    val productName: String = "",
    val brand: String = "",
    val category: String = "",
    val merchant: String = "",
//    val attributes: List<ProductAttributes> = emptyList(), // TODO
    val imageWidth: Int = -1,
    val imageHeight: Int = -1,
    val imageUrl: String = "",

)
