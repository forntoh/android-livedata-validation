package com.forntoh.livedata_validation.rule

import android.content.Context

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
            if (m is String) text = m
            else if (m is Int) text = context.getString(m)
            text
        } ?: ""
    }

    /**
     * Validate text input based on Rule
     */
    abstract fun validate(text: String?): Boolean
}