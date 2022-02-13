package com.secondslot.carstest.presentation.models.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.secondslot.carstest.databinding.ItemListBinding
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.presentation.models.ui.OnModelClickListener

class ModelsPagingAdapter(
    private val listener: OnModelClickListener
) : PagingDataAdapter<Model, ModelViewHolder>(ModelComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        val holder = ModelViewHolder(binding)
        holder.itemView.setOnClickListener {
            getItem(holder.bindingAdapterPosition)?.let { model ->
                listener.onModelClick(model.name)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let { model ->
            holder.binding.makeItemTextView.text = model.name
        }
    }
}

class ModelViewHolder(internal val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

class ModelComparator : DiffUtil.ItemCallback<Model>() {

    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.name == newItem.name
    }
}
