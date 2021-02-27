package com.geermank.common.extensions

import android.view.View

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.remove() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}