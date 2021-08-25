package com.bccowo.naiz.presentation.home.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.databinding.ItemProgressCandiBinding
import com.bccowo.naiz.domain.model.Candi

class ProgressCandiAdapter(val listener: (Candi) -> Unit) :
    RecyclerView.Adapter<ProgressCandiAdapter.ProgressCandiViewHolder>() {
    private val dataList: MutableList<Candi> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList: List<Candi>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class ProgressCandiViewHolder(private val view: ItemProgressCandiBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(candiProgress: Candi) {
            with(view) {
                candiName.text = candiProgress.name
                candiProgressBar.max = candiProgress.totalReliefs
                candiProgressBar.progress = candiProgress.scannedRelief
                "${candiProgress.scannedRelief}/${candiProgress.totalReliefs}".also { candiProgressText.text = it }
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