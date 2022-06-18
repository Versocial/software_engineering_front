package com.example.software_engineer.ui.admin.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R


class CarHeaderAdapter : RecyclerView.Adapter<CarHeaderAdapter.HeaderViewHolder>() {
    private var pileCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val UserTextView: TextView = itemView.findViewById(R.id.user_head_id)
        private val UserCarId: TextView = itemView.findViewById(R.id.user_car_id)
        private val UserCarCap: TextView = itemView.findViewById(R.id.user_car_capacity)
        private val UserCarRequset: TextView = itemView.findViewById(R.id.user_car_request_quantity)
        private val UserWaitingTime: TextView = itemView.findViewById(R.id.user_waiting_time)
        fun bind(flowerCount: Int) {

        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_detail_head, parent, false)
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