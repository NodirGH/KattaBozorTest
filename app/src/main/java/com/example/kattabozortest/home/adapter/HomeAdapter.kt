package com.example.kattabozortest.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kattabozortest.databinding.ItemHomeBinding
import remote.dto.OffersDto
import java.util.Collections.emptyList

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var offerList: List<OffersDto> = emptyList()

    fun submitList(offersDto: List<OffersDto>) {
        offerList = offersDto
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(
            offerList[holder.adapterPosition]
        )
    }

    override fun getItemCount() = offerList.size

    inner class HomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(
            offersDto: OffersDto
        ) {
            binding.tvProduct.text = offersDto.productName
            binding.tvBrand.text = offersDto.brand
            binding.tvCategory.text = offersDto.category
            binding.tvMerchant.text = offersDto.merchant
            binding.ivProductImage.layoutParams.height = offersDto.imageHeight
            binding.ivProductImage.layoutParams.width = offersDto.imageWidth
//            Picasso.get().load(offersDto.imageUrl).into(binding.ivProductImage) //TODO
        }
    }
}