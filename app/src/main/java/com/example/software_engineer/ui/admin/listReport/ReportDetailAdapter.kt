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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.Report

class ReportDetailAdapter :
    ListAdapter<Report, ReportDetailAdapter.DetailAdapter>(ReportDiffCallback) {


    class DetailAdapter(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val ReportTime: TextView = itemView.findViewById(R.id.report_time)
        private val PileID: TextView = itemView.findViewById(R.id.report_pile_id)
        private val TotalCount: TextView = itemView.findViewById(R.id.report_total_count)
        private val TotalTime: TextView = itemView.findViewById(R.id.report_total_time)
        private val TotalQuantity: TextView = itemView.findViewById(R.id.report_total_quantity)
        private val TotalFee: TextView = itemView.findViewById(R.id.report_total_fee)
        private val TotalServiceFee: TextView = itemView.findViewById(R.id.report_service_total_fee)
        private val PileTotalFee: TextView = itemView.findViewById(R.id.pile_total_fee)
        private var currentPile: Report? = null


        fun bind(pile: Report) {
            currentPile = pile
            PileID.text = pile.pile_id.toString()
            ReportTime.text = pile.time.toString()
            TotalCount.text = pile.pile_charging_total_count.toString()
            TotalTime.text = pile.pile_charging_total_time
            TotalQuantity.text = pile.pile_charging_total_quantity.toString()
            TotalFee.text = pile.pile_charging_total_fee.toString()
            TotalServiceFee.text = pile.pile_service_total_fee.toString()
            PileTotalFee.text = pile.pile_total_fee.toString()

        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.report_detail_item, parent, false)
        return DetailAdapter(view)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: DetailAdapter, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)
    }
}

object ReportDiffCallback : DiffUtil.ItemCallback<Report>() {
    override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
        return oldItem.pile_id == newItem.pile_id
    }
}
