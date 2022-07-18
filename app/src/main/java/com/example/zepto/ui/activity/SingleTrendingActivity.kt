package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewModel.SingleTWalletViewModel
import com.example.viewModel.SingleWalletFactory
import com.example.zepto.R
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.databinding.ActivitySingleTrendingBinding
import com.example.zepto.databinding.AddItemCountBinding
import com.example.zepto.databinding.BottomTotalAmountBinding
import com.example.zepto.model.cardItemModel


class SingleTrendingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleTrendingBinding
    private lateinit var dialogbinding: AddItemCountBinding
    private lateinit var dialogTotalBinding: BottomTotalAmountBinding
    private lateinit var singleWalletViewModel :SingleTWalletViewModel
    private lateinit var rvBottom: RecyclerView
    private var itemCount: Int = 0

    var amountData :Int = 0
    lateinit var nameData  :String
     var imgData  :Int =0
    //wallet item count
    var walletItemCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_trending)

        binding.btAddtrending.setOnClickListener {
            showMyDialog()
        }
        tendingItem()

        //intent get
        val intentTrending = intent
         amountData = intentTrending.getIntExtra("amountKey",1)
         nameData = intentTrending.getStringExtra("nameKey").toString()
         imgData = intentTrending.getIntExtra("imgKey",0)
        Log.d("ttt", "onCreate: $amountData  $nameData $imgData")
        activityFieldsSet()
        //viewModel
        singleWalletViewModel = ViewModelProvider(this,SingleWalletFactory(amountData))[SingleTWalletViewModel::class.java]

    }
    private fun activityFieldsSet(){
        binding.tvNameSingle.text = nameData
        binding.tvItemCostSingleTrending.text = amountData.toString()
        binding.ivIcons.setImageResource(imgData)
    }
    private fun showMyDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogbinding = AddItemCountBinding.inflate(layoutInflater)
        dialog.setContentView(dialogbinding.root)
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        //initilize item inside dialog
        dialogbinding.dialogItemName.setOnClickListener {
            Log.d("rawat", "showMyDialog: Works")
        }
        //subtract item
        dialogbinding.ivMinusItem.setOnClickListener {
            singleWalletViewModel.itemCountDec()
            singleWalletViewModel.totalAmount()
            Log.d("dialogCheck", "showMyDialog: ${singleWalletViewModel.itemCount}")
            dialogbinding.tvCount.text = (itemCount).toString()
            dialogbinding.tvAddItemRs.text = (singleWalletViewModel.totalAmount).toString()
        }
        //add item
        dialogbinding.ivAddItem.setOnClickListener {
//            Log.d("dialogCheck", "showMyDialog: ${itemCount++}")
//            dialogbinding.tvCount.text = (itemCount + 1).toString()
//            dialogbinding.tvAddItemRs.text = (itemCount * 29).toString()

            singleWalletViewModel.itemCountInc()
            singleWalletViewModel.totalAmount()
            Log.d("dialogCheck", "showMyDialog: ${singleWalletViewModel.itemCount}")
            dialogbinding.tvCount.text = (itemCount).toString()
            dialogbinding.tvAddItemRs.text = (singleWalletViewModel.totalAmount).toString()
        }

        dialogbinding.llAddItem.setOnClickListener {
            dialog.dismiss()
            showTotalDialog()
        }
    }

    private fun showTotalDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogTotalBinding = BottomTotalAmountBinding.inflate(layoutInflater)
        dialog.setContentView(dialogTotalBinding.root)
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        dialogTotalBinding.cBottomTotal.setOnClickListener {
            startActivity(Intent(this, YourOrderActivity::class.java))
            Log.d("rawat", "showTotalDialog: Works")
        }
    }

    private fun tendingItem() {

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.f1, "Maggie", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.beauty, "Beauty", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.biscut, "Biscuits", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.colddrink, "Drinks", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.egg, "Eggs", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Fruit Vegetable", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.munch, "Munch", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.surf, "Surf", 2, 3))

        binding.rvRecommendedSingle.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val arrayAdapter = adapterTrending(arrayList, this)
        binding.rvRecommendedSingle.adapter = arrayAdapter
    }
}