package br.com.stone.emeraldcomponents.basic

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponents.extension.dimen

/**
 * Created on 25/05/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
class EmeraldButton : AppCompatButton {

    private var color: Int = R.color.emerald_button_primary

    var type: ButtonType = ButtonType.PRIMARY
        set(newButtonType) {
            field = newButtonType
            isClickable = true
            when (newButtonType) {
                ButtonType.PRIMARY -> {
                    color = R.color.emerald_button_primary
                    setStyleProperties(color, android.R.color.white)
                }
                ButtonType.CONFIRM -> {
                    color = R.color.emerald_button_confirm
                    setStyleProperties(color, android.R.color.white)
                }
                ButtonType.DELETE -> {
                    color = R.color.emerald_button_delete
                    setStyleProperties(color, android.R.color.white)
                }
                ButtonType.NEUTRAL -> {
                    setStyleProperties(android.R.color.white, R.color.emerald_button_neutral_text)
                }
                ButtonType.DISABLED -> {
                    setStyleProperties(R.color.emerald_button_disabled, R.color.emerald_button_disabled_text_and_icon)
                    isClickable = false
                }
            }
        }

    var style: ButtonStyle? = ButtonStyle.FILLED
        set(newButtonStyle) {
            field = newButtonStyle
            if (type != ButtonType.NEUTRAL && type != ButtonType.DISABLED) {
                if (newButtonStyle == ButtonStyle.FILLED) {
                    setTextColor(context.colorRes(android.R.color.white))
                }
                if (newButtonStyle == ButtonStyle.OUTLINE || newButtonStyle == ButtonStyle.TEXT) {
                    setTextColor(context.colorRes(color))
                }
            }
        }

    private var radius = 0f

    enum class ButtonType {
        PRIMARY,
        CONFIRM,
        DELETE,
        NEUTRAL,
        DISABLED
    }

    enum class ButtonStyle {
        FILLED,
        OUTLINE,
        TEXT
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    init {
        setAllCaps(false)
        setDefaultPadding()
    }

    private fun setAttributes(attributeSet: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attributeSet, R.styleable.EmeraldButton, 0, 0)

        radius = args.getFloat(R.styleable.EmeraldButton_emeraldButtonRadius, 0f)
        type = EmeraldButton.ButtonType.values()[
                args.getInt(R.styleable.EmeraldButton_emeraldButtonType, EmeraldButton.ButtonType.PRIMARY.ordinal)]
        style = EmeraldButton.ButtonStyle.values()[
                args.getInt(R.styleable.EmeraldButton_emeraldButtonStyle, EmeraldButton.ButtonStyle.FILLED.ordinal)]

        args.recycle()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val stateOutline = intArrayOf(R.attr.state_outline)
        val stateText = intArrayOf(R.attr.state_text)

        val drawableState = super.onCreateDrawableState(extraSpace + 2)

        var selectedStates: IntArray = intArrayOf()
        if (style == ButtonStyle.OUTLINE) selectedStates = stateOutline
        else if (style == ButtonStyle.TEXT) selectedStates = stateText

        View.mergeDrawableStates(drawableState, selectedStates)
        return drawableState
    }

    private fun setDefaultPadding() {
        val topBottom = context.dimen(R.dimen.button_padding_default_top_bottom).toInt()
        val sides = context.dimen(R.dimen.button_padding_default_sides).toInt()
        setPadding(sides, topBottom, sides, topBottom)
    }

    fun setStyleProperties(backgroundColorRes: Int, textColorRes: Int, radius: Float = this.radius) {
        val dpRadiusValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, resources.displayMetrics)
        background = getBackgroundDrawable(backgroundColorRes, dpRadiusValue)
        setTextColor(context.colorRes(textColorRes))
    }

    private fun getBackgroundDrawable(color: Int, radius: Float): StateListDrawable {
        val states = StateListDrawable()
        val colorRes = context.colorRes(color)

        addOutlinePressedState(colorRes, states, radius)

        states.addState(intArrayOf(R.attr.state_text),
                ContextCompat.getDrawable(context, android.R.color.transparent))

        addOutlineState(colorRes, states, radius)

        addPressedState(states, color, radius)

        addDefaultState(states, color, radius)

        return states
    }

    private fun addDefaultState(states: StateListDrawable, color: Int, radius: Float) {
        val colorRes = context.colorRes(color)
        val drawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorRes, colorRes))
        drawable.cornerRadius = radius
        states.addState(intArrayOf(), drawable)
    }

    private fun addPressedState(states: StateListDrawable, color: Int, radius: Float) {
        val colorRes = context.colorRes(color)
        val colorDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorRes, colorRes))
        colorDrawable.cornerRadius = radius

        val blackLayerColorRes = context.colorRes(R.color.emerald_button_transparent_20percent_opacity)
        val blackLayer = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(blackLayerColorRes, blackLayerColorRes))
        blackLayer.cornerRadius = radius

        val layers = LayerDrawable(arrayOf(colorDrawable, blackLayer))
        states.addState(intArrayOf(android.R.attr.state_pressed), layers)
    }

    private fun addOutlineState(colorRes: Int, states: StateListDrawable, radius: Float) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_border)
                ?.mutate() as? GradientDrawable
        drawable?.cornerRadius = radius
        drawable?.setStroke(context.dimen(R.dimen.button_border_width).toInt(), colorRes)
        states.addState(intArrayOf(R.attr.state_outline), drawable)
    }

    private fun addOutlinePressedState(colorRes: Int, states: StateListDrawable, radius: Float) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_border)
                ?.mutate() as? GradientDrawable
        drawable?.cornerRadius = radius
        drawable?.setStroke(context.dimen(R.dimen.button_border_width).toInt(), colorRes)
        drawable?.setColor(context.colorRes(R.color.emerald_button_transparent_10percent_opacity))
        states.addState(intArrayOf(android.R.attr.state_pressed, R.attr.state_outline), drawable)
    }
}