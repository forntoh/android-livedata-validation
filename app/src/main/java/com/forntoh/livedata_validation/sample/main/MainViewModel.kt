package com.forntoh.livedata_validation.sample.main

import androidx.lifecycle.MutableLiveData
import com.forntoh.livedata_validation.constant.PasswordPattern
import com.forntoh.livedata_validation.rule.*
import com.forntoh.livedata_validation.sample.R
import com.forntoh.livedata_validation.validation.ValidatorViewModel

class MainViewModel : ValidatorViewModel() {

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val haveAcceptTerms = MutableLiveData(false)

    override fun validate() {
        validator
            .addField(firstName, R.id.firstNameEt, NotEmptyRule("First name required"))
            .addField(lastName, R.id.lastNameEt, NotEmptyRule("Last name required"))
            .addField(
                phoneNumber, R.id.phoneNumberEt,
                NumberRule("Must be numbers"),
                NotEmptyRule("Please enter Phone Number"),
                LengthRangeRule.Builder()
                    .minLength(3)
                    .maxLength(10)
                    .error("Please enter valid Phone Number")
                    .build()
            )
            .addField(
                email, R.id.emailEtLyt,
                NotEmptyRule("Please enter Email"),
                EmailRule(R.string.error_invalid_email)
            )
            .addField(
                password, R.id.passwordEtLyt,
                NotEmptyRule("Please enter Password"),
                PasswordRule(PasswordPattern.ALPHA_NUMERIC, "Please provide strong Password")
            )
            .addField(
                confirmPassword,
                R.id.confirmPasswordEtLyt,
                NotEmptyRule("Please enter Password"),
                EqualRule(password, "Password and Confirm password must match")
            )
            .addField(haveAcceptTerms, R.id.termsOfUseCB, CheckedRule("La la la"))
    }

}