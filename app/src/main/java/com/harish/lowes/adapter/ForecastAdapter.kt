package com.harish.lowes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.lowes.R
import com.harish.lowes.adapter.ForecastAdapter.VH
import com.harish.lowes.model.ForecastModel
import com.harish.lowes.utils.OnItemClickListener

class ForecastAdapter(var forecastModels: List<ForecastModel>, var context: Context) : RecyclerView.Adapter<VH>() {
    private var position = 0

    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.forecast_item, parent, false)
        return VH(view)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textViewTemop.text = forecastModels[position].temp + "°F"
        holder.textViewCloud.text = forecastModels[position].cloud
        this.position = position
    }

    fun setClickListener(itemClickListener: OnItemClickListener?) {
        clickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return forecastModels.size
    }

    inner class VH(itemView: View) : ViewHolder(itemView) {
        var textViewTemop: TextView
        var textViewCloud: TextView

        init {
            textViewTemop = itemView.findViewById(R.id.textView_fftemp)
            textViewCloud = itemView.findViewById(R.id.textView_ffcloud)
            itemView.setOnClickListener { v -> if (clickListener != null) clickListener!!.onClick(v, adapterPosition) }
        }
    }

}