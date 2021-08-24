package com.bccowo.naiz.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bccowo.naiz.databinding.ItemOrnamentBinding
import com.bccowo.naiz.domain.model.Relief

class OrnamentAdapter(private val listener: (Relief) -> Unit) :
    RecyclerView.Adapter<OrnamentAdapter.OrnamentViewHolder>() {
    private val dataList: MutableList<Relief> = mutableListOf()

    fun setData(dataList: List<Relief>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size)
    }

    inner class OrnamentViewHolder(val view: ItemOrnamentBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(ornament: Relief) {
            with(view) {
                ornamentName.text = ornament.name
                ornamentImage.load(ornament.image)
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