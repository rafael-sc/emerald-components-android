package br.com.stone.emeraldcomponents.basics

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.EmeraldButton
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created on 25/05/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */

@RunWith(RobolectricTestRunner::class)
class EmeraldButtonTest {

    lateinit var button: EmeraldButton

    @Before
    fun setup() {
        button = EmeraldButton(RuntimeEnvironment.application)
    }

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldButton(RuntimeEnvironment.application)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.emeraldButtonStyle, EmeraldButton.ButtonStyle.FILLED.ordinal.toString())
                .addAttribute(R.attr.emeraldButtonType, EmeraldButton.ButtonType.CONFIRM.ordinal.toString())
                .build()

        val view = EmeraldButton(RuntimeEnvironment.application, attrs)

        Assert.assertEquals(view.type, EmeraldButton.ButtonType.CONFIRM)
        Assert.assertEquals(view.style, EmeraldButton.ButtonStyle.FILLED)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSetInvalid() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.emeraldButtonStyle, "teste")
                .addAttribute(R.attr.emeraldButtonType, "valor invalido")
                .build()

        val view = EmeraldButton(RuntimeEnvironment.application, attrs)

        Assert.assertEquals(view.type, EmeraldButton.ButtonType.PRIMARY)
        Assert.assertEquals(view.style, EmeraldButton.ButtonStyle.FILLED)
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetType() {
        val type = EmeraldButton.ButtonType.PRIMARY
        button.setButtonType(type)
        Assert.assertEquals(type, button.type)
    }

    @Test
    fun testButtonTextColorByTypeAndStyle() {
        EmeraldButton.ButtonType.values().forEach { type ->
            EmeraldButton.ButtonStyle.values().forEach { style ->
                button.setButtonType(type)
                button.setButtonStyle(style)
                when (type) {
                    EmeraldButton.ButtonType.PRIMARY -> {
                        if (style == EmeraldButton.ButtonStyle.FILLED) {
                            Assert.assertEquals(button.textColors.defaultColor, Color.WHITE)
                        } else {
                            Assert.assertEquals(button.textColors.defaultColor,
                                    RuntimeEnvironment.application.getColor(R.color.emerald_button_primary))
                        }
                    }
                    EmeraldButton.ButtonType.CONFIRM -> {
                        if (style == EmeraldButton.ButtonStyle.FILLED) {
                            Assert.assertEquals(button.textColors.defaultColor, Color.WHITE)
                        } else {
                            Assert.assertEquals(button.textColors.defaultColor,
                                    RuntimeEnvironment.application.getColor(R.color.emerald_button_confirm))
                        }
                    }
                    EmeraldButton.ButtonType.DELETE -> {
                        if (style == EmeraldButton.ButtonStyle.FILLED) {
                            Assert.assertEquals(button.textColors.defaultColor, Color.WHITE)
                        } else {
                            Assert.assertEquals(button.textColors.defaultColor,
                                    RuntimeEnvironment.application.getColor(R.color.emerald_button_delete))
                        }
                    }
                    EmeraldButton.ButtonType.NEUTRAL -> Assert.assertEquals(button.textColors.defaultColor,
                            RuntimeEnvironment.application.getColor(R.color.emerald_button_neutral_text))
                    EmeraldButton.ButtonType.DISABLED -> Assert.assertEquals(button.textColors.defaultColor,
                            RuntimeEnvironment.application.getColor(R.color.emerald_button_disabled_text_and_icon))
                }
            }
        }
    }

    @Test
    fun testButtonColorByType() {
        var type: EmeraldButton.ButtonType
        for (int in 1 until EmeraldButton.ButtonType.values().size) {
            type = EmeraldButton.ButtonType.values()[int]
            button.setButtonType(type)
            when (type) {
                EmeraldButton.ButtonType.PRIMARY -> Assert.assertEquals(button.background,
                        RuntimeEnvironment.application.getDrawable(R.drawable.button_primary))
                EmeraldButton.ButtonType.CONFIRM -> Assert.assertEquals(button.background,
                        RuntimeEnvironment.application.getDrawable(R.drawable.button_confirm))
                EmeraldButton.ButtonType.DELETE -> Assert.assertEquals(button.background,
                        RuntimeEnvironment.application.getDrawable(R.drawable.button_delete))
                EmeraldButton.ButtonType.NEUTRAL -> Assert.assertEquals(button.background,
                        RuntimeEnvironment.application.getDrawable(R.drawable.button_neutral))
                EmeraldButton.ButtonType.DISABLED -> Assert.assertEquals(button.background as ColorDrawable,
                        ColorDrawable(RuntimeEnvironment.application.getColor(R.color.emerald_button_disabled)))
            }
        }
    }
}