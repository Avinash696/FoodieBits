package com.example.zepto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.zepto.viewModel.DetailViewModel
import com.example.zepto.R
import com.example.zepto.adapter.adapterSubListCategories
import com.example.zepto.model.cardItemModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Oil5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Oil5Fragment(val detailViewModel: DetailViewModel) : Fragment() {
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
        return inflater.inflate(R.layout.fragment_oil5, container, false)
    }

    private fun populatingData(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.oil5, "All Green", 2, 3))
        arrayList.add(cardItemModel(2, R.drawable.oil3, "oil1", 2, 3))
        arrayList.add(cardItemModel(3, R.drawable.oil8, "oil2", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.oil4, "Green Tea 3", 2, 3))
        arrayList.add(cardItemModel(5, R.drawable.oil2, "Green Tea 4", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.oil6, "Green tea 5", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.oil7, "Green Tea 6", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.oil8 , "Green tea 7", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.oil9, "Green tea 8", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.oil10, "Green Tea", 2, 3))

       val adapter =adapterSubListCategories(requireContext(), arrayList,detailViewModel)
        simpleCategories.adapter = adapter
    }
}