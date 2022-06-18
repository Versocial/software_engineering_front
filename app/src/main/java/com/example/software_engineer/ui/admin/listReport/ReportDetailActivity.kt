package com.example.software_engineer.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.software_engineer.R
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.ui.admin.list.*

class ReportDetailActivity : AppCompatActivity() {

    private val flowersListViewModel by viewModels<ReportListViewModel> {
        ReportListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_detail)
        val headerAdapter=ReportHeaderAdapter()
        val detailAdapter=ReportDetailAdapter()
        val concatAdapter=ConcatAdapter(headerAdapter,detailAdapter)

        val recyclerView: RecyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter=concatAdapter

        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<Report>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}