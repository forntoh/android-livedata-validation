package com.github.forntoh.livedata_validation.validation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ValidatorViewModel : ViewModel() {

    private val _inputError = MutableLiveData<InputError>()
    val inputError = _inputError

    lateinit var validator: LiveDataValidator

    open fun validate() {}
}