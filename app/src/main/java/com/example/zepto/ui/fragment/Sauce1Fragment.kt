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
import com.example.zepto.model.cardItemWithoutId

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Sauce1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Sauce1Fragment(val detailViewModel: DetailViewModel) : Fragment() {
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
        return inflater.inflate(R.layout.fragment_sauce1, container, false)
    }

    private fun populatingData(){
        val arrayList = ArrayList<cardItemWithoutId>()
        arrayList.add(cardItemWithoutId(1, R.drawable.s1, "All Sause", 2, 3))
        arrayList.add(cardItemWithoutId(2, R.drawable.s2, "Sause1", 2, 3))
        arrayList.add(cardItemWithoutId(3, R.drawable.s3, "Sause1", 2, 3))
        arrayList.add(cardItemWithoutId(4, R.drawable.s4, "Sause 3", 2, 3))
        arrayList.add(cardItemWithoutId(5, R.drawable.s5, "Sause4", 2, 3))
        arrayList.add(cardItemWithoutId(6, R.drawable.s7, "Sause 5", 2, 3))
        arrayList.add(cardItemWithoutId(6, R.drawable.s5, "Sause 6", 2, 3))
        arrayList.add(cardItemWithoutId(6, R.drawable.s8 , "Sause 7", 2, 3))
        arrayList.add(cardItemWithoutId(6, R.drawable.s10, "Sause 8", 2, 3))
        arrayList.add(cardItemWithoutId(6, R.drawable.s14, "Sause", 2, 3))

       val adapter =adapterSubListCategories(requireContext(), arrayList)
        simpleCategories.adapter = adapter
    }
}