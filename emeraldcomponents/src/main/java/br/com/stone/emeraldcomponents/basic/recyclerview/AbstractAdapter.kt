package br.com.stone.emeraldcomponents.basic.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * Created by renan.silva on 22/03/2018.
 */
abstract class AbstractAdapter<ITEM>
    : RecyclerView.Adapter<AbstractAdapter.Holder>(), AutoUpdatableAdapter {

    var itemList: List<ITEM> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        autoNotify(oldValue, newValue) { oldItem, newItem ->
            oldItem == newItem
        }
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val viewHolder = Holder(view)
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    protected open fun onItemClick(itemView: View, position: Int) {
    }

    protected open fun View.bind(item: ITEM) {
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
