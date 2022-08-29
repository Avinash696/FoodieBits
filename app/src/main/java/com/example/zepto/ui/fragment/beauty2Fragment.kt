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
 * Use the [beauty2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class beauty2Fragment(val detailViewModel: DetailViewModel) : Fragment() {
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
        return inflater.inflate(R.layout.fragment_beauty2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.gvbeauty2)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun populatingData(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(5, R.drawable.b10, "Beauty", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.b9, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(33, R.drawable.b6, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(42, R.drawable.b3, "Pizza", 2, 3))
        arrayList.add(cardItemModel(51, R.drawable.b5, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))
        arrayList.add(cardItemModel(62, R.drawable.b1, "Pizza", 2, 3))

       val adapter =adapterSubListCategories(requireContext(), arrayList,detailViewModel)
        simpleCategories.adapter = adapter
    }
}