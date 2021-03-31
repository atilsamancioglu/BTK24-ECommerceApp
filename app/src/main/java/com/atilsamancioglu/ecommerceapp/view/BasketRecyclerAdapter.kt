package com.atilsamancioglu.ecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.ecommerceapp.R
import com.atilsamancioglu.ecommerceapp.model.Product
import com.bumptech.glide.Glide

class BasketRecyclerAdapter(val basketList : List<Product>): RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>() {

    class BasketViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_recycler_row,parent,false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketImageView = holder.itemView.findViewById<ImageView>(R.id.basketImageView)
        val basketProductNameText = holder.itemView.findViewById<TextView>(R.id.basketProductNameText)
        val basketPriceText = holder.itemView.findViewById<TextView>(R.id.basketPriceText)
        val basketCountText = holder.itemView.findViewById<TextView>(R.id.basketCountText)

        Glide.with(holder.itemView.context).load(basketList.get(position).url).into(basketImageView)
        basketProductNameText.text = basketList.get(position).name
        basketPriceText.text = "Fiyat: ${basketList.get(position).price}"
        basketCountText.text = "Adet: ${basketList.get(position).count}"
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}