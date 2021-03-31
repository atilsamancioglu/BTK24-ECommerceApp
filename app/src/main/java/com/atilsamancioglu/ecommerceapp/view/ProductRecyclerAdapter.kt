package com.atilsamancioglu.ecommerceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.ecommerceapp.R
import com.atilsamancioglu.ecommerceapp.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_row.view.*

class ProductRecyclerAdapter(val productList : List<Product>, private val listener: Listener) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val productImage = holder.itemView.findViewById<ImageView>(R.id.productImage)
        val productNameText = holder.itemView.findViewById<TextView>(R.id.productName)
        val productPriceText = holder.itemView.findViewById<TextView>(R.id.productPrice)

        holder.itemView.addBasketButton.setOnClickListener {
            Toast.makeText(it.context,"Sepete Eklendi ${productList.get(position).name}",Toast.LENGTH_LONG).show()
            listener.onItemClick(productList.get(position))
        }

        productNameText.text = productList.get(position).name
        productPriceText.text = "${productList.get(position).price} TL"
        Glide.with(holder.itemView.context).load(productList.get(position).url).into(productImage)

    }

    override fun getItemCount(): Int {
        return productList.size
    }
    interface Listener {
        fun onItemClick(product: Product)
    }

}


