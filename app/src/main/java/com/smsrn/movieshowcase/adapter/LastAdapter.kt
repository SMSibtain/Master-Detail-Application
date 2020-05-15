package com.smsrn.movieshowcase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smsrn.movieshowcase.BR
import com.smsrn.movieshowcase.adapter.LastAdapterFilterUtil.filterResult

/**
 * Created by Sibtain Raza on 4/6/2020.
 * smsibtainrn@gmail.com
 */

class LastAdapter<T> internal constructor(
    private val layout: Int,
    private val itemClickListener: OnItemClickListener<T>? = null
) : RecyclerView.Adapter<LastAdapter<T>.MyViewHolder>(), Filterable {

    var items: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            if (itemsFiltered.size > 0)
                itemsFiltered.clear()
            itemsFiltered.addAll(value)
            notifyDataSetChanged()
        }

    var itemsFiltered: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
        fun onSubItemOneClick(item: T) {}
        fun onSubItemTwoClick(item: T) {}
        fun onItemLongClick(item: T) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsFiltered[position]
        holder.itemView.setOnClickListener { itemClickListener?.onItemClick(item) }
        holder.itemView.setOnLongClickListener {
            itemClickListener?.onItemLongClick(item)
            true
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsFiltered.size
    }

    override fun getItemViewType(position: Int): Int {
        return layout
    }

    inner class MyViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            /*binding.setVariable(BR.listener, itemClickListener)*/
            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString().toLowerCase()
                if (charString.isEmpty()) {
                    itemsFiltered.clear()
                    itemsFiltered.addAll(items)
                } else {
                    val filterItems: ArrayList<T> = ArrayList()
                    for (item in items) {
                        if (filterResult(item, charString)) {
                            filterItems.add(item)
                        }
                    }
                    itemsFiltered.clear()
                    itemsFiltered.addAll(filterItems)
                }
                val filterResult = FilterResults()
                filterResult.values = itemsFiltered
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults) {
                itemsFiltered = p1.values as ArrayList<T>
                notifyDataSetChanged()
            }
        }
    }

    fun notifyItemChanged(item: T) {
        notifyItemChanged(itemsFiltered.indexOf(item))
    }
}
