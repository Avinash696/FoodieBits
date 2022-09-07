package com.example.zepto.model

data class orderPlacedResponceModel(
    val error: Boolean,
    val orderData: List<OrderData>
)