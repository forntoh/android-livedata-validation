package com.forntoh.livedata_validation.rule

/**
 * Rule to validate Email Address
 */
class EmailRule : BaseRule {

    constructor(errorRes: Int) : super(arrayOf(errorRes))

    constructor(error: String) : super(arrayOf(error))

    override fun validate(text: String?) = isEmailValid(text)

    companion object {
        /**
         * Email Regex Pattern
         */
        private const val EMAIL_PATTERN = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
                "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"


        /**
         * Check if email is valid or not
         *
         * @param email String Email TExt
         * @return Boolean return true if email is valid else return false
         */
        fun isEmailValid(email: String?) = RegexRule.validate(EMAIL_PATTERN, email)
    }
}
