package br.com.stone.emeraldcomponents.common

import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import stone.com.br.basiccomponents.common.ParentActivityException

/**
 * Created by renan.silva on 19/04/2018.
 */
class ParentActivityExceptionTest {

    @get:Rule
    var thrown = ExpectedException.none()

    @Test
    fun testThrowDefaultMessage() {
        thrown.expect(ParentActivityException::class.java)
        thrown.expectMessage("Parent Activity not found. Activity should extend FragmentActivity")
        throw ParentActivityException()
    }

    @Test
    fun testThrowCustomMessage() {
        val message = "test"
        thrown.expect(ParentActivityException::class.java)
        thrown.expectMessage(message)
        throw ParentActivityException(message)
    }
}