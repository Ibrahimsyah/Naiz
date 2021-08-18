package com.bccowo.naiz.presentation.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ItemCandiGridBinding
import com.bccowo.naiz.domain.model.Candi

class PopularCandiAdapter(
    private val homeViewModel: HomeViewModel,
    private val lifeCyclerOwner: LifecycleOwner,
    private val onClickListener: (Candi) -> Unit,
    private val onBookmarkChange: (Candi, Boolean) -> Unit
) : RecyclerView.Adapter<PopularCandiAdapter.PopularCandiViewHolder>() {
    private val dataList: MutableList<Candi> = mutableListOf()

    fun setData(dataList: List<Candi>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class PopularCandiViewHolder(private val view: ItemCandiGridBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(candi: Candi) {
            var isFavorite = false
            with(view) {
                candiName.text = candi.name
                candiRating.rating = candi.rating.toFloat()
                candiRatingText.text = candi.rating.toString()
                candiImage.load(candi.image) {
                    crossfade(true)
                }
                itemView.setOnClickListener { onClickListener(candi) }
                homeViewModel.checkCandiBookmarked(candi.id).observe(lifeCyclerOwner, {
                    isFavorite = it
                    val icon = if (it) R.drawable.ic_bookmark_active else R.drawable.ic_bookmark
                    view.fabBookmark.setImageResource(icon)
                })
                fabBookmark.setOnClickListener { onBookmarkChange(candi, !isFavorite) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCandiViewHolder {
        val view = ItemCandiGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularCandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularCandiViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}