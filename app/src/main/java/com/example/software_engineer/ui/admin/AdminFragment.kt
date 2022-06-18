package com.example.software_engineer.ui.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.software_engineer.*
import com.example.software_engineer.databinding.FragmentAdminBinding
import com.example.software_engineer.ui.BaseResp
import com.example.software_engineer.ui.home.HomeViewModel
import com.example.software_engineer.ui.home.runOnUiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
    private var _binding: FragmentAdminBinding? = null

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

        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.openPile.setOnClickListener {
            openPile()
        }
        binding.queryPileInfo.setOnClickListener {
            queryPileInfo()
        }
        binding.queryCarInfo.setOnClickListener {
            queryCar()
        }
        binding.queryReport.setOnClickListener {
            queryReport()
        }
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


//        val button: Button =binding.login
//        button.setOnClickListener{
//            sendRequestWithHttpURL(binding.signInAccount.text.toString(),binding.signInPassWord.text.toString(),"login")
//        }
//        binding.register.setOnClickListener {
//            sendRequestWithHttpURL(binding.signInAccount.text.toString(),binding.signInPassWord.text.toString(),"register"
//
//        binding.addCar.setOnClickListener {
//            addCar(binding.batteryCap.text.toString())
//        }
        return root
    }
    private  fun  openPile(){
        val pileId=binding.pileNum.toString()
        retrofit.create(PileRequest::class.java).post(pileId).enqueue(
            object :Callback<ClosePileResp>{
                override fun onFailure(call: Call<ClosePileResp>, t: Throwable) {
                    Toast.makeText(context,"some thing wrong",Toast.LENGTH_SHORT).show()
                    t.message?.let { it -> Log.e("charge network error:", it) };
                }

                override fun onResponse(
                    call: Call<ClosePileResp>,
                    response: Response<ClosePileResp>
                ) {
                    runOnUiThread {
                        var str=""
                        (response.body()as ClosePileResp).apply {
                            if (pile_status){
                                str="已经启动 \n $pileId"
                            }else{
                                str="已经关闭\n $pileId"
                            }
                        }
                        binding.textInfo.text=str
                    }
                }
            }

        )
    }
    private fun queryPileInfo(){
        retrofit.create(StatusRequest::class.java).get(
            TheToken
        ).enqueue(
            object :Callback<PileResp>{
                override fun onFailure(call: Call<PileResp>, t: Throwable) {
                    runOnUiThread {
                        Toast.makeText(context,"some thing wrong",Toast.LENGTH_SHORT).show()
                    }
                    t.message?.let { it->Log.e("queryPileInfo error",it) };
                }

                override fun onResponse(call: Call<PileResp>, response: Response<PileResp>) {
                   runOnUiThread {
                       val intent=Intent(context,PileDetailActivity::class.java)
                       startActivity(intent)
                   }
                }
            }
        )
    }

    private fun queryReport(){
        retrofit.create(ReportRequest::class.java).get(
            TheToken
        ).enqueue(
            object :Callback<ReportResp>{
                override fun onFailure(call: Call<ReportResp>, t: Throwable) {
                    runOnUiThread {
                        Toast.makeText(context,"some thing wrong",Toast.LENGTH_SHORT).show()
                    }
                    t.message?.let { it->Log.e("queryPileInfo error",it) };
                }

                override fun onResponse(call: Call<ReportResp>, response: Response<ReportResp>) {
                    runOnUiThread {
                        val intent=Intent(context,ReportDetailActivity::class.java)
                        val bundle=Bundle()
//                        (response.body() as ReportResp).apply {
//                            bundle.putSerializable("reports", response.body()!!.reports as Serializable)
//                        }
                        var t =response.body() as ReportResp
                        t.let {

                            bundle.putSerializable("reports",t.reports as Serializable)
                            intent.putExtra("extra_data",bundle)
                            startActivity(intent)
                        }
                    }
                }
            }
        )
    }
    private fun queryCar(){
        val pileId=binding.pileNum.toString()
        with(retrofit) {
            create(CarRequest::class.java).get(
                TheToken,
           pile_id = pileId
            ).enqueue(
                object :Callback<PileCarResp>{
                    override fun onFailure(call: Call<PileCarResp>, t: Throwable) {

                        runOnUiThread {
                            Toast.makeText(context,"some thing wrong",Toast.LENGTH_SHORT).show()
                        }
                        t.message?.let { it->Log.e("queryPileInfo error",it) };
                    }

                    override fun onResponse(
                        call: Call<PileCarResp>,
                        response: Response<PileCarResp>
                    ) {
                        runOnUiThread {


                            val intent=Intent(context,CarDetailActivity::class.java)
                            val bundle=Bundle()
//                        (response.body() as ReportResp).apply {
//                            bundle.putSerializable("reports", response.body()!!.reports as Serializable)
//                        }
                            response.body()?: let{
                                Toast.makeText(context, "empty pile",Toast.LENGTH_SHORT).show()
                                return@runOnUiThread}
                                var t =response.body() as PileCarResp
                                t.let {

                                    bundle.putSerializable("cars",t.cars as Serializable)
                                    intent.putExtra("extra_data",bundle)
                                    startActivity(intent)
                                }
                            }

                        }

                }
            )
        }
    }
    private fun showResponse(response: BaseResp, type: String){
        runOnUiThread{
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}