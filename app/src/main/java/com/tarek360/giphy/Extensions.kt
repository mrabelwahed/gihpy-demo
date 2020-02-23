package com.tarek360.giphy

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.longToast(message: CharSequence) {
    showLongToast(this, message)
}

fun Fragment.longToast(message: CharSequence) {
    context?.run { showLongToast(this, message) }
}

fun Resources.isLandscape(): Boolean {
    val orientation = configuration.orientation
    return orientation == Configuration.ORIENTATION_LANDSCAPE
}

private fun showLongToast(context: Context, message: CharSequence) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    var firstChange = true
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            if (!firstChange) {
                afterTextChanged.invoke(editable.toString())
            } else {
                firstChange = false
            }
        }
    })
}

fun Any?.exhaustive() = Unit
