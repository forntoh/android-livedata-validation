package com.forntoh.livedata_validation.rule

import androidx.lifecycle.LiveData
import com.forntoh.livedata_validation.constant.Is

/**
 * Rule to check how input text compares to a number
 */
class NumberCompareRule : BaseRule {

    private val compare: Is
    private val number: LiveData<Number>

    constructor(
        compare: Is,
        number: LiveData<Number>,
        vararg error: Any?
    ) : super(error) {
        this.compare = compare
        this.number = number
    }

    constructor(
        compare: Is,
        number: LiveData<Number>,
        error: Any?
    ) : this(compare = compare, number = number, error = arrayOf(error))

    override fun validate(text: String?): Boolean = isGreaterThan(text, number.value, compare)

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

    }
}