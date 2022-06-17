

package com.example.software_engineer.ui.admin.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R


class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var pileCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IsWorkTextView: TextView = itemView.findViewById(R.id.head_is_work)
        private val TotalCountTextView: TextView = itemView.findViewById(R.id.head_total_count)
        private val TotalTimeTextView: TextView = itemView.findViewById(R.id.head_total_time)
        private val TotalQuantityTextView: TextView = itemView.findViewById(R.id.head_total_quantity)

        fun bind(flowerCount: Int) {

        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pile_detail_head, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(pileCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of flowers when a flower is added or subtracted. */
    fun updatePileCount(updatedPileCount: Int) {
        pileCount = updatedPileCount
        notifyDataSetChanged()
    }
}