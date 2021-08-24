package com.bccowo.naiz.presentation.home.home

import android.annotation.SuppressLint
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
    private val lifecycleOwner: LifecycleOwner,
    private val onClickListener: (Candi) -> Unit,
    private val onBookmarkChange: (Candi, Boolean) -> Unit
) : RecyclerView.Adapter<PopularCandiAdapter.PopularCandiViewHolder>() {
    private val dataList: MutableList<Candi> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList: List<Candi>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class PopularCandiViewHolder(private val view: ItemCandiGridBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(candi: Candi) {
            var isFavorite = false
            with(view) {
                candiName.text = candi.name
                candiRating.rating = candi.rating.toFloat()
                candiRatingText.text = String.format("%.1f", candi.rating)
                candiImage.load(candi.image) {
                    crossfade(true)
                }
                itemView.setOnClickListener { onClickListener(candi) }
                homeViewModel.checkCandiBookmarked(candi.id).observe(lifecycleOwner, {
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