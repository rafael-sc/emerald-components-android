package br.com.stone.emeraldcomponents.basic.calendar

import android.app.DatePickerDialog
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.PopupMenu
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.hide
import br.com.stone.emeraldcomponents.extension.month
import br.com.stone.emeraldcomponents.extension.show
import br.com.stone.emeraldcomponents.extension.year
import kotlinx.android.synthetic.main.widget_date_filter.view.*
import java.util.Calendar

/**
 * Created on 23/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
class EmeraldDateFilter : ConstraintLayout {

    var startDate: Calendar = Calendar.getInstance()
        private set(new) {
            field = new
            refreshTitleAndDate()
        }

    var endDate: Calendar = Calendar.getInstance()
        private set(new) {
            field = new
            refreshTitleAndDate()
        }

    private var filterChangedListener: (startDate: Calendar, endDate: Calendar) -> Unit = { _ , _ -> }

    private var currentFilterType: EmeraldDateFilterOptions = EmeraldDateFilterOptions.TODAY

    private val datePatternString = context.getString(R.string.emerald_date_filter_pattern)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        ConstraintLayout.inflate(context, R.layout.widget_date_filter, this)
        setListeners()
        setFilter(EmeraldDateFilterOptions.TODAY)
    }

    fun setFilter(item: EmeraldDateFilterOptions) {
        currentFilterType = item
        if (item == EmeraldDateFilterOptions.PERSONALIZED) {
            setupPersonalizedLayout(true)
        } else {
            setupPersonalizedLayout(false)

            val (start, end) = item.calculate(Calendar.getInstance())
            startDate = start
            endDate = end

            filterChangedListener(startDate, endDate)
        }
    }

    fun setCustomFilter(startDate: Calendar, endDate: Calendar) {
        this.startDate = startDate
        this.endDate = endDate

        currentFilterType = EmeraldDateFilterOptions.PERSONALIZED
        setupPersonalizedLayout(true)

        filterChangedListener(startDate, endDate)
    }

    fun setOnFilterChangedListener(filterChangedListener: (startDate: Calendar, endDate: Calendar) -> Unit) {
        this.filterChangedListener = filterChangedListener
    }

    private fun setListeners() {

        emeraldDateFilterButton.setOnClickListener {
            val popUp = PopupMenu(context, emeraldDateFilterButton)
            popUp.inflate(R.menu.menu_date_filter_popup)

            popUp.setOnMenuItemClickListener { menuItem ->
                setFilter( EmeraldDateFilterOptions.values().find {
                    context.getString(it.textResId) == menuItem.title.toString()
                } ?: EmeraldDateFilterOptions.TODAY )
                true
            }

            popUp.show()
        }

        emeraldDateFilterStartDate.setOnClickListener {
            val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                startDate = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                emeraldDateFilterStartDate.text = startDate.format(datePatternString)
                filterChangedListener(startDate, endDate)
            }
            DatePickerDialog(context, 0, datePickerDialogListener,
                    startDate.year(), startDate.month(), startDate.day()).show()
        }

        emeraldDateFilterEndDate.setOnClickListener {
            val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                endDate = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                emeraldDateFilterEndDate.text = endDate.format(datePatternString)
                filterChangedListener(startDate, endDate)
            }
            DatePickerDialog(context, 0, datePickerDialogListener,
                    endDate.year(), endDate.month(), endDate.day()).show()
        }
    }

    private fun refreshTitleAndDate() {
        if (currentFilterType != EmeraldDateFilterOptions.PERSONALIZED) {
            emeraldTextDateLabel.text = context.getString(currentFilterType.textResId)
            emeraldTextDateRangeSubtitle.text = if (startDate == endDate) {
                startDate.format(datePatternString)
            } else {
                context.getString(R.string.emerald_date_filter_date_range,
                        startDate.format(datePatternString), endDate.format(datePatternString))
            }
        }
    }

    private fun setupPersonalizedLayout(shouldShowLayout: Boolean) {
        if (shouldShowLayout) {
            emeraldDateFilterExpandConstraint.show()
            emeraldTextDateRangeSubtitle.hide()

            emeraldTextDateLabel.text = context.getString(EmeraldDateFilterOptions.PERSONALIZED.textResId)
            emeraldDateFilterStartDate.text = startDate.format(datePatternString)
            emeraldDateFilterEndDate.text = endDate.format(datePatternString)
        } else {
            emeraldDateFilterExpandConstraint.hide()
            emeraldTextDateRangeSubtitle.show()
        }
    }
}