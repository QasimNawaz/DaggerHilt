package com.example.dagger_hilt_demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_hilt_demo.R
import com.example.dagger_hilt_demo.data.model.DataItem
import com.example.dagger_hilt_demo.databinding.UserListItemBinding

class UserListAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UserListAdapter.ItemViewHolder>() {

    private val orders: ArrayList<DataItem> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(item: DataItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(orders[position], listener)
    }

    override fun getItemCount() = orders.size

    fun update(list: List<DataItem>) {
        this.orders.clear()
        this.orders.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: DataItem,
            listener: OnItemClickListener
        ) {
            binding.user = item
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }

        }
    }
}