package com.example.software_engineer.ui.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.list.CarDetailAdapter
import com.example.software_engineer.ui.admin.list.CarHeaderAdapter
import com.example.software_engineer.ui.admin.list.CarListViewModel
import com.example.software_engineer.ui.admin.list.CarListViewModelFactory

class CarDetailActivity : AppCompatActivity() {

    lateinit var extraData: List<CarInfo>
    private val flowersListViewModel by viewModels<CarListViewModel> {
        CarListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pile_detail)
        val headerAdapter = CarHeaderAdapter()
        val detailAdapter = CarDetailAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, detailAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        extraData = intent.getBundleExtra("extra_data")?.get("cars") as List<CarInfo>
        flowersListViewModel.insertFlowers(extraData)
        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                detailAdapter.submitList(it as MutableList<CarInfo>)
                headerAdapter.updatePileCount(it.size)
            }
        })
    }
}