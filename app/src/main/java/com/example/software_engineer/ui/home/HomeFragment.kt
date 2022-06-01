package com.example.software_engineer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.software_engineer.UsrURL
import com.example.software_engineer.databinding.FragmentHomeBinding
import okhttp3.OkHttpClient
import okhttp3.Request
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
            sendRequestWithHttpURL()
        }
        return root
    }
    private fun sendRequestWithHttpURL() {
        thread {
            try {
                val client=OkHttpClient()
                val request=Request.Builder().url(UsrURL+"/register").build()
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