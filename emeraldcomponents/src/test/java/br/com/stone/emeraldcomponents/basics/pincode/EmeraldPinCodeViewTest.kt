package br.com.stone.emeraldcomponents.basics.pincode

import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.pinview.EmeraldPinCodeView
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmeraldPinCodeViewTest {

    @Test
    fun `test create pin code`() {
        val view = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun `test create pin item`() {
        val view = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view.createPinItem(0, false))
    }

    @Test
    fun `test correct amount of items created`() {
        val emeraldPinCode = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        val maxItems = 6
        val itemList = emeraldPinCode.createItems(maxItems, true)
        Assert.assertTrue(maxItems == itemList.size)
    }

    @Test
    fun `test paste text`() {
        val emeraldPinCode = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        val maxItems = 6
        val itemList = emeraldPinCode.createItems(maxItems, true)
        emeraldPinCode.setEditTextList(itemList)
        val textToPaste = "123456"
        emeraldPinCode.handlePasteText(textToPaste, itemList)
        Assert.assertTrue(emeraldPinCode.getCode() == textToPaste)
    }

    @Test
    fun `test paste partial text`() {
        val view = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        val maxItems = 6
        val itemList = view.createItems(maxItems, true)
        view.setEditTextList(itemList)
        val textToPaste = "1234"
        view.handlePasteText(textToPaste, itemList)

        for (index in 0 until textToPaste.length - 1)
            Assert.assertEquals(itemList[index].text.toString(),
                    textToPaste.substring(index, index + 1))

        Assert.assertTrue(view.getCode() == textToPaste)
    }
}