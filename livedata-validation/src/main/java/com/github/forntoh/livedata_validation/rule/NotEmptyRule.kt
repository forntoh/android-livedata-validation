package com.github.forntoh.livedata_validation.rule

import androidx.annotation.StringRes

/**
 * Widely used rule, To check if user has entered input or not.
 *
 * Validate input field to be not empty
 */
class NotEmptyRule : BaseRule {

    constructor(@StringRes errorRes: Int) : super(arrayOf(errorRes))

    constructor(error: String) : super(arrayOf(error))

    /**
     * Check if text is Empty or not
     *
     * @param text String Text to validate
     * @return Boolean return true if text is not null and not blank else return false
     */
    override fun validate(text: String?) = isNonEmpty(text)

    companion object {

        /**
         * Check if text is Empty or not
         *
         * @param text String Text to validate
         * @return Boolean return true if text is not null and not blank else return false
         */
        fun isNonEmpty(text: String?): Boolean {
            return !text.isNullOrBlank()
        }

    }

}
