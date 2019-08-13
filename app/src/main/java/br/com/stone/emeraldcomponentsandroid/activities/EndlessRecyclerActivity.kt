package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.recyclerview.EndlessRecyclerViewManager
import br.com.stone.emeraldcomponents.extension.setUp
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_endless_recycler.*
import kotlinx.android.synthetic.main.item_endless_recycler.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EndlessRecyclerActivity : AppCompatActivity() {

    lateinit var endlessRecyclerViewManager: EndlessRecyclerViewManager
    private val TAG = "Endless Recycler"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endless_recycler)
        recyclerView.setUp(emptyList<Int>(), {
            if (endlessRecyclerViewManager.shouldShowLoading(it))
                R.layout.item_loading
            else
                R.layout.item_endless_recycler
        }, {
            textView?.text = it.toString()
        })
        endlessRecyclerViewManager = EndlessRecyclerViewManager(recyclerView, pageSize = 10) {
            updateData(it)
        }
        updateData(0)
    }

    private fun updateData(page: Int) {
        loadData(page) {
            CoroutineScope(Dispatchers.Main).launch {
                endlessRecyclerViewManager.addItems(it)
            }
        }
    }

    private fun loadData(page: Int, onFinished: (List<Int>) -> Unit) {
        Log.d(TAG, "START LOADING PAGE: $page")
        GlobalScope.launch {
            delay(2000)
            val newItems = when (page) {
                0 -> (1..10).toList()
                1 -> (11..20).toList()
                2 -> (21..27).toList()
                else -> listOf()
            }
            Log.d(TAG, "FINISHED LOADIND PAGE: $page")
            onFinished(newItems)
        }
    }


}
