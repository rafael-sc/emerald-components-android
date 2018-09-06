package br.com.stone.emeraldcomponents.basic.lifecycleowner

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry

/**
 * Created by renan.silva on 02/02/2018.
 */
interface LifecycleOwnerView : LifecycleOwner {

    val mLifecycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    fun onDestroyCalled() {
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    fun onCreateCalled() {
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }
}