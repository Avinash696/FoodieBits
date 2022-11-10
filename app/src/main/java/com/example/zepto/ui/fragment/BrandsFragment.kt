package com.example.zepto.ui.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.FileUtil
import com.example.zepto.R
import com.example.zepto.adapter.adapterBrands
import com.example.zepto.databinding.DialogBannerBinding
import com.example.zepto.databinding.FragmentBrandsBinding
import com.example.zepto.model.brandModel
import com.example.zepto.module.Toasty
import java.io.File
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BrandsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBrandsBinding
    private lateinit var adapterBrands: adapterBrands
    private lateinit var filepath: File
    var statusBrand = arrayOf("Active", "Deactivate")
    lateinit var dialogBinding: DialogBannerBinding

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
//        return inflater.inflate(R.layout.fragment_brands, container, false)
        binding = FragmentBrandsBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populatingData()
        binding.svBrands.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapterBrands.filter.filter(p0)
                return false
            }

        })

        //upload btn
        binding.btnAddBrands.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_banner)
            dialog.show()

            //dialog var define
            val btnSubmit = dialog.findViewById<Button>(R.id.btnSubmitBanner)
            val fileSelect = dialog.findViewById<LinearLayout>(R.id.llUploadFile)
            val bannerDate = dialog.findViewById<TextView>(R.id.tvBannerDate)
            val bannerStatus = dialog.findViewById<Spinner>(R.id.spBannerStatus)
            btnSubmit.setOnClickListener {
                dialog.dismiss()
            }
            //onclick on submit
//            val  submit = dialog.findViewById<Button>(R.id.btnUploadBanner)
            dialogBinding = DialogBannerBinding.inflate(layoutInflater)

//            dialogBinding.btnSubmitBanner.setOnClickListener {
//                //send data from here to server
//                dialog.dismiss()
//            }
            //upload File
            fileSelect.setOnClickListener {
//                val intent = Intent()
//                intent.action = Intent.ACTION_PICK
//                intent.type = "images/*"
//                startActivityForResult(Intent.createChooser(intent,"Select Picker"),76)

                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 76)
            }
            //btn date
            bannerDate.setOnClickListener {
                val cal = Calendar.getInstance()
                val y = cal.get(Calendar.YEAR)
                val m = cal.get(Calendar.MONTH)
                val d = cal.get(Calendar.DAY_OF_MONTH)


                val datepickerdialog = DatePickerDialog(
                    requireContext(),
                    { _, year, monthOfYear, dayOfMonth ->

                        // Display Selected date in textbox
                        bannerDate.text = ("$dayOfMonth /$monthOfYear/ $year")
                    },
                    y,
                    m,
                    d
                )

                datepickerdialog.show()
            }
            val spinnerAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                statusBrand
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bannerStatus.adapter = spinnerAdapter
            //btn  status
            bannerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    dialogBinding.spBannerStatus[p2]
//                    bannerStatus[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            super.onViewCreated(view, savedInstanceState)
        }



        super.onViewCreated(view, savedInstanceState)
    }

    private fun populatingData() {
        val arrayList = ArrayList<brandModel>()
        arrayList.add(brandModel(R.drawable.gucci, "Gucci", 67483))
        arrayList.add(brandModel(R.drawable.gt1, "Adidas", 97788))
        arrayList.add(brandModel(R.drawable.offer10, "Puma", 141332))
        arrayList.add(brandModel(R.drawable.offer3, "Adidas", 32324))
        arrayList.add(brandModel(R.drawable.gucci, "Gucci", 323232))
        adapterBrands = adapterBrands(requireContext(), arrayList)
        binding.rvBrands.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvBrands.adapter = adapterBrands
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && requestCode == 76) {
            filepath = FileUtil.from(requireContext(), data.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}