package br.com.stone.emeraldcomponents.basic.recyclerview

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by lucas.amaral on 03/04/2018.
 */
interface AutoUpdatableAdapter {

    fun <T> RecyclerView.Adapter<*>.autoNotify(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    compare(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    old[oldItemPosition] == new[newItemPosition]
        })

        diff.dispatchUpdatesTo(this)
    }
}