package com.example.software_engineer.ui.charge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.software_engineer.R
import com.example.software_engineer.TheToken
import com.example.software_engineer.isLogined
import com.example.software_engineer.retrofit
import com.example.software_engineer.ui.home.runOnUiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [ChargeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChargeFragment : Fragment() {

    lateinit var chargeQuantityInput: EditText
    lateinit var carIdInput: EditText
    lateinit var chargingTypeInput: Switch
    lateinit var textInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_charge, container, false)
        carIdInput = view.findViewById(R.id.car_id)
        chargeQuantityInput = view.findViewById(R.id.pile_num)
        chargingTypeInput = view.findViewById(R.id.fastSlow)
        textInfo = view.findViewById(R.id.textInfo)

        view.findViewById<Button>(R.id.charge).setOnClickListener {
            if (isLogined)
                charge()
            else
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.queryInfo).setOnClickListener {
            if (isLogined)
                queryInfo()
            else
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun charge() {
        //charge
        val carId = Integer.parseInt(carIdInput.text.toString())
        val chargeQuantity = Integer.parseInt(chargeQuantityInput.text.toString())
        val chargingType = if (chargingTypeInput.isChecked) 0 else 1

        retrofit.create(PostRequest::class.java).post(
            TheToken, ChargeParam(carId, chargingType, chargeQuantity)
        ).enqueue(
            object : Callback<ChargeResp> {
                override fun onFailure(call: Call<ChargeResp>, t: Throwable) {
                    t.message?.let { it -> Log.e("charge network error:", it) }
                }

                override fun onResponse(
                    call: Call<ChargeResp>,
                    response: Response<ChargeResp>
                ) {
                    runOnUiThread {
                        var str = ""
                        (response.body() as ChargeResp).apply {
                            if (resp) {
                                str = "您的充电请求被接收：\n$queue_id 号\n前方还有$num 人"
                            } else {
                                str = "充电请求被拒绝：\n$status_code:$status_msg"
                            }
                        }
                        textInfo.text = str
                    }
                }
            })
    }

    private fun queryInfo() {
        retrofit.create(QueryRequest::class.java).get(TheToken, QueryParam(0)).enqueue(
            object : Callback<QueryResp> {
                override fun onFailure(call: Call<QueryResp>, t: Throwable) {
                    t.message?.let { it -> Log.e("charge network error:", it) }
                }

                override fun onResponse(call: Call<QueryResp>, response: Response<QueryResp>) {
                    runOnUiThread {
                        var str = ""
                        (response.body() as QueryResp).apply {
                            if (queue_id > 0) {
                                str = "您的排队情况：\n$queue_id 号\n前方还有$num 人"
                            } else {
                                str = "未排队"
                            }
                        }
                        textInfo.text = str
                    }
                }
            }

        )
    }
}