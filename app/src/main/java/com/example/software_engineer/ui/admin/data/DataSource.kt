package com.example.software_engineer.ui.admin.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.software_engineer.ui.admin.Pile

class DataSource(resources: Resources) {
    private val initialFlowerList = flowerList(resources)

    private fun flowerList(resources: Resources): List<Pile>{
return listOf(
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
    Pile(
        is_work = true,
        total_count = 2,
        total_time = "dsadas",
        total_quantity = 1.24234,
        id = 2
    ),
)
    }

    private val flowersLiveData = MutableLiveData(initialFlowerList)

    /* Adds flower to liveData and posts value. */
    fun addFlower(flower: Pile) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(listOf(flower))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removeFlower(flower: Pile) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getFlowerForId(id: Int): Pile? {
        flowersLiveData.value?.let { piles->
            return piles.firstOrNull{ it.id== id}
        }
        return null
    }

    fun getFlowerList(): LiveData<List<Pile>> {
        return flowersLiveData
    }


    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}