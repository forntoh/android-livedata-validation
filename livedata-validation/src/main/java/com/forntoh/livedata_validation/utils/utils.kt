package com.forntoh.livedata_validation.utils

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun View.showError(message: String?) {
    when (this) {
        is TextInputLayout -> error = message
        is EditText -> error = message
        is CheckBox -> error = message
    }
}