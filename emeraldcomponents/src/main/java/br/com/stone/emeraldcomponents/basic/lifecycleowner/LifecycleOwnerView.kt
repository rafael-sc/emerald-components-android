package br.com.stone.emeraldcomponents.basic.lifecycleowner

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry

/**
 * Created by renan.silva on 02/02/2018.
 */
interface LifecycleOwnerView : LifecycleOwner {

    val lifecycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    fun onDestroyCalled() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    fun onCreateCalled() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }
}