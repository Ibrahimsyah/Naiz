package com.bccowo.naiz.presentation.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.databinding.ItemProgressCandiBinding
import com.bccowo.naiz.domain.model.CandiProgress

class ProgressCandiAdapter(val listener: (CandiProgress) -> Unit) :
    RecyclerView.Adapter<ProgressCandiAdapter.ProgressCandiViewHolder>() {
    private val dataList: MutableList<CandiProgress> = mutableListOf()

    fun setData(dataList: List<CandiProgress>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class ProgressCandiViewHolder(private val view: ItemProgressCandiBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(candiProgress: CandiProgress) {
            val progress = candiProgress.progress.split('/').map { it.toInt() }
            with(view) {
                candiName.text = candiProgress.name
                candiProgressBar.max = progress[1]
                candiProgressBar.progress = progress[0]
                candiProgressText.text = candiProgress.progress
                candiImage.load(candiProgress.image) {
                    crossfade(true)
                }
            }
            itemView.setOnClickListener { listener(candiProgress) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressCandiViewHolder {
        val view =
            ItemProgressCandiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressCandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressCandiViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}