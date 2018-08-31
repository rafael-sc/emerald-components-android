package br.com.stone.emeraldcomponents.extension

import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 04/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class ViewGroupTest {

    @Test
    fun testGetAllChildren() {
        val context = RuntimeEnvironment.application
        val viewgroup: ViewGroup = LinearLayout(context)
        val editText = EditText(context)
        val textView = TextView(context)

        viewgroup.addView(editText)
        viewgroup.addView(textView)

        val resultList = viewgroup.getAllChildren()

        assertTrue("Edit Text not found", resultList.contains(editText))
        assertTrue("Text view not found", resultList.contains(textView))
    }
}