package com.example.software_engineer.ui.car

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.software_engineer.CarURL
import com.example.software_engineer.R
import com.example.software_engineer.TheToken
import com.example.software_engineer.moshi
import com.example.software_engineer.ui.addCarResp
import com.example.software_engineer.ui.home.runOnUiThread
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.internal.EMPTY_REQUEST
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 * Use the [CarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarFragment : Fragment() {

    lateinit var  carBatteryCP :EditText
    lateinit var  button:Button
    lateinit var  answer:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_car, container, false)
        carBatteryCP=view.findViewById(R.id.batteryCap)
        answer=view.findViewById(R.id.carAddAnswer)
        button=view.findViewById(R.id.addCar)
        button.setOnClickListener { addCar(carBatteryCP.text.toString()) }
        return view
    }


    private fun addCar(cap:String) {
        thread {
            try {
                val RespAdapter= moshi.adapter(addCarResp::class.java)
                val client= OkHttpClient()
                val empty: RequestBody = EMPTY_REQUEST
                val request= Request.Builder(). url("$CarURL?token=$TheToken&cap=$cap").post(empty).build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if (responseData!=null){
                    val car=RespAdapter. fromJson(responseData)

                    if (car!= null) {
                        runOnUiThread{
                            answer.text=car.status_msg+"  "+car.car_id
                        }
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}