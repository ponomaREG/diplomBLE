package com.fap.diplomble

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fap.diplomble.databinding.ItemDeviceBinding

class BleAdapter : RecyclerView.Adapter<BleAdapter.ViewHolder>() {

    private var items: MutableList<BleDevice> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Log.e("item", item.toString())
        holder.binding.apply {
            itemDeviceName.text = item.name
            itemNameRssi.text = item.rssi.toString()
            itemNameMajor.text = item.major.toString()
            itemNameMinor.text = item.minor.toString()
            itemNameDistance.text = String.format("%.2f", item.distance)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val item = items[position]
            holder.binding.apply {
                itemNameRssi.text = item.rssi.toString()
                itemNameDistance.text = item.distance.toString()
            }
        } else onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<BleDevice>) {
        items.clear()
        items.addAll(newItems)
    }

    fun getItems() = items.toList()

    class ViewHolder(val binding: ItemDeviceBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<BleDevice>() {
        override fun areItemsTheSame(oldItem: BleDevice, newItem: BleDevice): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: BleDevice, newItem: BleDevice): Boolean =
            oldItem == newItem
    }
}