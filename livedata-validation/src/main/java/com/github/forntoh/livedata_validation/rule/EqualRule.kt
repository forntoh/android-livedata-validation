package com.github.forntoh.livedata_validation.rule

/**
 * Rule to check if Input text is equal to the provided text.
 *
 * This rule can be used to confirm input text.
 * e.g. Confirm Password, Confirm Email
 */
class EqualRule : BaseRule {

    private var mText: String? = null

    constructor(text: String?, errorRes: Int) : super(arrayOf(errorRes)) {
        this.mText = text
    }

    constructor(text: String?, error: String) : super(arrayOf(error)) {
        this.mText = text
    }

    override fun validate(text: String?) = text == mText
}
