package com.example.zepto.model

data class adminOrderMaster(
    val id: Int,
    val date: String,
    val address: String,
    val paymentType: String,
    val paymentStatus: Int
) {
}