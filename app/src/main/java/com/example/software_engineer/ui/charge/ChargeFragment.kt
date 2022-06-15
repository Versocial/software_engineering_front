package com.example.software_engineer.ui.charge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.example.software_engineer.R
import com.example.software_engineer.TheToken
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

    lateinit var chargeQuantityInput:EditText
    lateinit var carIdInput:EditText
    lateinit var chargingTypeInput: Switch
    lateinit var textInfo:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_charge, container, false)
        carIdInput=view.findViewById(R.id.car_id)
        chargeQuantityInput=view.findViewById(R.id.charging_quantity)
        chargingTypeInput=view.findViewById(R.id.fastSlow)
        textInfo=view.findViewById(R.id.textInfo)

        view.findViewById<Button>(R.id.charge).setOnClickListener {
            val  carId=Integer.parseInt(carIdInput.text.toString())
            val chargeQuantity=Integer.parseInt(chargeQuantityInput.text.toString())
            val chargingType=if (chargingTypeInput.isChecked)0 else 1
            val a=ChargeParam(carId,chargingType,chargeQuantity )
            val b=retrofit.create(PostRequest::class.java)
                val cc=b.post(
                    TheToken, a)
                    cc.enqueue(
                object :Callback<ChargeResp>{
                    override fun onFailure(call: Call<ChargeResp>, t: Throwable) {
                        t.message?.let { it1 -> Log.e("Upload error:", it1) };
                    }

                    override fun onResponse(
                        call: Call<ChargeResp>,
                        response: Response<ChargeResp>
                    ) {
                       runOnUiThread {
                           var str=""
                           (response.body() as ChargeResp).apply {
                               if(resp) {
                                   str = "您的充电请求被接收：\n$queue_id 号\n前方还有$num 人"
                               }
                               else{
                                   str="充电请求被拒绝：\n$status_code:$status_msg"
                               }
                           }
                           textInfo.text=str
                       }
                    }
                })
        }
        return view
    }

}