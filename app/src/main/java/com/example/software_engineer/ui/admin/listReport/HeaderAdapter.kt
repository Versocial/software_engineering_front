

package com.example.software_engineer.ui.admin.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.software_engineer.R


class ReportHeaderAdapter: RecyclerView.Adapter<ReportHeaderAdapter.HeaderViewHolder>() {
    private var pileCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val ReportTime:TextView=itemView.findViewById(R.id.report_time)
        private val PileID:TextView=itemView.findViewById(R.id.report_pile_id)
        private val TotalCount:TextView=itemView.findViewById(R.id.report_total_count)
        private val TotalTime:TextView=itemView.findViewById(R.id.report_total_time)
        private val TotalQuantity:TextView=itemView.findViewById(R.id.report_total_quantity)
        private val TotalFee:TextView=itemView.findViewById(R.id.report_total_fee)
        private val TotalServiceFee:TextView=itemView.findViewById(R.id.report_service_total_fee)
        private val PileTotalFee:TextView=itemView.findViewById(R.id.pile_total_fee)
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