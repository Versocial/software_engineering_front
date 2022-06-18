package com.example.software_engineer.ui.car

import com.example.software_engineer.ui.BaseResp
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class addCarParam(val token: String, val cap: Int)

@JsonClass(generateAdapter = true)
data class addCarResp(
    val user_id: Int = -1, val cap: Int = -1, val car_id: Int = 1,
    override val status_code: Int = -1, override val status_msg: String = ""
) :
    BaseResp(status_msg, status_code)
