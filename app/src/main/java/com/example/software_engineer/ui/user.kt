package com.example.software_engineer.ui

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class userParam(val username:String="", val password:String="")

@JsonClass(generateAdapter=true)
data class userResp(val user_id:Int=-1, val token:String="", override val status_code:Int=-1, override val status_msg:String=""):BaseResp(status_msg,status_code)