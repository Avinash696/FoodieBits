package com.example.zepto.Admin.ui.retailerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.zepto.R
import com.example.zepto.adapter.adapterDashboard
import com.example.zepto.model.cardItemModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RetailerDashboardFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_retailer_dashboard, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleCategories = view.findViewById(R.id.dashboardRetailer)
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun populatingData() {
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(100, R.drawable.profile, "Total User", 2, 3))
        arrayList.add(cardItemModel(50, R.drawable.total_product, "Total Product", 2, 3))
        arrayList.add(cardItemModel(50, R.drawable.total_sale, "Total Sale Qty", 2, 3))
        arrayList.add(cardItemModel(200, R.drawable.sale_amount, "Total Sale Amount", 2, 3))
        arrayList.add(cardItemModel(50, R.drawable.ic_baseline_supervised_user_circle_24, "Total SubUser ", 2, 3))
        arrayList.add(cardItemModel(50, R.drawable.ic_baseline_supervised_user_circle_24, "User Incentive ", 2, 3))
        arrayList.add(cardItemModel(50, R.drawable.ic_baseline_supervised_user_circle_24, "Sub User Incentive ", 2, 3))


        val adapter = adapterDashboard(requireContext(), arrayList)

        simpleCategories.adapter = adapter
    }
}