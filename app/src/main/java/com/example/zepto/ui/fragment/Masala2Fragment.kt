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
 * Use the [Masala2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Masala2Fragment(val detailViewModel: DetailViewModel) : Fragment() {
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
        return inflater.inflate(R.layout.fragment_masala2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.gvMasala2)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun populatingData(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.gt1, "All Green", 2, 3))
        arrayList.add(cardItemModel(2, R.drawable.gt2, "Green Tea1", 2, 3))
        arrayList.add(cardItemModel(3, R.drawable.gt3, "Green tea 2", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.gt4, "Green Tea 3", 2, 3))
        arrayList.add(cardItemModel(5, R.drawable.gt5, "Green Tea 4", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.gt6, "Green tea 5", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.gt7, "Green Tea 6", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.gt8 , "Green tea 7", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.gt9, "Green tea 8", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.gt10, "Green Tea", 2, 3))

       val adapter =adapterSubListCategories(requireContext(), arrayList,detailViewModel)
        simpleCategories.adapter = adapter
    }
}