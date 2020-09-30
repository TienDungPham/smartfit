package com.smartfit.smartfit.ui.paymenthistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.entity.UserPayment
import com.smartfit.smartfit.databinding.ItemPaymentBinding

class PaymentAdapter :
    ListAdapter<UserPayment, PaymentAdapter.ViewHolder>(PaymentDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserPayment, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPaymentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PaymentDiffUtil : DiffUtil.ItemCallback<UserPayment>() {
    override fun areItemsTheSame(oldItem: UserPayment, newItem: UserPayment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserPayment, newItem: UserPayment): Boolean {
        return oldItem.description == newItem.description
    }
}