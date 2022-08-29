package com.example.zepto.ui.activity

import  android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zepto.R
import com.example.zepto.adapter.adapterItemSubAdmin
import com.example.zepto.databinding.ActivityAllItemSubAdminBinding
import com.example.zepto.model.cardItemModel


class AllItemSubAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemSubAdminBinding
    val arrayList = ArrayList<cardItemModel>()
    lateinit var data: cardItemModel
    private val PICK_FROM_CAMERA = 1
    private val PICK_FROM_GALLARY = 2
    lateinit var dialog :Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        val mobileArray = arrayOf(
            "Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"
        )
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            com.example.zepto.R.layout.activity_all_item_sub_admin
        )
        supportActionBar!!.title = "Add Product"
        listData(mobileArray)

        binding.ivAddAdminUSer.setOnClickListener {
            //add
            startActivity(Intent(Intent(this, AddProductFormActivity::class.java)))
        }
         dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_show_form)
        val name = dialog.findViewById<EditText>(R.id.etShowName).text
        val price = dialog.findViewById<EditText>(R.id.etShowPrice).text
        val quantity = dialog.findViewById<EditText>(R.id.etShowQunatity).text
        binding.lvAddUserItem.setOnItemClickListener { adapterView, view, i, l ->
            dialog.show()
            //specfic data take out
            data = arrayList[i]
//            dialog.findViewById<ImageView>(R.id.ivEditShowName).setOnClickListener {
//                dialog.findViewById<TextView>(R.id.dialogNameSelect).text =
//            }
            dialog.findViewById<Button>(R.id.btSubmitAddProduct).setOnClickListener {
                //model class changed
                data.name = name.toString()
                data.discount = Integer.parseInt(price.toString())
                data.Price = Integer.parseInt(quantity.toString())
                dialog.dismiss()
            }
            val btState = dialog.findViewById<Button>(R.id.btActivateDeactivateForm)
            btState.setOnClickListener {
                if (btState.text.equals("Deactivate"))
                    btState.text = "Activate"
                else
                    btState.text = "Deactivate"
            }
            dialog.findViewById<ImageView>(R.id.ivAddImageForm).setOnClickListener {

                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_FROM_GALLARY);
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var bitmap: Bitmap? = null
        when(requestCode){
            PICK_FROM_GALLARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    //pick image from gallery
                    val selectedImage = data!!.data
                    val  filePathColumn = arrayOf( MediaStore.Images.Media.DATA)

                    // Get the cursor
                    val cursor = contentResolver
                        .query(selectedImage!!, filePathColumn, null, null, null);
                    // Move to first row
                    cursor!!.moveToFirst();

                    val  columnIndex = cursor . getColumnIndex (filePathColumn[0]);
                    val imgDecodableString = cursor . getString (columnIndex);
                    cursor.close();
                    bitmap = BitmapFactory.decodeFile(imgDecodableString)
                    dialog.findViewById<ImageView>(R.id.ivAddImageForm).setImageBitmap(bitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun listData(mobileArray: Array<String>) {

        arrayList.add(cardItemModel(12, R.drawable.by1, "Beauty", 2, 3))
        arrayList.add(cardItemModel(11, R.drawable.instant1, "Instant Food", 2, 3))
        arrayList.add(cardItemModel(21, R.drawable.cd1, "Cold Drink", 2, 3))
        arrayList.add(cardItemModel(31, R.drawable.biscut, "Biscuts", 2, 3))
        arrayList.add(cardItemModel(41, R.drawable.c1, "Choco", 2, 3))
        arrayList.add(cardItemModel(51, R.drawable.m1, "Masala", 2, 3))
        arrayList.add(cardItemModel(61, R.drawable.oil1, "Oil", 2, 3))
        arrayList.add(cardItemModel(71, R.drawable.s1, "Sauce", 2, 3))
        arrayList.add(cardItemModel(17, R.drawable.coffee0, "Coffee", 2, 3))
        arrayList.add(cardItemModel(15, R.drawable.gt1, "Green Tea", 2, 3))
        arrayList.add(cardItemModel(14, R.drawable.tea1, "Tea ", 2, 3))
        arrayList.add(cardItemModel(13, R.drawable.clean_item, "Home Clean", 2, 3))
        val adapterItemSubAdmin = adapterItemSubAdmin(this, arrayList)
        val listView = binding.lvAddUserItem
        listView.adapter = adapterItemSubAdmin
    }
}