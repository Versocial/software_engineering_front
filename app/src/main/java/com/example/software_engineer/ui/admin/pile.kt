package com.example.software_engineer.ui.admin

import com.example.software_engineer.ui.BaseResp
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.*

public interface PileRequest {
    @POST("admin/pile/{id}")
    fun post(
        @Path("id") id:String
    ): Call<ClosePileResp>
}

public interface StatusRequest {
    @GET("admin/status/pipe")
    fun get(
        @Query("token") token:String,
    ): Call<PileResp>
}

@JsonClass(generateAdapter=true)
data class ClosePileResp(val pile_status:Boolean=false,  override val status_code:Int=-1, override val status_msg:String=""):
    BaseResp(status_msg,status_code)/*what*/
@JsonClass(generateAdapter = true)
data class PileResp(
    @Json(name = "status_msg") val msg: String,
    @Json(name = "status_code") val status_code: Int,
    @Json(name = "pile") val cars: List<Pile>
)

@JsonClass(generateAdapter = true)
data class Pile(
    @Json(name = "is_work") val is_work:Boolean,
    @Json(name = "charging_total_count") val total_count:Int,
    @Json(name = "charging_total_time") val total_time:String,
    @Json(name = "charging_total_quantity") val total_quantity:Double,
    @Json(name = "id") val id:Int,

)