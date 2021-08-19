package com.bccowo.naiz.presentation.detail_candi.ornament

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.databinding.ItemOrnamentBinding
import com.bccowo.naiz.domain.model.Ornament

class OrnamentAdapter(private val listener: (Ornament) -> Unit) :
    RecyclerView.Adapter<OrnamentAdapter.OrnamentViewHolder>() {
    private val dataList: MutableList<Ornament> = mutableListOf()

    fun setData(dataList: List<Ornament>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class OrnamentViewHolder(val view: ItemOrnamentBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(ornament: Ornament) {
            with(view) {
                ornamentName.text = ornament.name
                ornamentImage.load(ornament.image)
                tvFound.visibility = if (ornament.isFound) View.VISIBLE else View.GONE
                foundLogo.visibility = if (ornament.isFound) View.VISIBLE else View.GONE
                tvNotFound.visibility = if (!ornament.isFound) View.VISIBLE else View.GONE
            }
            itemView.setOnClickListener { listener(ornament) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrnamentViewHolder {
        val view = ItemOrnamentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrnamentViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrnamentViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}