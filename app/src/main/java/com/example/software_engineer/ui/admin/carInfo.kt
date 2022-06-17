package com.example.software_engineer.ui.admin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


public interface CarRequest {
    @GET("admin/pile/cars")
    fun get(
        @Query("token") token: String,
        @Query("pile_id") pile_id: Int,
    ): Call<PileCarResp>
}

@JsonClass(generateAdapter = true)
data class PileCarResp(
    @Json(name = "status_msg") val msg: String,
    @Json(name = "status_code") val status_code: Int,
    @Json(name = "cars_info") val cars: List<CarInfo>
)

@JsonClass(generateAdapter = true)
data class CarInfo(
    @Json(name = "user_id") val user_id: Int,
    @Json(name = "car_id") val car_id: Int,
    @Json(name = "car_capacity") val car_capacity: Double,
    @Json(name = "requested_quantity") val requested_quantity: Double,
    @Json(name = "waiting_time") val waiting_time: String,
)

