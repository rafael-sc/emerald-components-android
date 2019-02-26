package br.com.stone.emeraldcomponents.basic.calendar

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.hide
import br.com.stone.emeraldcomponents.extension.month
import br.com.stone.emeraldcomponents.extension.show
import br.com.stone.emeraldcomponents.extension.year
import kotlinx.android.synthetic.main.widget_date.view.*
import java.util.Calendar

/**
 * Created on 01/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
open class EmeraldDateSelector : ConstraintLayout {

    var date: Calendar = Calendar.getInstance()
        set(new) {
            field = new
            refreshDateTitle(date)
        }

    private var dateChangedListener: (date: Calendar) -> Unit = {}

    var showDatePickerOnClick = true
    var navigateUsingMonths = true
        set(new) {
            field = new
            refreshDateTitle(date)
        }

    var showArrows = true
        set(new) {
            field = new
            if (showArrows) {
                emeraldImgLeftArrowDate.show()
                emeraldImgRightArrowDate.show()
            } else {
                emeraldImgLeftArrowDate.hide()
                emeraldImgRightArrowDate.hide()
            }
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    init {
        inflate(context, R.layout.widget_date, this)
        if (!isInEditMode) {
            setListeners()
        }
    }

    private fun setListeners() {
        emeraldImgLeftArrowDate.setOnClickListener {
            onLeftArrowClick()
        }
        emeraldImgRightArrowDate.setOnClickListener {
            onRightArrowClick()
        }

        emeraldTextDate.setOnClickListener {
            if (showDatePickerOnClick) {
                onDateTitleClick()
            }
        }
    }

    private fun setAttributes(attributeSet: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attributeSet,
                R.styleable.EmeraldDateSelector, 0, 0)

        showDatePickerOnClick = args.getBoolean(R.styleable.EmeraldDateSelector_showDatePicker, true)
        navigateUsingMonths = args.getBoolean(R.styleable.EmeraldDateSelector_navigateUsingMonths, true)
        showArrows = args.getBoolean(R.styleable.EmeraldDateSelector_showArrows, true)

        args.recycle()
        refreshDateTitle(date)
    }

    private fun addDate(valueToAdd: Int) {
        val field = if (navigateUsingMonths) {
            Calendar.MONTH
        } else {
            Calendar.DAY_OF_YEAR
        }

        date = Calendar.getInstance().apply {
            timeInMillis = date.timeInMillis
            set(field, get(field) + valueToAdd)
        }

        refreshDateTitle(date)
        dateChangedListener(date)
    }

    fun setDateChangedListener(dateChangedListener: (date: Calendar) -> Unit) {
        this.dateChangedListener = dateChangedListener
    }

    protected fun refreshDateTitle(date: Calendar) {
        val format = if (navigateUsingMonths) {
            context.getString(R.string.emerald_date_selector_pattern_month)
        } else {
            context.getString(R.string.emerald_date_selector_pattern_day)
        }
        emeraldTextDate.text = date.format(format)
    }

    protected open fun onRightArrowClick() {
        addDate(1)
    }

    protected open fun onLeftArrowClick() {
        addDate(-1)
    }

    protected open fun onDateTitleClick() {
        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            date = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
            refreshDateTitle(date)
            dateChangedListener(date)
        }
        DatePickerDialog(context, 0, datePickerDialogListener,
                date.year(), date.month(), date.day()).show()
    }
}