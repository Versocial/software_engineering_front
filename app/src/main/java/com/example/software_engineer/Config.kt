package com.example.software_engineer

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val HostURL="http://122.9.146.200:8080"
const val UsrURL="$HostURL/v1/user"
const val CarURL="$HostURL/v1/car"

val retrofit = Retrofit.Builder()
.baseUrl("$HostURL/v1/")
.addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
.build()

var isLogined:Boolean=false

val moshi = Moshi.Builder()
 .add(KotlinJsonAdapterFactory())
 .build()

var TheToken:String = ""
