package com.example.software_engineer.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.software_engineer.R
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.ui.admin.list.*

class CarDetailActivity : AppCompatActivity() {

    private val flowersListViewModel by viewModels<CarListViewModel> {
        CarListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pile_detail)
        val headerAdapter=CarHeaderAdapter()
        val detailAdapter=CarDetailAdapter()
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