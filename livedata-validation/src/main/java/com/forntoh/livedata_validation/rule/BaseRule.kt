package com.forntoh.livedata_validation.rule

import android.content.Context
import androidx.lifecycle.LiveData

abstract class BaseRule constructor() {

    /**
     * Error message, can be either [String] or StringRes
     */
    private var error: Array<out Any?>? = null

    constructor(error: Array<out Any?>?) : this() {
        this.error = error
    }

    /**
     * Get the error message
     */
    open fun getError(context: Context): String {
        return error?.joinToString(separator = " ") { m ->
            var text = ""
            when (m) {
                is String -> text = m
                is Int -> text = context.getString(m)
                is LiveData<*> -> text = m.value.toString()
            }
            text
        } ?: ""
    }

    /**
     * Validate text input based on Rule
     */
    abstract fun validate(text: String?): Boolean
}