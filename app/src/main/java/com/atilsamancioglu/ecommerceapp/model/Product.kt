package com.atilsamancioglu.ecommerceapp.model

data class Product(
        val id: String,
        val name: String,
        val price: String,
        val url: String) {
    var count = 0
}
