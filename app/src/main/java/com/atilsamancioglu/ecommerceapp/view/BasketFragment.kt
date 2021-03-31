package com.atilsamancioglu.ecommerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.ecommerceapp.R
import com.atilsamancioglu.ecommerceapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_basket.*

class BasketFragment : Fragment() {

    //Shared ViewModel
    private val productViewModel: ProductViewModel by activityViewModels()
    private var basketRecyclerAdapter : BasketRecyclerAdapter? = null


    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            if (basketRecyclerAdapter != null) {
                val selectedProduct = basketRecyclerAdapter!!.basketList[layoutPosition]
                productViewModel.deleteProductFromBasket(selectedProduct)
                basketRecyclerAdapter!!.notifyDataSetChanged()
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketRecyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(basketRecyclerView)

        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketRecyclerAdapter = BasketRecyclerAdapter(it)
            basketRecyclerView.adapter = basketRecyclerAdapter
            for (product in it) {
                println("${product.name}: ${product.count}")
            }
        })

        productViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
            totalBasketText.text = "Toplam Sepet: ${it}"
        })
    }


}