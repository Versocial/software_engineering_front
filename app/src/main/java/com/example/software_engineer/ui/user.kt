package com.example.software_engineer.ui

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class user(val username:String="",val password:String="")
