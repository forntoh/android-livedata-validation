package com.github.forntoh.livedata_validation.validation

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.forntoh.livedata_validation.rule.BaseRule
import com.github.forntoh.livedata_validation.utils.showError
import java.math.BigDecimal
import kotlin.collections.set

class LiveDataValidator constructor(private val context: Context) {

    /**
     * Map View with Rules
     */
    private val mValidationFields = LinkedHashMap<Int, List<BaseRule>>()

    /**
     * Map Data with view
     */
    private val mValidationData = LinkedHashMap<Int, LiveData<*>>()

    /**
     * [InputError] observer
     * In-charge of showing the error on the view
     */
    private var observer: Observer<InputError> = Observer { inputError ->
        val v = mRoot?.findViewById<View>(inputError.view)
        v?.showError(inputError.error)
    }

    private val errors = LinkedHashMap<Int, Boolean>()

    private var mRoot: View? = null

    private var mViewModel: ValidatorViewModel? = null

    private var mLifecycleOwner: LifecycleOwner? = null

    private val _isDataValid: MutableLiveData<Boolean> = MutableLiveData()
    val isDataValid: LiveData<Boolean> = _isDataValid

    fun observe() {
        validate()
        if (mLifecycleOwner == null) mViewModel?.inputError?.observeForever(observer)
        else mViewModel?.inputError?.observe(mLifecycleOwner!!, observer)
    }

    /**
     * Add UI widget and its Rules
     *
     * @param viewId Android Widget on which validation will be applied
     * @param rule Rules to applied on Widget data
     */
    fun <T> addField(
        data: LiveData<T>,
        @IdRes viewId: Int,
        vararg rule: BaseRule
    ) = apply {
        mValidationFields[viewId] = rule.toList()
        mValidationData[viewId] = data
    }

    private fun validate() {
        mViewModel?.validator = this
        mViewModel?.validate()

        for (field in mValidationFields) {

            val dataObserver: Observer<Any> = Observer { data ->
                errors[field.key] = applyValidation(field.key, getText(data)).isEmpty()
                _isDataValid.postValue(!errors.containsValue(false))
            }

            val data = mValidationData[field.key]

            if (mLifecycleOwner == null) data?.observeForever(dataObserver)
            else data?.observe(mLifecycleOwner!!, dataObserver)
        }
    }

    /**
     * Apply Validation
     *
     * @param viewId Android Widget on which validation will be applied
     * @param text String to validate
     */
    private fun applyValidation(viewId: Int, text: String?): ArrayList<InputError> {
        val rules = mValidationFields[viewId]
        val errors = ArrayList<InputError>()

        if (rules != null) {
            for (rule in rules) if (!rule.validate(text)) {
                errors.add(InputError(viewId, rule.getError(context)))
                break
            }
            if (errors.isEmpty()) setError(viewId, null)
        }
        setErrors(errors)
        return errors
    }

    /**
     * Get text from LiveData value
     *
     * @param data LiveData value
     * @return String text gotten from LiveData
     */
    private fun <T> getText(data: T?): String? = when (data) {
        is String -> data
        is BigDecimal -> data.toPlainString()
        null -> ""
        else -> data.toString()
    }

    /**
     * Set error on UI Widget
     */
    private fun setErrors(validations: List<InputError>) {
        for (validation in validations)
            setError(validation.view, validation.error)
    }

    /**
     * Set Error on View
     *
     * @param view View UI Widget
     * @param error String error to set on UI Widget
     */
    private fun setError(view: Int, error: String?) {
        mViewModel?.inputError?.postValue(InputError(view, error))
    }

    /**
     * Set the root view
     *
     * @param root Root layout
     */
    fun attachTo(root: View) = apply { mRoot = root }

    /**
     * Add the Fragment's/Activity's lifecycle owner in the validator
     *
     * @param lifecycleOwner Fragment/Activity lifecycle owner
     */
    fun lifecycleOwner(lifecycleOwner: LifecycleOwner) = apply { mLifecycleOwner = lifecycleOwner }

    /**
     * Set the ValidatorViewModel
     *
     * @param viewModel Concerned ValidatorViewModel
     */
    fun viewModel(viewModel: ValidatorViewModel) = apply { mViewModel = viewModel }

    /**
     * Start observing changes
     */
    inline fun observe(func: LiveDataValidator.() -> Unit) = apply {
        this.func()
        this.observe()
    }

}