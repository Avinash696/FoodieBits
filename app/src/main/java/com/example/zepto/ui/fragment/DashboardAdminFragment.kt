package com.example.zepto.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.zepto.R
import com.example.zepto.adapter.adapterDashboard
import com.example.zepto.model.cardItemWithoutId

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardAdminFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_dashboard_admin, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.dashboardMainAdmin)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayList = ArrayList<cardItemWithoutId>()
        arrayList.add(cardItemWithoutId(100, R.drawable.coffee11, "Total User", 2, 3))
        arrayList.add(cardItemWithoutId(50, R.drawable.coffee4, "Total Product", 2, 3))
        arrayList.add(cardItemWithoutId(50, R.drawable.coffee12, "Total Sale Qty", 2, 3))
        arrayList.add(cardItemWithoutId(200, R.drawable.coffee15, "Total Sale Amount", 2, 3))
        arrayList.add(cardItemWithoutId(50, R.drawable.coffee3, "Total SubUser ", 2, 3))
        arrayList.add(cardItemWithoutId(150, R.drawable.coffee3, "User /SubUser ", 2, 3))
        arrayList.add(cardItemWithoutId(150, R.drawable.coffee3, "Monthly Sale ", 2, 3))
        arrayList.add(cardItemWithoutId(150, R.drawable.coffee3, "Weekly Sale", 2, 3))
        arrayList.add(cardItemWithoutId(150, R.drawable.coffee3, "Today Sale", 2, 3))

        val adapter = adapterDashboard(requireContext(), arrayList)

        simpleCategories.adapter = adapter
    }
}