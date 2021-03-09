package com.forntoh.livedata_validation.validation

import androidx.lifecycle.ViewModel

abstract class ValidatorViewModel : ViewModel() {

    lateinit var validator: LiveDataValidator

    open fun validate() {}
}