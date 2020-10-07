package com.forntoh.livedata_validation.rule

/**
 * Rule to check if text length matches given length
 */
class LengthRangeRule : BaseRule {

    private var minLength: Int = 0
    private var maxLength: Int = Int.MAX_VALUE

    constructor(
        minLength: Int,
        maxLength: Int,
        vararg error: Any?
    ) : super(error) {
        this.minLength = minLength
        this.maxLength = maxLength
    }

    constructor(
        minLength: Int,
        maxLength: Int,
        error: Any?
    ) : this(minLength = minLength, maxLength = maxLength, error = arrayOf(error))

    override fun validate(text: String?): Boolean = isValidLength(text, minLength, maxLength)

    class Builder {

        private var minLength: Int = 0
        private var maxLength: Int = Int.MAX_VALUE
        private var error: Array<out Any?> = emptyArray()

        /**
         * @param error provides the error message to display. If not provided, error will be set to [emptyArray].
         */
        fun error(vararg error: Any?) = apply { this.error = error }

        /**
         * @param error provides the error message to display.
         */
        fun error(error: Any?) = apply { this.error = arrayOf(error) }

        /**
         * @param length provides minimum length. If not provided, minLength will be set to 0.
         */
        fun minLength(length: Int) = apply { this.minLength = length }

        /**
         * @param length provides max length. If not provided, minLength will be set to [Int.MAX_VALUE]
         */
        fun maxLength(length: Int) = apply { this.maxLength = length }

        fun build() = LengthRangeRule(minLength = minLength, maxLength = maxLength, error = error)
    }

    companion object {

        /**
         * Check if text length is equal to content length
         *
         * @param text String Text to validate
         * @param minLength Int provides minimum length.
         * @param maxLength Int provides maximum length.
         * @return Boolean return true if text length matches to provide lengths else return false
         */
        fun isValidLength(text: String?, minLength: Int, maxLength: Int) =
            text?.length in minLength..maxLength

    }
}