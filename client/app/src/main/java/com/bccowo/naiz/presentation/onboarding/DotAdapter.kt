package com.bccowo.naiz.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ItemDotBinding

class DotAdapter(private val size: Int) : RecyclerView.Adapter<DotAdapter.DotViewHolder>() {
    var currentPosition = 0
        set(position) {
            field = position
            this.notifyItemRangeChanged(0, size)
        }

    inner class DotViewHolder(private val binding: ItemDotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val dotDrawable =
                if (position == currentPosition) R.drawable.dot_filled else R.drawable.dot
            binding.dot.setImageResource(dotDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val view = ItemDotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DotViewHolder(view)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = size
}