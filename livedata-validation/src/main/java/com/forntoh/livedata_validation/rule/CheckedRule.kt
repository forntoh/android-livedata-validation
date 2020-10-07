package com.forntoh.livedata_validation.rule

/**
 * Used with CheckBox Widget, To check if widget is checked or not
 */
class CheckedRule : BaseRule {

    constructor(errorRes: Int) : super(arrayOf(errorRes))

    constructor(error: String) : super(arrayOf(error))

    /**
     * Check if input text is true of false
     *
     * @param text String value of boolean
     *
     * @return Boolean true if text is 'true' else false
     */
    override fun validate(text: String?) = text?.toBoolean() == true
}
