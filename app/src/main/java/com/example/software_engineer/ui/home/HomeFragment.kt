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
import com.example.software_engineer.UsrURL
import com.example.software_engineer.databinding.FragmentHomeBinding
import com.example.software_engineer.moshi
import com.example.software_engineer.ui.user
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
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
            textView.text = "ertete"
        }
        val button:Button=binding.login
        button.setOnClickListener{
            sendRequestWithHttpURL(binding.signInAccount.text.toString(),binding.signInPassWord.text.toString())
        }
        return root
    }
    private fun sendRequestWithHttpURL(name:String,passwd:String) {
        thread {
            try {
           val usrA= moshi.adapter(user::class.java)
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val requestBody =  usrA.toJson(user(name,passwd)).toRequestBody(mediaType)
                Log.d(TAG, "sendRequestWithHttpURL: usr(name,passwd).toString"+usrA.toJson(user(name,passwd)))
                val client=OkHttpClient()
                val request=Request.Builder().url("$UsrURL/register").post(requestBody).build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if (responseData!=null){
                    showResponse(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun showResponse(response:String){
        runOnUiThread{
        binding.textHome.text=response
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}