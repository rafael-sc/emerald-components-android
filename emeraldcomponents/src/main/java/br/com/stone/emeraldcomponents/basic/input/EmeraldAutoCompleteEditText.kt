package br.com.stone.emeraldcomponents.basic.input

import android.content.Context
import android.util.AttributeSet
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_autocomplete.view.*

class EmeraldAutoCompleteEditText : EmeraldBaseEditText {
    private lateinit var dataList: List<*>

    private var selectedItem: Any? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        addView(inflate(context, R.layout.widget_autocomplete, null))
        emeraldAutoCompleteView.threshold = 1
        editText?.inputType = inputType
        editText?.setText(textAttribute)
    }

    override fun validateEditText(): Pair<Boolean, String> {
        return Pair(dataList
                .find { it.toString() == emeraldAutoCompleteView.text.toString().trim() } != null,
                context.getString(R.string.emerald_invalid_auto_complete_text)
        )
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> setItems(values: Set<T>,
                     callback: (T) -> Unit = {}) {
        //set was used to avoid duplicate elements
        dataList = values.toList()
        val newAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, dataList)
        emeraldAutoCompleteView.setAdapter(newAdapter)

        emeraldAutoCompleteView.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, position, _ ->
            selectedItem = adapter.getItemAtPosition(position) as T
            callback(selectedItem as T)
        }
    }
}
