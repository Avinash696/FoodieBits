package com.example.test

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R


class CustomFilterActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var sv: SearchView
    lateinit var  arrayAdapter:adapterCustomRv
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_filter)

        rv = findViewById(R.id.rvFilter)
        sv = findViewById(R.id.svFilter)
        fillarrayList()


        sv.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(inputText: String?): Boolean {
                arrayAdapter.filter.filter(inputText)
                return false
            }
        })
    }

    private fun fillarrayList() {
        val arrayList = ArrayList<customFilterModel>()
        arrayList.add(customFilterModel(R.drawable.order, "One", "Ten"))
        arrayList.add(customFilterModel(R.drawable.profile, "Two", "Eleven"))
        arrayList.add(customFilterModel(R.drawable.total_sale, "Three", "Twelve"))
        arrayList.add(customFilterModel(R.drawable.add, "Four", "Thirteen"))
        arrayList.add(customFilterModel(R.drawable.faq_back3, "Five", "Fourteen"))
        arrayList.add(customFilterModel(R.drawable.biscuit1, "Six", "Fifteen"))
        arrayList.add(customFilterModel(R.drawable.dashboard, "Seven", "Sixteen"))
        arrayList.add(customFilterModel(R.drawable.gt1, "Eight", "Seventeen"))
        arrayList.add(customFilterModel(R.drawable.adhar, "Nine", "Eighteen"))

        arrayAdapter = adapterCustomRv(this, arrayList)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv.adapter = arrayAdapter
    }


}