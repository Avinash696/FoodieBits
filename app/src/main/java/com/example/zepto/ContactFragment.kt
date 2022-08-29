package com.example.zepto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zepto.adapter.adapterContactAdmin
import com.example.zepto.adapter.adapterOrderMasterAdmin
import com.example.zepto.databinding.FragmentContactBinding
import com.example.zepto.model.adminContactModel
import com.example.zepto.model.adminOrderMaster

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentContactBinding

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
        binding = FragmentContactBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun populatingData(){
        val arrayData = ArrayList<adminContactModel>()
        arrayData.add(adminContactModel(1,"Avi","avi@gmail.com","9898989898"))
        arrayData.add(adminContactModel(2,"Avi","avi@gmail.com","9898989898"))
        arrayData.add(adminContactModel(3,"Avi","avi@gmail.com","9898989898"))
        arrayData.add(adminContactModel(45,"Avi","avi@gmail.com","9898989898"))
        arrayData.add(adminContactModel(16,"Avi","avi@gmail.com","9898989898"))

        val adapter = adapterContactAdmin(arrayData, requireContext())
        binding.rvContactAdmin.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvContactAdmin.adapter = adapter
    }
}