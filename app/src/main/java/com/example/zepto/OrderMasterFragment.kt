package com.example.zepto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.AviInterface
import com.example.zepto.adapter.adapterOrderMasterAdmin
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.adminOrderMaster
import com.example.zepto.model.orderPlacedResponceModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OrderMasterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rev :RecyclerView
    private lateinit var layoutView :View

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
        layoutView =  inflater.inflate(R.layout.fragment_order_master, container, false)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        getPlacedOrder()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun init(){
        rev = layoutView.findViewById(R.id.rvOrderMasterAdmin)
    }
    private fun populating(orderData: orderPlacedResponceModel?) {
        val arrayData = ArrayList<adminOrderMaster>()
        for (i in 0 until orderData!!.orderData.size){
            val tempData = orderData.orderData[i]
            arrayData.add(adminOrderMaster(tempData.id,tempData.date,"More Market",tempData.type,1))
        }
//        arrayData.add(adminOrderMaster(1,"01/01/2020","571 More Market","COD",1))
//        arrayData.add(adminOrderMaster(2,"01/11/2020","571 More Market","COD",1))
//        arrayData.add(adminOrderMaster(3,"01/21/2020","571 More Market","COD",1))
//        arrayData.add(adminOrderMaster(4,"01/31/2020","571 More Market","COD",1))
//        arrayData.add(adminOrderMaster(5,"01/41/2020","571 More Market","COD",1))
        requireActivity().runOnUiThread {
            val adapter = adapterOrderMasterAdmin(arrayData, requireContext())
            rev.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            rev.adapter = adapter
        }
    }
    private fun getPlacedOrder(){
        val retro = RetrofitHelper.getClient().create(AviInterface::class.java)
        GlobalScope.launch {
            val call = retro.getPlacedOrder()
            if(call.isSuccessful) {
                populating(call.body())
                Log.d("dataComimg", "getPlacedOrder: ${Gson().toJson( call.body())}")
            }
            else
                Log.d("fragOrder", "getPlacedOrder: ${call.errorBody()}")
        }

    }
}