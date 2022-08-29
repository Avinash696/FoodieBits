package com.example.zepto

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.adapter.recyclerAdapterCategories
import com.example.zepto.databinding.FragmentCategroiesBinding
import com.example.zepto.model.AdminCategoriesModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategroiesFragment : Fragment() {
    private lateinit var binding: FragmentCategroiesBinding

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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategroiesBinding.inflate(requireActivity().layoutInflater)
//        return inflater.inflate(R.layout.fragment_categroies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populatingData()
        val dialog = Dialog(requireContext())
        binding.ivAddCategoriesAdmin.setOnClickListener {
            Toast.makeText(requireContext(), "Added ", Toast.LENGTH_SHORT).show()

            dialog.setContentView(R.layout.dialog_user_master)
            dialog.show()
            val submit = dialog.findViewById<Button>(R.id.btSubmit)
            submit.setOnClickListener {
                dialog.dismiss()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayData = ArrayList<AdminCategoriesModel>()
        arrayData.add(AdminCategoriesModel(1, R.drawable.beauty, "Beauty"))
        arrayData.add(AdminCategoriesModel(2, R.drawable.instant9, "Instant Food"))
        arrayData.add(AdminCategoriesModel(3, R.drawable.cd1, "Cold Drink"))
        arrayData.add(AdminCategoriesModel(4, R.drawable.biscuit10, "Biscuts"))
        arrayData.add(AdminCategoriesModel(5, R.drawable.c3, "Choco"))
        arrayData.add(AdminCategoriesModel(6, R.drawable.maggi, "Masala"))
        arrayData.add(AdminCategoriesModel(7, R.drawable.oil1, "Oil"))
        arrayData.add(AdminCategoriesModel(8, R.drawable.biscuit9, "Sauce"))
        arrayData.add(AdminCategoriesModel(9, R.drawable.coffee0, "Coffee"))
        arrayData.add(AdminCategoriesModel(10, R.drawable.gt1, "Green Tea"))
        arrayData.add(AdminCategoriesModel(11, R.drawable.clean_item, "Home Clean"))
        arrayData.add(AdminCategoriesModel(12, R.drawable.tea1, "Tea"))

        val adapter = recyclerAdapterCategories(arrayData, requireContext())
        binding.rvCategoriesAdmin.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvCategoriesAdmin.adapter = adapter

    }
}