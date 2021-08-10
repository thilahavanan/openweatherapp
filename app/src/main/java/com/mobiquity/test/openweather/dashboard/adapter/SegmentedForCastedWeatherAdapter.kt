package com.mobiquity.test.openweather.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.test.network.dashboard.models.ForeCastModel
import com.mobiquity.test.openweather.R
import com.mobiquity.test.openweather.databinding.SegmentedForcastWeatherItemBinding

class SegmentedForCastedWeatherAdapter(
    private val list: List<ForeCastModel>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rowData = list?.get(position)
        rowData?.let {
            (holder as SegmentedWeatherItemViewHolder).bindRowData(rowData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rowView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.segmented_forcast_weather_item, parent, false)
        return SegmentedWeatherItemViewHolder(rowView)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    private inner class SegmentedWeatherItemViewHolder(val rowView: View) :
        RecyclerView.ViewHolder(rowView) {
        fun bindRowData(rowData: ForeCastModel) {
            val binding: SegmentedForcastWeatherItemBinding? = DataBindingUtil.bind(rowView)
            binding?.let {
                it.forcastModel = rowData
            }
            binding?.executePendingBindings()
        }
    }
}