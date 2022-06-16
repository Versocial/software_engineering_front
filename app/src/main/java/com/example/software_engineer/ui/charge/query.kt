package com.example.software_engineer.ui.charge

import com.example.software_engineer.ui.BaseResp
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.*

public interface QueryRequest {
    @GET("charge/query")
    fun get(
        @Query("token") token:String,
        @Body json:QueryParam
    ): Call<QueryResp>
}

@JsonClass(generateAdapter=true)
data class QueryParam(val queue_id: Int)

@JsonClass(generateAdapter=true)
data class QueryResp(val queue_id:Int=-1, val num:Int=-1,val area:Int=-1, override val status_code:Int=-1, override val status_msg:String=""):
    BaseResp(status_msg,status_code)/*what*/