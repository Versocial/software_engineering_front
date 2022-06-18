package com.example.software_engineer.ui.charge

import com.example.software_engineer.ui.BaseResp
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PostRequest {
    @POST("charge/come")
    fun post(
        @Query("token") token: String,
        @Body json: ChargeParam
    ): Call<ChargeResp>
}

@JsonClass(generateAdapter = true)
data class ChargeParam(val car_id: Int, val charging_type: Int, val charging_quantity: Int)

@JsonClass(generateAdapter = true)
data class ChargeResp(
    val resp: Boolean = false,
    val queue_id: Int = -1,
    val num: Int = -1,
    override val status_code: Int = -1,
    override val status_msg: String = ""
) :
    BaseResp(status_msg, status_code)/*what*/