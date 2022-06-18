package com.example.software_engineer.ui.admin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.software_engineer.R
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.ui.admin.list.*

class ReportDetailActivity : AppCompatActivity() {

    lateinit var extraData:List<Report>

    private val flowersListViewModel by viewModels<ReportListViewModel> {
        ReportListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_detail)
        val headerAdapter=ReportHeaderAdapter()
        val detailAdapter=ReportDetailAdapter()
        val concatAdapter=ConcatAdapter(headerAdapter,detailAdapter)

        val recyclerView: RecyclerView=findViewById(R.id.recycler_report_view)
        recyclerView.adapter=concatAdapter
        extraData=listOf(
            Report(
                time = "ddsadasdasdas",
                pile_charging_total_time = "dsa",
                pile_id = 1,
                pile_charging_total_count = 1,
                pile_charging_total_fee = 2.1,
                pile_charging_total_quantity = 2.3,
                pile_service_total_fee = 2.3,
                pile_total_fee = 2.34
            ),

            Report(
                time = "aaaaaaaaa",
                pile_charging_total_time = "dsa",
                pile_id = 1,
                pile_charging_total_count = 1,
                pile_charging_total_fee = 2.1,
                pile_charging_total_quantity = 2.3,
                pile_service_total_fee = 2.3,
                pile_total_fee = 2.34
            ),
                    Report(
                    time = "bbbbbbb",
            pile_charging_total_time = "dsa",
            pile_id = 1,
            pile_charging_total_count = 1,
            pile_charging_total_fee = 2.1,
            pile_charging_total_quantity = 2.3,
            pile_service_total_fee = 2.3,
            pile_total_fee = 2.34
        )
        )
        for (i in extraData.indices){
            Log.d(TAG, "onCreate: sdsadassd")
            flowersListViewModel.reportDataSource.addFlower(extraData[i])
        }
//        val reports= extraData?.get("reports")
        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<Report>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}