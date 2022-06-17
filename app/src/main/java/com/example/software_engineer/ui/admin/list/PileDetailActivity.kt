package com.example.software_engineer.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.list.HeaderAdapter
import com.example.software_engineer.ui.admin.list.PileDetailAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.ui.admin.list.FlowersListViewModelFactory
import com.example.software_engineer.ui.admin.list.PileListViewModel

class PileDetailActivity : AppCompatActivity() {

    private val flowersListViewModel by viewModels<PileListViewModel> {
        FlowersListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pile_detail)
        val headerAdapter=HeaderAdapter()
        val detailAdapter=PileDetailAdapter()
        val concatAdapter=ConcatAdapter(headerAdapter,detailAdapter)

        val recyclerView: RecyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter=concatAdapter

        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<Pile>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}