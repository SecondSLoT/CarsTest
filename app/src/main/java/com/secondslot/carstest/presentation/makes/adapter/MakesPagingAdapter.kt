package com.secondslot.carstest.presentation.makes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.secondslot.carstest.databinding.ItemListBinding
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.presentation.makes.ui.OnMakeClickListener

class MakesPagingAdapter(
    private val listener: OnMakeClickListener
) : PagingDataAdapter<Make, MakeViewHolder>(MakesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        val holder = MakeViewHolder(binding)
        holder.itemView.setOnClickListener {
            getItem(holder.bindingAdapterPosition)?.let { make ->
                listener.onMakeClicked(make.id, make.name)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MakeViewHolder, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let { make ->
            holder.binding.makeItemTextView.text = make.name
        }
    }
}

class MakeViewHolder(internal val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

class MakesComparator : DiffUtil.ItemCallback<Make>() {

    override fun areItemsTheSame(oldItem: Make, newItem: Make): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Make, newItem: Make): Boolean {
        return oldItem.id == newItem.id
    }
}
