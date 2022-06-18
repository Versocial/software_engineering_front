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

class PileDetailAdapter :
    ListAdapter<Pile, PileDetailAdapter.DetailAdapter>(FlowerDiffCallback) {

    class DetailAdapter(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val WorkTextView: TextView = itemView.findViewById(R.id.is_work)
        private val PileTextView: TextView = itemView.findViewById(R.id.pile_id)
        private val HeadTotalTextView: TextView = itemView.findViewById(R.id.head_total_count)
        private val HeadTotalTime: TextView = itemView.findViewById(R.id.head_total_time)
        private val HeadQuantityTextView: TextView = itemView.findViewById(R.id.head_total_quantity)
        private var currentPile: Pile? = null


        fun bind(pile: Pile) {
            currentPile = pile
            PileTextView.text = pile.id.toString()
            WorkTextView.text = pile.is_work.toString()
            HeadTotalTextView.text = pile.total_count.toString()
            HeadTotalTime.text = pile.total_time
            HeadQuantityTextView.text = pile.total_quantity.toString()
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pile_detail_item, parent, false)
        return DetailAdapter(view)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: DetailAdapter, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<Pile>() {
    override fun areItemsTheSame(oldItem: Pile, newItem: Pile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pile, newItem: Pile): Boolean {
        return oldItem.id == newItem.id
    }
}
