package br.com.stone.emeraldcomponents.basic

import android.content.Context
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

    var type: ButtonType = ButtonType.PRIMARY
        set(buttonType) {
            field = buttonType
            isClickable = true
            when (buttonType) {
                ButtonType.PRIMARY -> {
                    color = R.color.emerald_button_primary
                    drawable = R.drawable.button_primary
                    setStyleProperties(drawable, android.R.color.white)
                }
                ButtonType.CONFIRM -> {
                    color = R.color.emerald_button_confirm
                    drawable = R.drawable.button_confirm
                    setStyleProperties(drawable, android.R.color.white)
                }
                ButtonType.DELETE -> {
                    color = R.color.emerald_button_delete
                    drawable = R.drawable.button_delete
                    setStyleProperties(drawable, android.R.color.white)
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

    var style: ButtonStyle = ButtonStyle.FILLED
        set(buttonStyle) {
            field = buttonStyle
            if (type != ButtonType.NEUTRAL && type != ButtonType.DISABLED) {
                when (buttonStyle) {
                    ButtonStyle.FILLED -> {
                        isStateOutline = false
                        isStateText = false
                        setStyleProperties(drawable, android.R.color.white)
                    }
                    ButtonStyle.OUTLINE -> {
                        isStateOutline = true
                        isStateText = false
                        setTextColor(context.colorRes(color))
                    }
                    ButtonStyle.TEXT -> {
                        isStateOutline = false
                        isStateText = true
                        setTextColor(context.colorRes(color))
                    }
                }
            }
        }

    private var color: Int = R.color.emerald_button_primary
    private var drawable: Int = R.drawable.button_primary

    private var isStateOutline = false
    private var isStateText = false

    private val stateOutline = arrayOf(R.attr.state_outline)
    private val stateText = arrayOf(R.attr.state_text)

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

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
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
        val drawableState = super.onCreateDrawableState(extraSpace + 2)
        if (isStateOutline) View.mergeDrawableStates(drawableState, stateOutline.toIntArray())
        if (isStateText) View.mergeDrawableStates(drawableState, stateText.toIntArray())
        return drawableState
    }

    private fun setDefaultPadding() {
        val topBottom = context.dimen(R.dimen.button_padding_default_top_bottom).toInt()
        val sides = context.dimen(R.dimen.button_padding_default_sides).toInt()
        setPadding(sides, topBottom, sides, topBottom)
    }

    private fun setStyleProperties(backgroundColorResId: Int, textColorResId: Int) {
        setBackgroundResource(backgroundColorResId)
        setTextColor(context.colorRes(textColorResId))
    }
}