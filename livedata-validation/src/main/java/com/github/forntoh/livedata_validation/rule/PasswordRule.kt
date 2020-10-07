package com.github.forntoh.livedata_validation.rule

import com.github.forntoh.livedata_validation.constant.PasswordPattern

/**
 * Validate Password, Provide PasswordPattern as per the requirement
 */
class PasswordRule : BaseRule {

    /***
     * Password Pattern, Password must have alphabets and digits both
     */
    private var mPattern: PasswordPattern = PasswordPattern.ALPHA_NUMERIC_SYMBOLS

    constructor(errorRes: Int) : super(arrayOf(errorRes))

    constructor(error: String) : super(arrayOf(error))

    constructor(pattern: PasswordPattern, errorRes: Int) : super(arrayOf(errorRes)) {
        this.mPattern = pattern
    }

    constructor(pattern: PasswordPattern, error: String) : super(arrayOf(error)) {
        this.mPattern = pattern
    }

    override fun validate(text: String?) = isValidPassword(mPattern, text)

    companion object {

        /**
         * Validate Password based on Password Patten
         *
         * @param pattern PasswordPattern
         * @param password String password text
         *
         * @return Boolean, Return true is password matched the pattern else false
         */
        private fun isValidPassword(pattern: PasswordPattern, password: String?) =
            RegexRule.validate(pattern.value, password)

    }
}
