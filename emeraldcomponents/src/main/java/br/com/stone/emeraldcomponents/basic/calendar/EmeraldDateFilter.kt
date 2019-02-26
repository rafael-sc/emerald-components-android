package br.com.stone.emeraldcomponents.basic.calendar

import android.app.DatePickerDialog
import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.hide
import br.com.stone.emeraldcomponents.extension.month
import br.com.stone.emeraldcomponents.extension.show
import br.com.stone.emeraldcomponents.extension.year
import kotlinx.android.synthetic.main.widget_date_filter.view.*
import java.util.Calendar
import java.util.Locale

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

    var currentFilterType: EmeraldDateFilterOptions = EmeraldDateFilterOptions.TODAY
        private set

    private var filterChangedListener: (startDate: Calendar, endDate: Calendar) -> Unit = { _, _ -> }

    private val datePatternString = context.getString(R.string.emerald_date_filter_pattern)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_date_filter, this)
        if (!isInEditMode) {
            setListeners()
            setFilter(EmeraldDateFilterOptions.TODAY)
        }
    }

    fun setFilter(item: EmeraldDateFilterOptions) {
        currentFilterType = item
        if (item == EmeraldDateFilterOptions.PERSONALIZED) {
            setupPersonalizedLayout()
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
        setupPersonalizedLayout()

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
                setFilter(EmeraldDateFilterOptions.values().find {
                    context.getString(it.textResId) == menuItem.title.toString()
                } ?: EmeraldDateFilterOptions.TODAY)
                true
            }

            popUp.show()
        }

        emeraldDateFilterStartDate.setOnClickListener {
            val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selected = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                startDate = if (isValidDateRange(selected, endDate)) selected else endDate
                emeraldDateFilterStartDate.text = startDate.format(datePatternString)
                filterChangedListener(startDate, endDate)
            }
            DatePickerDialog(context, 0, datePickerDialogListener,
                    startDate.year(), startDate.month(), startDate.day()).show()
        }

        emeraldDateFilterEndDate.setOnClickListener {
            val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selected = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                endDate = if (isValidDateRange(startDate, selected)) selected else startDate
                emeraldDateFilterEndDate.text = endDate.format(datePatternString)
                filterChangedListener(startDate, endDate)
            }
            DatePickerDialog(context, 0, datePickerDialogListener,
                    endDate.year(), endDate.month(), endDate.day()).show()
        }
    }

    internal fun isValidDateRange(start: Calendar, end: Calendar): Boolean {
        return if (start.time.after(end.time)) {
            showInvalidCustomDateFilterMessage()
            false
        } else {
            true
        }
    }

    private fun refreshTitleAndDate() {
        if (currentFilterType != EmeraldDateFilterOptions.PERSONALIZED) {
            emeraldTextDateLabel.text = when (currentFilterType) {
                EmeraldDateFilterOptions.THIS_MONTH, EmeraldDateFilterOptions.LAST_MONTH ->
                    startDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("pt", "BR")).capitalize()
                else -> context.getString(currentFilterType.textResId)
            }
            emeraldTextDateRangeSubtitle.text = if (startDate == endDate) {
                startDate.format(datePatternString)
            } else {
                context.getString(R.string.emerald_date_filter_date_range,
                        startDate.format(datePatternString), endDate.format(datePatternString))
            }
        }
    }

    private fun setupPersonalizedLayout(shouldShowLayout: Boolean = true) {
        emeraldDateFilterExpandConstraint.hide()
        emeraldTextDateRangeSubtitle.show()

        if (shouldShowLayout) {
            emeraldDateFilterExpandConstraint.show()
            emeraldTextDateRangeSubtitle.hide()

            emeraldTextDateLabel.text = context.getString(EmeraldDateFilterOptions.PERSONALIZED.textResId)
            emeraldDateFilterStartDate.text = startDate.format(datePatternString)
            emeraldDateFilterEndDate.text = endDate.format(datePatternString)
        }
    }

    private fun showInvalidCustomDateFilterMessage() {
        Toast.makeText(context, context.getString(R.string.emerald_date_filter_invalid_custom_date_message),
                Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(currentFilterType, startDate, endDate, super.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        super.onRestoreInstanceState(state.superState)
        if (state.filter == EmeraldDateFilterOptions.PERSONALIZED) {
            setCustomFilter(state.startDate, state.endDate)
        } else {
            setFilter(state.filter)
        }
    }

    internal class SavedState(val filter: EmeraldDateFilterOptions,
                              val startDate: Calendar,
                              val endDate: Calendar,
                              superState: Parcelable) : View.BaseSavedState(superState)
}