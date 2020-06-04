package br.com.stone.emeraldcomponents.extension

fun String.numberfy(): String {
    return filter { it.isDigit() }
}