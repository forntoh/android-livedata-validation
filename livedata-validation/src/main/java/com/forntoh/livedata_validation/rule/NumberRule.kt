package com.forntoh.livedata_validation.rule

/**
 * Rule to check if input text is valid number or not
 */
class NumberRule : BaseRule {

    constructor(errorRes: Int) : super(arrayOf(errorRes))

    constructor(error: String) : super(arrayOf(error))

    override fun validate(text: String?) = text?.toDoubleOrNull() != null

}
