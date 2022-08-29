package com.example.zepto.module

import android.graphics.Bitmap

class retailerAddUserModel(val id:Int, val role :String, override val name :String, pic: Int ):
    retrailerFirstAddUserModel(pic,name) {
}

//open class retrailerFirstAddUserModel(open val pic: Bitmap, open val name:String )
open class retrailerFirstAddUserModel(open val pic: Int, open val name:String )