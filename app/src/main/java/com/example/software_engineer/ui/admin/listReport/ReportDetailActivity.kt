package com.example.software_engineer.ui.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.list.ReportDetailAdapter
import com.example.software_engineer.ui.admin.list.ReportHeaderAdapter
import com.example.software_engineer.ui.admin.list.ReportListViewModel
import com.example.software_engineer.ui.admin.list.ReportListViewModelFactory

class ReportDetailActivity : AppCompatActivity() {

    lateinit var extraData: List<Report>

    private val flowersListViewModel by viewModels<ReportListViewModel> {
        ReportListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_detail)
        val headerAdapter = ReportHeaderAdapter()
        val detailAdapter = ReportDetailAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, detailAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_report_view)
        recyclerView.adapter = concatAdapter
        extraData = intent.getBundleExtra("extra_data")?.get("reports") as List<Report>
        flowersListViewModel.insertFlowers(extraData)
//        val reports= extraData?.get("reports")
//        extraData=listOf(
//            Report(
//                time = "ddsadasdasdas",
//                pile_charging_total_time = "ddd",
//                pile_id = 1,
//                pile_charging_total_count = 1,
//                pile_charging_total_fee = 2.1,
//                pile_charging_total_quantity = 2.3,
//                pile_service_total_fee = 2.3,
//                pile_total_fee = 2.34
//            ),
//
//            Report(
//                time = "aaaaaaaaa",
//                pile_charging_total_time = "bbbb",
//                pile_id = 1,
//                pile_charging_total_count = 1,
//                pile_charging_total_fee = 2.1,
//                pile_charging_total_quantity = 2.3,
//                pile_service_total_fee = 2.3,
//                pile_total_fee = 2.34
//            ),
//                    Report(
//                    time = "bbbbbbb",
//            pile_charging_total_time = "eeee",
//            pile_id = 1,
//            pile_charging_total_count = 1,
//            pile_charging_total_fee = 2.1,
//            pile_charging_total_quantity = 2.3,
//            pile_service_total_fee = 2.3,
//            pile_total_fee = 2.34
//        )
//        )
//        for (i in extraData.indices){
//            flowersListViewModel.insertFlower(extraData)

//        }
//        val reports= extraData?.get("reports")
        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<Report>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}