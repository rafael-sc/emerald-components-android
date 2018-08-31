package br.com.stone.emeraldcomponents.basic


import android.view.View

/**
 * Created by renan.silva on 18/04/2018.
 */
open class EmeraldPagerItem(val layoutId: Int, val bindValues: (layout: View) -> Unit, val title: String = "")