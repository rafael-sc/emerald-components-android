package br.com.stone.emeraldcomponents.basic

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
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
                    setStyleProperties(R.drawable.button_neutral, R.color.emerald_button_neutral_text)
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
                when (newButtonStyle) {
                    ButtonStyle.FILLED -> {
                        setTextColor(context.colorRes(android.R.color.white))
                    }
                    ButtonStyle.OUTLINE, ButtonStyle.TEXT -> {
                        setTextColor(context.colorRes(color))
                    }
                    else -> {
                    }
                }
            }
        }

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
        when (style) {
            ButtonStyle.OUTLINE -> View.mergeDrawableStates(drawableState, stateOutline)
            ButtonStyle.TEXT -> View.mergeDrawableStates(drawableState, stateText)
            else -> {
            }
        }
        return drawableState
    }

    private fun setDefaultPadding() {
        val topBottom = context.dimen(R.dimen.button_padding_default_top_bottom).toInt()
        val sides = context.dimen(R.dimen.button_padding_default_sides).toInt()
        setPadding(sides, topBottom, sides, topBottom)
    }

    private fun setStyleProperties(color: Int, textColorResId: Int) {
        background = getBackgroundDrawable(color)
        setTextColor(context.colorRes(textColorResId))
    }

    private fun getBackgroundDrawable(color: Int): StateListDrawable {
        val states = StateListDrawable()
        val colorRes = context.colorRes(color)

        addOutlinePressedState(colorRes, states)

        states.addState(intArrayOf(R.attr.state_text),
                ContextCompat.getDrawable(context, android.R.color.transparent))

        addOutlineState(colorRes, states)

        addPressedState(states, color)

        states.addState(intArrayOf(),
                ContextCompat.getDrawable(context, color))

        return states
    }

    private fun addOutlineState(colorRes: Int, states: StateListDrawable) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_border)
                ?.mutate() as? GradientDrawable
        drawable?.setStroke(context.dimen(R.dimen.button_border_width).toInt(), colorRes)
        states.addState(intArrayOf(R.attr.state_outline), drawable)
    }

    private fun addOutlinePressedState(colorRes: Int, states: StateListDrawable) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_border)
                ?.mutate() as? GradientDrawable
        drawable?.setStroke(context.dimen(R.dimen.button_border_width).toInt(), colorRes)
        drawable?.setColor(context.colorRes(R.color.emerald_button_transparent_10percent_opacity))
        states.addState(intArrayOf(android.R.attr.state_pressed, R.attr.state_outline), drawable)
    }

    private fun addPressedState(states: StateListDrawable, color: Int) {
        val colorDrawable = ContextCompat.getDrawable(context, color)?.mutate()
        val blackLayer = ContextCompat.getDrawable(context, R.color.emerald_button_transparent_20percent_opacity)?.mutate()
        val layers = LayerDrawable(arrayOf(colorDrawable, blackLayer))
        states.addState(intArrayOf(android.R.attr.state_pressed), layers)
    }
}