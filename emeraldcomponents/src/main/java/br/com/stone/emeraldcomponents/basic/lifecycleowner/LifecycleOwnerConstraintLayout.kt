package br.com.stone.emeraldcomponents.basic.lifecycleowner

import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

/**
 * Created by renan.silva on 01/02/2018.
 */
abstract class LifecycleOwnerConstraintLayout : ConstraintLayout, LifecycleOwnerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        onCreateCalled()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onDestroyCalled()
    }
}