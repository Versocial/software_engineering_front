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
import com.example.software_engineer.ui.admin.Pile

class CarDetailAdapter() :
    ListAdapter<Pile, CarDetailAdapter.DetailAdapter>(CarDiffCallback) {

    class DetailAdapter(itemView: View ) :
        RecyclerView.ViewHolder(itemView) {
        private val WorkTextView: TextView = itemView.findViewById(R.id.is_work)
        private val PileTextView: TextView = itemView.findViewById(R.id.pile_id)
        private val HeadTotalTextView: TextView = itemView.findViewById(R.id.head_total_count)
        private val HeadTotalTime: TextView = itemView.findViewById(R.id.head_total_time)
        private val HeadQuantityTextView: TextView = itemView.findViewById(R.id.head_total_quantity)
        private var currentPile: Pile? = null


        fun bind(pile: Pile) {
            currentPile = pile
           PileTextView.text=pile.id.toString()
            WorkTextView.text = pile.is_work.toString()
            HeadTotalTextView.text=pile.total_count.toString()
            HeadTotalTime.text= pile.total_time
            HeadQuantityTextView.text=pile.total_quantity.toString()
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pile_detail_item, parent, false)
        return DetailAdapter(view )
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: DetailAdapter, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}
object CarDiffCallback : DiffUtil.ItemCallback<Pile>() {
    override fun areItemsTheSame(oldItem: Pile, newItem: Pile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pile, newItem: Pile): Boolean {
        return oldItem.id == newItem.id
    }
}
