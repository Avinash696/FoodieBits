package com.example.zepto.module

class retailerAddUserModel(
    val id: String, val role:String,
    override val name:String, pic: String,
    pass:String,
    email:String,
    mobile:String,
    address:String,
    adhar:String,
    panCard:String,
    shopReg:String):
    retrailerFirstAddUserModel(pic,name)

//open class retrailerFirstAddUserModel(open val pic: Bitmap, open val name:String )
open class retrailerFirstAddUserModel(open val pic: String, open val name:String )