package com.example.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SingleWalletFactory( val amount: Int) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleTWalletViewModel(amount) as T
    }
}