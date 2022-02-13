package com.secondslot.carstest.presentation.year.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.secondslot.carstest.databinding.ItemListBinding
import com.secondslot.carstest.domain.model.Year
import com.secondslot.carstest.presentation.year.ui.OnYearClickListener

class YearsListAdapter(
    private val listener: OnYearClickListener
) : ListAdapter<Year, YearViewHolder>(YearComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        val holder = YearViewHolder(binding)
        holder.itemView.setOnClickListener {
            getItem(holder.bindingAdapterPosition)?.let { year ->
                listener.onYearClicked(year.year)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: YearViewHolder, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let { year ->
            holder.binding.makeItemTextView.text = year.year.toString()
        }
    }
}

class YearViewHolder(internal val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

class YearComparator : DiffUtil.ItemCallback<Year>() {

    override fun areItemsTheSame(oldItem: Year, newItem: Year): Boolean {
        return oldItem.year == newItem.year
    }

    override fun areContentsTheSame(oldItem: Year, newItem: Year): Boolean {
        return oldItem.year == newItem.year
    }
}
