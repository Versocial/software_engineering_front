package com.example.software_engineer.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.software_engineer.CarURL
import com.example.software_engineer.TheToken
import com.example.software_engineer.UsrURL
import com.example.software_engineer.databinding.FragmentHomeBinding
import com.example.software_engineer.moshi
import com.example.software_engineer.ui.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.EMPTY_REQUEST
import kotlin.concurrent.thread


fun Fragment?.runOnUiThread(action: () -> Unit) {
    this ?: return
    if (!isAdded) return // Fragment not attached to an Activity
    activity?.runOnUiThread(action)
}
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val button:Button=binding.login
        button.setOnClickListener{
            sendRequestWithHttpURL(binding.signInAccount.text.toString(),binding.signInPassWord.text.toString(),"login")
        }
        binding.register.setOnClickListener {
            sendRequestWithHttpURL(binding.signInAccount.text.toString(),binding.signInPassWord.text.toString(),"register")
        }

        binding.addCar.setOnClickListener {
            addCar(binding.batteryCap.text.toString())
        }
        return root
    }
    private fun sendRequestWithHttpURL(name:String,passwd:String,type:String) {
        thread {
            try {
           val userParamAdapter= moshi.adapter(userParam::class.java)
                val userRespAdapter= moshi.adapter(userResp::class.java)
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val requestBody =  userParamAdapter.toJson(userParam(name,passwd)).toRequestBody(mediaType)
                Log.d(TAG, "sendRequestWithHttpURL: usr(name,passwd).toString"+userParamAdapter.toJson(userParam(name,passwd)))
                val client=OkHttpClient()
                val request=Request.Builder().url("$UsrURL/$type").post(requestBody).build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if (responseData!=null){
                    val user=userRespAdapter. fromJson(responseData)
                    Log.d(TAG, "sendRequestWithHttpURL: userResp is"+user.toString())

                    if (user != null) {
                       if (user.status_code==0){
                           TheToken=user.token
                       }
                        showResponse(user,type)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun addCar(cap:String) {
        thread {
            try {
                val RespAdapter= moshi.adapter(addCarResp::class.java)
                val client=OkHttpClient()
                val empty: RequestBody = EMPTY_REQUEST
                val request=Request.Builder(). url("$CarURL?token=$TheToken&cap=$cap").post(empty).build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if (responseData!=null){
                    val car=RespAdapter. fromJson(responseData)

                    if (car!= null) {
                        showResponse(car,"addCar")
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun showResponse(response:BaseResp,type: String){
        runOnUiThread{
        binding.textHome.text=response.status_msg+"  "+type
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}