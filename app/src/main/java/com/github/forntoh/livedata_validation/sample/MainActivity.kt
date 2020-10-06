package com.github.forntoh.livedata_validation.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Library Demo
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 28 March 2020
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    fun onSubmitClick(view: View) {
        if (isValidForm()) {
            Toast.makeText(view.context, "Submit Form", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidForm(): Boolean {
        /*return LiveDataValidator(this)
            .addField(firstNameEt, NonEmptyRule(R.string.error_empty_first_name))
            .addField(lastNameEt, NonEmptyRule(R.string.error_empty_last_name))
            .addField(
                emailEtLyt,
                NonEmptyRule("Please enter Email"),
                EmailRule(R.string.error_invalid_email)
            )
            .addField(
                passwordEtLyt,
                NonEmptyRule("Please enter Password"),
                PasswordRule(PasswordPattern.ALPHA_NUMERIC, "Please provide strong Password")
            )
            .addField(
                confirmPasswordEtLyt,
                NonEmptyRule("Please enter Password"),
                EqualRule(
                    confirmPasswordEt.text.toString(),
                    "Password and Confirm password must match"
                )
            )
            .addField(
                phoneNumberEt,
                NonEmptyRule("Please enter Phone Number"),
                LengthRule(10, "Please enter valid Phone Number")
            )
            .addField(
                termsOfUseCB,
                CheckedRule("Accept terms of use")
            )
            .setErrorListener {
                for (error in it) {
                    if (error.view is CheckBox) {
                        (error.view as CheckBox).error = null
                        Toast.makeText(applicationContext, error.error, Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Error", error.toString())
                }
            }
            .validate()*/
        return true
    }
}
