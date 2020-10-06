package com.github.forntoh.livedata_validation.sample.ui

import androidx.lifecycle.MutableLiveData
import com.github.forntoh.livedata_validation.validation.ValidatorViewModel

class MainViewModel : ValidatorViewModel() {

    val input1 = MutableLiveData("")
    val input2 = MutableLiveData("")
    val input3 = MutableLiveData("")

    override fun validate() {
        validator
//            .addField(input1, R.id.editTextTextPersonName, EqualRule("Hello", "A beg paa"))
//            .addField(input2, R.id.editTextNumber, LengthRangeRule(3, 10, "You're not good"))
//            .addField(
//                input3,
//                R.id.editTextNumberSigned,
//                NumberCompareRule(NumberCompareRule.Is.GREATER, 10, "Less than 10")
//            )
    }

}