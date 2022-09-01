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
private lateinit var simpleCategories: GridView

/**
 * A simple [Fragment] subclass.
 * Use the [Choco5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Choco5Fragment(val detailViewModel: DetailViewModel) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_choco5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.gvChoco5)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun populatingData(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.c1, "All Biscuit", 2, 3))
        arrayList.add(cardItemModel(2, R.drawable.c2, "50 -50", 2, 3))
        arrayList.add(cardItemModel(3, R.drawable.c3, "Bounce", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.c4, "Parle G", 2, 3))
        arrayList.add(cardItemModel(5, R.drawable.c5, "Dark Fantasy", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c6, "Cookies", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c7, "Choco Files", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c8, "Little Hearts", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c9, "Marie Gold", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c10, "Badam Cookies", 2, 3))

       val adapter =adapterSubListCategories(requireContext(), arrayList,detailViewModel)
        simpleCategories.adapter = adapter
    }
}