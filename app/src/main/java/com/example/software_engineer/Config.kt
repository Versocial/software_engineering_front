package com.example.software_engineer

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

const val UsrURL="http://122.9.146.200:8080/v1/user"
const val CarURL="http://122.9.146.200:8080/v1/car"
val moshi = Moshi.Builder()
 .add(KotlinJsonAdapterFactory())
 .build()
var TheToken:String = ""
