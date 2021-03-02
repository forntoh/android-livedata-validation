package com.forntoh.livedata_validation.rule

/**
 * Rule that executes a function to validate.
 *
 * This rule can be used for custom validation.
 */
class FunctionRule : BaseRule {

    private val mAction: (String?) -> Boolean

    constructor(errorRes: Int, action: (String?) -> Boolean) : super(arrayOf(errorRes)) {
        this.mAction = action
    }

    constructor(error: String, action: (String?) -> Boolean) : super(arrayOf(error)) {
        this.mAction = action
    }

    override fun validate(text: String?) = mAction(text)
}
