package com.example.software_engineer.ui.admin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

interface ReportRequest {
    @GET("admin/report")
    fun get(
        @Query("token") token: String,
    ): Call<ReportResp>
}

@JsonClass(generateAdapter = true)
data class ReportResp(
    @Json(name = "status_msg") val msg: String,
    @Json(name = "status_code") val status_code: Int,
    @Json(name = "reports") val reports: List<Report>
)

@JsonClass(generateAdapter = true)
data class Report(
    @Json(name = "time") val time: String,
    @Json(name = "pile_charging_total_time") val pile_charging_total_time: String,
    @Json(name = "pile_id") val pile_id: Int,
    @Json(name = "pile_charging_total_count") val pile_charging_total_count: Int,
    @Json(name = "pile_charging_total_quantity") val pile_charging_total_quantity: Double,
    @Json(name = "pile_charging_total_fee") val pile_charging_total_fee: Double,
    @Json(name = "pile_service_total_fee") val pile_service_total_fee: Double,
    @Json(name = "pile_total_fee") val pile_total_fee: Double,
) : Serializable
