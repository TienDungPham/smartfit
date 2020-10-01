package com.smartfit.smartfit.ui.paymenthistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartfit.smartfit.R
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
            binding.apply {
                statusTitle.text = item.status
                paymentDescription.text = item.description
                totalPrice.text = item.totalPrice.toString()
            }

            Glide.with(binding.statusImage)
                .load("")
                .error(if (item.status == "Done") R.drawable.icon_success_rounded else R.drawable.icon_fail)
                .into(binding.statusImage)
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