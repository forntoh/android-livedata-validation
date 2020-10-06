package com.github.forntoh.livedata_validation.utils

import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun View.showError(message: String?) {
    if (this is TextInputLayout) error = message
    else if (this is EditText) error = message
}