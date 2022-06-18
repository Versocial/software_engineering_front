/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.software_engineer.ui.admin.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.software_engineer.ui.admin.data.CarDataSource
import com.example.software_engineer.ui.admin.data.PileDataSource

class CarListViewModel(val carDataSource: CarDataSource) : ViewModel() {

    val flowersLiveData = carDataSource.getFlowerList()

//    /* If the name and description are present, create new Flower and add it to the datasource */
//    fun insertFlower(flowerName: String?, flowerDescription: String?) {
//        if (flowerName == null || flowerDescription == null) {
//            return
//        }
//
//        val newFlower = Pile(
//            Random.nextLong(),
//            flowerName,
//            image,
//            flowerDescription
//        )
//
//        dataSource.addFlower(newFlower)
//    }
//}
}

class CarListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PileListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PileListViewModel(
                pileDataSource = PileDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}