package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.databinding.ActivityApiHitTestBinding
import com.example.zepto.db.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody

class ApiHitTestActivity : AppCompatActivity() {
    private val TAG = "aviHit"
    private lateinit var binding:ActivityApiHitTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_api_hit_test)
//        hit()
    }

//    private fun hit (){
//        val name =
//            RequestBody.create(MediaType.parse("text/plain"), binding.etjsald.text.toString())
//
//        val retro = RetrofitHelper.getClient().create(AviInterface::class.java)
//        GlobalScope.launch {
//            val call = retro.postCartDetail(name,name,name,name,name)
//            if(call.isSuccessful)
//                Log.d(TAG, "hit:Success  ${call.body()}")
//            else
//                Log.d(TAG, "hit: ${call.errorBody()}")
//        }
//    }
}