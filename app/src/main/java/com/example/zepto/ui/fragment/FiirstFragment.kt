package com.example.zepto.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterSubListCategories
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.model.mainSubProductResponceModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FiirstFragment(val detailViewModel: DetailViewModel, val body: mainSubProductResponceModel?) :
    Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var simpleCategories: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fiirst, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.gvFirstFrag)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayList = ArrayList<cardItemModel>()
        if (body == null) {
//            arrayList.add(cardItemWithoutId(1, "https://images.app.goo.gl/K1A5ViwLzABrqC1e9", "No Item", 0, 0))
        } else {
            for (i in 0 until body!!.subProductImg.size) {
                val data = body!!.subProductImg[i]
                Log.d("firstFragTest", "populatingData: ${data}")
                arrayList.add(
                    cardItemModel(
                        data.addProductId.toString(),
                        data.addProductImg,
                        data.addProductName,
                        data.addProductQuantity,
                        Integer.parseInt(data.addProductPrice)
                    )
                )
            }
        }
//        arrayList.add(cardItemWithoutId(1, R.drawable.beauty, "Beauty", 2, 3))
//        arrayList.add(cardItemWithoutId(1, R.drawable.colddrink, "Cold Drink", 2, 3))
//        arrayList.add(cardItemWithoutId(1, R.drawable.biscut, "Biscuts", 2, 3))
//        arrayList.add(cardItemWithoutId(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemWithoutId(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
//        arrayList.add(cardItemWithoutId(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))

        val adapter = adapterSubListCategories(requireContext(), arrayList)
        simpleCategories.adapter = adapter
    }
}