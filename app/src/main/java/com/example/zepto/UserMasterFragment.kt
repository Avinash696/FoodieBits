package com.example.zepto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.adapter.adapterUserMasterAdmin
import com.example.zepto.model.userMasterModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserMasterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserMasterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewLayout:View
    private lateinit var rvUserMaster :RecyclerView

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
        viewLayout= inflater.inflate(R.layout.fragment_user_master, container, false)
        return viewLayout.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        populatingData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun init(){
        rvUserMaster = viewLayout.findViewById(R.id.rvUserMasterAdmin)
    }

    private fun populatingData() {
        val adapterData = ArrayList<userMasterModel>()
            adapterData.add(userMasterModel(1,"avi","avi@gmail.com",8787878787,"12/10/2121"))
            adapterData.add(userMasterModel(1,"avi","avi@gmail.com",8787878787,"12/10/2121"))
            adapterData.add(userMasterModel(1,"avi","avi@gmail.com",8787878787,"12/10/2121"))
            adapterData.add(userMasterModel(1,"avi","avi@gmail.com",8787878787,"12/10/2121"))
            adapterData.add(userMasterModel(1,"avi","avi@gmail.com",8787878787,"12/10/2121"))
        val adapter = adapterUserMasterAdmin(adapterData,requireContext())
        rvUserMaster.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
         rvUserMaster.adapter = adapter
    }
}