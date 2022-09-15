package com.example.zepto.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.adapter.adapterDashboard
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.adminDashboardUpdated
import com.example.zepto.model.cardItemWithoutId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        getDashboardData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData(body: adminDashboardUpdated?) {
        val arrayList = ArrayList<cardItemWithoutId>()
        //price not use
        for (i in 0 until body!!.size) {
            val data = body[i]
            Log.d("myDay", "populatingData: $data")
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.profile,
                    "Total User",
                    Integer.parseInt(data.totalUser),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    20,
                    R.drawable.total_product,
                    "Total Product",
                    Integer.parseInt(data.totalProduct),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.sale_amount,
                    "Total Sale Qty",
                    Integer.parseInt(data.quantity),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.sale_amount,
                    "Total Sale Amount",
                    Integer.parseInt(data.totalSaleAmount),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.total_product,
                    "Total SubUser ",
                    Integer.parseInt(data.totalSubUser),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.profile,
                    "User /SubUser ",
                    Integer.parseInt(data.users),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.total_sale,
                    "Monthly Sale ",
                    Integer.parseInt(data.monthlySale),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.sale_amount,
                    "Weekly Sale",
                    Integer.parseInt(data.weeklySale),
                    3
                )
            )
            arrayList.add(
                cardItemWithoutId(
                    Integer.parseInt(data.id),
                    R.drawable.total_sale,
                    "Today Sale",
                    Integer.parseInt(data.todaySale),
                    3
                )
            )
        }
        GlobalScope.launch(Dispatchers.Main) {
            val adapter = adapterDashboard(requireContext(), arrayList)

            simpleCategories.adapter = adapter
        }
    }

    private fun getDashboardData() {
        val retro = RetrofitHelper.getClient().create(aviInterface::class.java)
        GlobalScope.launch {
            val call = retro.getDashboard()
            if (call.isSuccessful)
                populatingData(call.body())
            else
                Log.d("redEye", "getDashboardData: ${call.errorBody()}")
        }
    }

}