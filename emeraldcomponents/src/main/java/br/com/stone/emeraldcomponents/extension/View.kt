package br.com.stone.emeraldcomponents.extension

import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.stone.emeraldcomponents.basic.recyclerview.SlingAdapter
import br.com.stone.emeraldcomponents.common.ParentActivityException

/**
 * Created by renan.silva on 18/04/2018.
 */

fun View.getActivity(): FragmentActivity {
    // encontra a activity para criar o viewmodel, segundo o https://twitter.com/ubiratanfsoares vai dar certo
    var context = context
    while (context is ContextWrapper) {
        if (context is FragmentActivity) {
            return context
        }
        context = context.baseContext
    }
    throw ParentActivityException()
}

fun <ITEM> RecyclerView.setUp(items: List<ITEM>,
                              defineViewType: (Int) -> Int,
                              bindHolder: View.(ITEM) -> Unit,
                              itemClick: ITEM.(View) -> Unit = {},
                              manager: RecyclerView.LayoutManager =
                                      LinearLayoutManager(this.context)): SlingAdapter<ITEM> {
    val emeraldAdapter by lazy {
        SlingAdapter(defineViewType, {
            bindHolder(it)
        }, itemClick)
    }
    layoutManager = manager
    emeraldAdapter.itemList = items
    adapter = emeraldAdapter
    return emeraldAdapter
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}