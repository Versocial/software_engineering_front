package com.example.software_engineer.ui

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class userParam(val username:String="", val password:String="")

@JsonClass(generateAdapter=true)
data class userResp(val user_id:Int=-1,val token:String="",val status_code:Int=-1,val status_msg:String="")