package com.example.software_engineer.ui.admin.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.software_engineer.ui.admin.CarInfo

class CarDataSource(resources: Resources) {
    private val initialFlowerList = flowerList(resources)

    private fun flowerList(resources: Resources): List<CarInfo> {
        return listOf(
            CarInfo(
                user_id = 1,
                car_id = 2,
                car_capacity = 212.0,
                requested_quantity = 213.0,
                waiting_time = "dsadsa"
            )
        )
    }

    private val flowersLiveData = MutableLiveData(initialFlowerList)

    fun addFlowers(flower: List<CarInfo>) {
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
    /* Adds flower to liveData and posts value. */

    /* Removes flower from liveData and posts value. */
    fun removeFlower(flower: CarInfo) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getFlowerForId(id: Int): CarInfo? {
        flowersLiveData.value?.let { piles ->
            return piles.firstOrNull { it.car_id == id }
        }
        return null
    }

    fun getFlowerList(): LiveData<List<CarInfo>> {
        return flowersLiveData
    }


    companion object {
        private var INSTANCE: CarDataSource? = null

        fun getDataSource(resources: Resources): CarDataSource {
            return synchronized(CarDataSource::class) {
                val newInstance = INSTANCE ?: CarDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}