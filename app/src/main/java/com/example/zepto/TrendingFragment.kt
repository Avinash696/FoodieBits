package com.example.zepto

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.adapter.adapterTrendingAdmin
import com.example.zepto.databinding.FragmentTrendingBinding
import com.example.zepto.model.cardItemModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TrendingFragment : Fragment() {
    private lateinit var binding: FragmentTrendingBinding
    private lateinit var dialog:Dialog

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
//        return inflater.inflate(R.layout.fragment_trending, container, false)
        binding = FragmentTrendingBinding.inflate(requireActivity().layoutInflater)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populatingData()

        dialogRemoveCat()
        binding.ivAdminAddCat.setOnClickListener {
            dialogAddCat()
            dialog.show()
        }
        binding.ivAdminRemoveCat.setOnClickListener {
            dialogAddCat()
            dialog.show()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayData = ArrayList<cardItemModel>()
        arrayData.add(cardItemModel(1, R.drawable.beauty, "Beauty",23,1))
        arrayData.add(cardItemModel(2, R.drawable.instant9, "Instant Food",33,21))
        arrayData.add(cardItemModel(3, R.drawable.cd1, "Cold Drink",43,12))
        arrayData.add(cardItemModel(4, R.drawable.biscuit10, "Biscuts",21,43))
        arrayData.add(cardItemModel(5, R.drawable.c3, "Choco",32,12))
        arrayData.add(cardItemModel(6, R.drawable.maggi, "Masala",21,34))
        arrayData.add(cardItemModel(7, R.drawable.oil1, "Oil",42,12))
        arrayData.add(cardItemModel(8, R.drawable.biscuit9, "Sauce",53,12))


//        arrayData.add(adminTrendingModel(1,"01/01/2020","571 More Market","COD",1))
//        arrayData.add(adminTrendingModel(2,"01/11/2020","571 More Market","COD",1))
//        arrayData.add(adminTrendingModel(3,"01/21/2020","571 More Market","COD",1))
//        arrayData.add(adminTrendingModel(4,"01/31/2020","571 More Market","COD",1))
//        arrayData.add(adminTrendingModel(5,"01/41/2020","571 More Market","COD",1))

        val adapter = adapterTrendingAdmin(arrayData, requireContext())
        binding.rvTrendingAdmin.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvTrendingAdmin.adapter = adapter

    }
    private fun dialogAddCat(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_trending_admin)
    }
    private fun dialogRemoveCat(){
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_user_master)
    }
}
