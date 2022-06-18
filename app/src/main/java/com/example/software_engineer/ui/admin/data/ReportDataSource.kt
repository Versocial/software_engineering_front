package com.example.software_engineer.ui.admin.data


import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.software_engineer.ui.admin.Report

class ReportDataSource(resources: Resources) {
    private val initialFlowerList = flowerList(resources)

    private fun flowerList(resources: Resources): List<Report> {
        return listOf(
            Report(
                time = "dsa",
                pile_charging_total_time = "dddddd",
                pile_id = 1,
                pile_charging_total_count = 1,
                pile_charging_total_fee = 2.1,
                pile_charging_total_quantity = 2.3,
                pile_service_total_fee = 2.3,
                pile_total_fee = 2.34
            ),

            Report(
                time = "dddd",
                pile_charging_total_time = "aaa",
                pile_id = 1,
                pile_charging_total_count = 1,
                pile_charging_total_fee = 2.1,
                pile_charging_total_quantity = 2.3,
                pile_service_total_fee = 2.3,
                pile_total_fee = 2.34
            )
        )
    }

    private val flowersLiveData = MutableLiveData(initialFlowerList)

    /* Adds flower to liveData and posts value. */
    fun addFlowers(flower: List<Report>) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(flower)
        } else {
            val updatedList = currentList.toMutableList()
//            updatedList.add( flower)
            updatedList.addAll(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removeFlower(flower: Report) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getFlowerForId(id: Int): Report? {
        flowersLiveData.value?.let { reports ->
            return reports.firstOrNull { it.pile_id == id }
        }
        return null
    }

    fun getFlowerList(): LiveData<List<Report>> {
        return flowersLiveData
    }


    companion object {
        private var INSTANCE: ReportDataSource? = null

        fun getDataSource(resources: Resources): ReportDataSource {
            return synchronized(ReportDataSource::class) {
                val newInstance = INSTANCE ?: ReportDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}