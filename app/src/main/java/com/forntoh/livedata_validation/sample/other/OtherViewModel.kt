package com.forntoh.livedata_validation.sample.other

import androidx.lifecycle.MutableLiveData
import com.forntoh.livedata_validation.constant.Is
import com.forntoh.livedata_validation.rule.FunctionRule
import com.forntoh.livedata_validation.rule.LengthRangeRule
import com.forntoh.livedata_validation.rule.NotEmptyRule
import com.forntoh.livedata_validation.rule.NumberCompareRule
import com.forntoh.livedata_validation.sample.R
import com.forntoh.livedata_validation.validation.ValidatorViewModel

class OtherViewModel : ValidatorViewModel() {

    val balance = MutableLiveData("")
    val amount = MutableLiveData("")
    val number = MutableLiveData("")
    val function = MutableLiveData("")

    override fun validate() {
        validator
            .addField(
                balance,
                R.id.balanceTIL,
                NumberCompareRule(Is.GREATER_THAN, MutableLiveData(0), "Must be above zero")
            )
            .addField(
                amount,
                R.id.amountTIL,
                NumberCompareRule(
                    Is.LESS_OR_EQUAL_TO,
                    balance,
                    amount,
                    R.string.error_amount_more_than,
                    "Balance:",
                    balance
                )
            )
            .addField(
                number, R.id.numberTIL,
                NotEmptyRule(R.string.required),
                LengthRangeRule.Builder()
                    .minLength(3)
                    .maxLength(3)
                    .error("Only 3 digit numbers allowed")
                    .build()
            )
            .addField(
                function, R.id.functionTIL,
                FunctionRule("Function returned false") {
                    return@FunctionRule it?.length ?: 0 >= 5
                }
            )
    }
}