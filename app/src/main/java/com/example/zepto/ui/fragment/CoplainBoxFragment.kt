package com.example.zepto.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.zepto.R
import com.example.zepto.adapter.adapterComplain
import com.example.zepto.model.adminComplainModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CoplainBoxFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var simpleCategories: ListView

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
        return inflater.inflate(R.layout.fragment_coplain_box, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.lvComplainBox)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayList = ArrayList<adminComplainModel>()
        arrayList.add(adminComplainModel(1, 232, "A1", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A2", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A3", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A4", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A5", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A6", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A7", "20/12/2021", "767676767687"))
        arrayList.add(adminComplainModel(1, 232, "A8", "20/12/2021", "767676767687"))
//        arrayList.add(adminComplainModel(2, R.drawable.coffee4, "Total Product", 2, 3))
//        arrayList.add(adminComplainModel(3, R.drawable.coffee12, "Total Sale Qty", 2, 3))
//        arrayList.add(adminComplainModel(4, R.drawable.coffee15, "Total Sale Amount", 2, 3))
//        arrayList.add(adminComplainModel(55, R.drawable.coffee3, "Total SubUser ", 2, 3))
//        arrayList.add(adminComplainModel(16, R.drawable.coffee3, "User /SubUser ", 2, 3))
//        arrayList.add(adminComplainModel(1, R.drawable.coffee3, "Monthly Sale ", 2, 3))
//        arrayList.add(adminComplainModel(157, R.drawable.coffee3, "Weekly Sale", 2, 3))
//        arrayList.add(adminComplainModel(150, R.drawable.coffee3, "Today Sale", 2, 3))

        val adapter = adapterComplain(requireContext(), arrayList)

        simpleCategories.adapter = adapter
    }

}