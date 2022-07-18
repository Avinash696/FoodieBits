package com.example.zepto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.zepto.R
import com.example.zepto.adapter.adapterSubListCategories
import com.example.zepto.model.cardItemModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CleaningEssentia5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CleaningEssentia5Fragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_cleaning_essentia5, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.gvCE5)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.c1, "All Biscuit", 2, 3))
        arrayList.add(cardItemModel(2, R.drawable.c2, "CE11", 2, 3))
        arrayList.add(cardItemModel(3, R.drawable.c3, "CE71", 2, 3))
        arrayList.add(cardItemModel(4, R.drawable.c4, "CE61", 2, 3))
        arrayList.add(cardItemModel(5, R.drawable.c5, "CE15", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c6, "CE14", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c7, "CE13", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c8, "CE12", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c9, "CE11", 2, 3))
        arrayList.add(cardItemModel(6, R.drawable.c10, "CE10", 2, 3))

        val adapter = adapterSubListCategories(requireContext(), arrayList)
        simpleCategories.adapter = adapter
    }
}