package com.example.zepto.ui.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.test.FileUtil
import com.example.test.aviInterface
import com.example.zepto.R
import com.example.zepto.adapter.adapterSubListSubAdmin

import com.example.zepto.databinding.ActivitySubListSubAdminBinding
import com.example.zepto.db.RetrofitHelper
import com.example.zepto.model.cardItemModel
import com.example.zepto.model.cardItemWithoutId
import com.example.zepto.model.mainSubCategoryModel
import com.example.zepto.module.Toasty
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SubListSubAdminActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubListSubAdminBinding
    private lateinit var simpleCategories: GridView
    private val PICK_IMAGE = 112
    private var filePath: File? = null
    lateinit var subCatIdKey: String
    private lateinit var dialogUpload: Dialog
    private lateinit var dialog: Dialog
    var arrayList = ArrayList<cardItemModel>()
    private lateinit var adapter: adapterSubListSubAdmin
    private val repo = RetrofitHelper.getClient().create(aviInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_list_sub_admin)
        simpleCategories = binding.gvSubListAdmin
        //sub cat

        dialogUpload = Dialog(this)
        hitMainSubCategoryApi()
        //intent
        val intent = intent
        val subCatKey = intent.getStringExtra("SubCatKey")    //name
        subCatIdKey = intent.getStringExtra("SubCatIdKey")!!
        Log.d("flow", "onCreate: $subCatKey $subCatIdKey")
        supportActionBar?.title = subCatKey

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_select_operation)

        binding.ivAddSubCategory.setOnClickListener {
            Toast.makeText(this, "Added ", Toast.LENGTH_SHORT).show()
            dialogUpload.setContentView(R.layout.dialog_subcategory)
            val dialogName = dialogUpload.findViewById<EditText>(R.id.etNameSubCategoryDialog)
            val dialogLL =
                dialogUpload.findViewById<LinearLayout>(R.id.llUploadImgMainSubCategoryDialog)

            // image picker
            dialogLL.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            }
            dialogUpload.show()
            val submit = dialogUpload.findViewById<Button>(R.id.btSubCategorySubmit)
            Log.d("checkiMg", "onActivityResult: $filePath")
            submit.setOnClickListener {
                postMainSubCategory(dialogName.text.toString())
                dialogUpload.dismiss()
            }
        }
        binding.refreshSubCatAdmin.setOnClickListener {
            hitMainSubCategoryApi()
            adapter.notifyDataSetChanged()
            binding.refreshSubCatAdmin.isRefreshing = false
        }
    }

    override fun onStart() {
        simpleCategories.setOnItemClickListener { _, _, i, l ->
            dialog.show()
            Picasso.get().load(arrayList[i].img)
                .into(dialog.findViewById<ImageView>(R.id.dialogImgSelect))
            dialog.findViewById<TextView>(R.id.dialogNameSelect).text = (arrayList[i].name)
            dialog.findViewById<Button>(R.id.btAddProductSelect).setOnClickListener {
                val intent = Intent(this, AllItemSubAdminActivity::class.java)
                intent.putExtra("SubProductIdKey", arrayList[i].id)
                Log.d("dummy", "populatingData: ${arrayList[i].id}")
                startActivity(intent)
            }
        }
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            filePath = FileUtil.from(this, data!!.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun populatingData(data: mainSubCategoryModel) {
        arrayList = ArrayList()
        for (i in 0 until data.subCategoryImg.size) {
            val dumy = data.subCategoryImg[i]

            arrayList.add(
                cardItemModel(
                    dumy.subCategoryId,
                    dumy.subCategoryImg,
                    dumy.subCategoryName,
                    2,
                    3
                )
            )
        }
        GlobalScope.launch(Dispatchers.Main) {
            adapter = adapterSubListSubAdmin(applicationContext, arrayList)
            simpleCategories.adapter = adapter
        }
    }

    private fun hitMainSubCategoryApi() {
        binding.subCategoryProgressbar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
//            val call = repo.getMainSubCategory()             subCatIdKey
            val call = repo.getMainSubCategoryFiltered(Integer.parseInt(subCatIdKey))
            if (call.isSuccessful) {
                binding.subCategoryProgressbar.visibility = View.GONE
                populatingData(call.body()!!)
            } else {
                binding.subCategoryProgressbar.visibility = View.GONE
                Toasty.getToasty(applicationContext, "${call.errorBody()}")
            }
        }
    }

    private fun postMainSubCategory(name: String) {
        Log.d("fileDataRepo", "postMainSubCategory: ")
        val filePath = filePath
        val statusTemp = "1"
        val requestBody = RequestBody.create(MediaType.parse("image/*"), filePath!!)
        val parts = MultipartBody.Part.createFormData("subCategoryImg", filePath.name, requestBody)

        val categoryId = RequestBody.create(MediaType.parse("text/plain"), subCatIdKey)
        val subCategoryName = RequestBody.create(MediaType.parse("text/plain"), name)
        val status = RequestBody.create(MediaType.parse("text/plain"), statusTemp)
        Log.d(
            "beforePost",
            "postMainCategory: ${parts.body()} $status $subCategoryName $subCatIdKey"
        )

        GlobalScope.launch(Dispatchers.IO) {
            val call = repo.postMainSubCategory(categoryId, parts, subCategoryName, status)
            Log.d("respo", "postMainCategory: $call")
            if (call.isSuccessful)
                Log.d("respo", "postMainCategory: Success ${call.body()}")
            else
                Log.d("respo", "postMainCategory:  Error ${call.message()}")
        }
    }
}