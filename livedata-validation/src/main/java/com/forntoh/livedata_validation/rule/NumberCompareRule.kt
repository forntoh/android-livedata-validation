package com.forntoh.livedata_validation.rule

import androidx.lifecycle.LiveData
import com.forntoh.livedata_validation.constant.Is

/**
 * Rule to check how input text compares to a number
 */
class NumberCompareRule<T> : BaseRule {

    private val compare: Is
    private val number: LiveData<T>

    constructor(
        compare: Is,
        number: LiveData<T>,
        vararg error: Any?
    ) : super(error) {
        this.compare = compare
        this.number = number
    }

    constructor(
        compare: Is,
        number: LiveData<T>,
        error: Any?
    ) : this(compare = compare, number = number, error = arrayOf(error))

    override fun validate(text: String?): Boolean =
        isGreaterThan(text, number.value.toString().toDoubleOrNull(), compare)

    companion object {

        /**
         * Check how text compares to a number
         *
         * @param text String Text to validate
         * @param number Number to compare with text
         * @param compare Comparison to make
         * @return Boolean return true if text is equal to provide length else return false
         */
        fun isGreaterThan(text: String?, number: Number?, compare: Is) =
            if (text.isNullOrBlank() || number == null) false else when (compare) {
                Is.GREATER_THAN -> text.toDouble() > number.toDouble()
                Is.GREATER_OR_EQUAL_TO -> text.toDouble() >= number.toDouble()
                Is.LESS_THAN -> text.toDouble() < number.toDouble()
                Is.LESS_OR_EQUAL_TO -> text.toDouble() <= number.toDouble()
                Is.EQUAL_TO -> text.toDouble() == number.toDouble()
            }

        /**
         * Parses the string as a [Double] number and returns the result.
         * @throws NumberFormatException if the string is not a valid representation of a number.
         */
        private fun String.toDouble(): Double =
            if (this == "-" || this.isBlank()) 0.0 else java.lang.Double.parseDouble(this)
    }
}