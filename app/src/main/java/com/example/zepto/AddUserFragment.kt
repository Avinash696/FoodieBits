package com.example.zepto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.zepto.databinding.FragmentAddUserBinding
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAddUserBinding
    var listview: ListView? = null
    var addButton: Button? = null
    var GetValue: EditText? = null
    var ListElements = arrayOf(
        "Android",
        "PHP",
        "Python"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
////        val ListElementsArrayList = ArrayList(listOf(ListElements))
//        val adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            ListElements
//        )
//        binding.listView1.adapter = adapter
        binding.button1.setOnClickListener {
//            ListElementsArrayList.add(arrayOf(binding.editText1.text.toString()))
//            adapter.notifyDataSetChanged();
        }
    }
}