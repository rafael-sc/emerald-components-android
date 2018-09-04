package br.com.stone.emeraldcomponents.common

import org.junit.Assert
import org.junit.Test

/**
 * Created by lucasdiego on 03/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class UtilValidatorTest {

    @Test
    fun testEmailValidator() {
        Assert.assertTrue(UtilValidator.isEmailValid("email@email.com"))
        Assert.assertFalse(UtilValidator.isEmailValid(" "))
        Assert.assertFalse(UtilValidator.isEmailValid(""))
        Assert.assertFalse(UtilValidator.isEmailValid("adsfasdf@fasd"))
    }
}