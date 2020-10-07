package com.forntoh.livedata_validation.rule

import androidx.lifecycle.LiveData

/**
 * Rule to check if Input text is equal to the provided text.
 *
 * This rule can be used to confirm input text.
 * e.g. Confirm Password, Confirm Email
 */
class EqualRule : BaseRule {

    private val mText: LiveData<String>

    constructor(text: LiveData<String>, errorRes: Int) : super(arrayOf(errorRes)) {
        this.mText = text
    }

    constructor(text: LiveData<String>, error: String) : super(arrayOf(error)) {
        this.mText = text
    }

    override fun validate(text: String?) = text == mText.value
}
