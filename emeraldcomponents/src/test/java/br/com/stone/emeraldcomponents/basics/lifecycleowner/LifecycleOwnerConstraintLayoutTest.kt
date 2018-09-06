package br.com.stone.emeraldcomponents.basics.lifecycleowner

import android.content.Context
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.basic.EmeraldButton
import br.com.stone.emeraldcomponents.basic.lifecycleowner.LifecycleOwnerConstraintLayout
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 06/09/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class LifecycleOwnerConstraintLayoutTest {

    @Test
    fun testInstanceWithContext() {
        val view = LifecycleOwnerConstraintLayoutImpl(RuntimeEnvironment.application)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet().build()
        val view = EmeraldButton(RuntimeEnvironment.application, attrs)
        Assert.assertNotNull(view)
    }

    internal class LifecycleOwnerConstraintLayoutImpl : LifecycleOwnerConstraintLayout {
        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    }
}