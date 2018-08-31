package br.com.stone.emeraldcomponents.basic.forms

import android.view.ViewGroup
import br.com.stone.emeraldcomponents.basic.input.SelfValidatorField
import br.com.stone.emeraldcomponents.extension.getAllChildren

/**
 * Created by renan.silva on 04/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
object FormController {

    fun validateFields(parentView: ViewGroup): Boolean {
        var isValid = true
        val childList = parentView.getAllChildren()
        childList.forEach {
            if (it is SelfValidatorField) {
                isValid = isValid && it.isValid()
            }
        }
        return isValid
    }
}