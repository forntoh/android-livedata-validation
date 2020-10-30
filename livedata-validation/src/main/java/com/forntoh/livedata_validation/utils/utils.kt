package com.forntoh.livedata_validation.utils

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun View.showError(message: String?) {
    when (this) {
        is TextInputLayout -> {
            if (message != null) isErrorEnabled = true
            error = message
            if (message == null) isErrorEnabled = false
        }
        is EditText -> error = message
        is CheckBox -> error = message
    }
}