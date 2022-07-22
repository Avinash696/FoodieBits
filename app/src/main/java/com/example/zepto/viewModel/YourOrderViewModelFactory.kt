package com.example.zepto.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class YourOrderViewModelFactory(private val amount: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleTWalletViewModel(amount) as T
    }
}