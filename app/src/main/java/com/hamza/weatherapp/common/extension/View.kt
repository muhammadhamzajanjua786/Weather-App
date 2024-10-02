package com.hamza.weatherapp.common.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible

inline fun View.onSafeClick(delayInClick: Long = 500L, crossinline listener: (View) -> Unit) {
    val enableAgain = Runnable { isEnabled = true }
    setOnClickListener {
        if (isEnabled) {
            isEnabled = false
            postDelayed(enableAgain, delayInClick)
            listener(it)
        }
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleOrInvisible() {
    if (this.isVisible) this.invisible() else this.visible()
}

fun View.showKeyboard() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
    this.clearFocus()
}