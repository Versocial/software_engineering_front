package com.example.software_engineer.ui.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.list.FlowersListViewModelFactory
import com.example.software_engineer.ui.admin.list.PileDetailAdapter
import com.example.software_engineer.ui.admin.list.PileHeaderAdapter
import com.example.software_engineer.ui.admin.list.PileListViewModel

class PileDetailActivity : AppCompatActivity() {

    lateinit var extraData: List<Pile>
    private val flowersListViewModel by viewModels<PileListViewModel> {
        FlowersListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pile_detail)
        val headerAdapter = PileHeaderAdapter()
        val detailAdapter = PileDetailAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, detailAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter
        extraData = intent.getBundleExtra("extra_data")?.get("piles") as List<Pile>
        flowersListViewModel.insertFlowers(extraData)
        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<Pile>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}