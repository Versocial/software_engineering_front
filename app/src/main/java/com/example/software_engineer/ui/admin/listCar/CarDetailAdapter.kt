package com.example.software_engineer.ui.admin.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R
import com.example.software_engineer.ui.admin.CarInfo

class CarDetailAdapter :
    ListAdapter<CarInfo, CarDetailAdapter.DetailAdapter>(CarDiffCallback) {

    class DetailAdapter(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val UserTextView: TextView = itemView.findViewById(R.id.user_head_id)
        private val UserCarId: TextView = itemView.findViewById(R.id.user_car_id)
        private val UserCarCap: TextView = itemView.findViewById(R.id.user_car_capacity)
        private val UserCarRequset: TextView = itemView.findViewById(R.id.user_car_request_quantity)
        private val UserWaitingTime: TextView = itemView.findViewById(R.id.user_waiting_time)
        private var currentPile: CarInfo? = null


        fun bind(pile: CarInfo) {
            currentPile = pile
            UserTextView.text = pile.user_id.toString()
            UserCarId.text = pile.car_id.toString()
            UserCarCap.text = pile.car_capacity.toString()
            UserCarRequset.text = pile.requested_quantity.toString()
            UserWaitingTime.text = pile.waiting_time
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

object CarDiffCallback : DiffUtil.ItemCallback<CarInfo>() {
    override fun areItemsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
        return oldItem.car_id== newItem.car_id
    }
}
