package com.bccowo.naiz.presentation.home.candilist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.databinding.ItemCandiListBinding
import com.bccowo.naiz.domain.model.Candi

class CandiListAdapter(val onClickListener: (Candi) -> Unit) :
    RecyclerView.Adapter<CandiListAdapter.CandiListViewHolder>() {
    private val dataList: MutableList<Candi> = mutableListOf()

    fun setData(dataList: List<Candi>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class CandiListViewHolder(val view: ItemCandiListBinding) :
        RecyclerView.ViewHolder(view.root) {
        private lateinit var candi: Candi
        fun bind(candi: Candi) {
            this.candi = candi
            with(view) {
                candiName.text = candi.name
                candiRating.rating = candi.rating.toFloat()
                candiRatingText.text = candi.rating.toString()
                candiAddress.text = candi.address
                candiImage.load(candi.image) {
                    crossfade(true)
                }
                itemView.setOnClickListener { onClickListener(candi) }
            }
        }

        fun getCandi() = candi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandiListViewHolder {
        val view = ItemCandiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandiListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandiListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}