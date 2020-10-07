![Title](https://github.com/forntoh/android-livedata-validation/blob/master/img/title.png?raw=true)

<br>

[![Download](https://img.shields.io/bintray/v/forntoh/maven/android-livedata-validation?label=Download) ](https://bintray.com/forntoh/maven/android-livedata-validation/_latestVersion)
[![Releases](https://img.shields.io/github/release/forntoh/android-livedata-validation/all.svg)](https://github.com/forntoh/android-livedata-validation/releases)
![Bintray](https://img.shields.io/bintray/dt/forntoh/maven/android-livedata-validation?label=Downloads)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)
[![PRWelcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/forntoh/android-livedata-validation)
[![Twitter](https://img.shields.io/twitter/url/https/github.com/forntoh/android-livedata-validation.svg?style=social)](https://twitter.com/intent/tweet?text=Checkout%20the%20UI%20livedata%20validation%20library%20for%20Android.%20https%3A%2F%2Fgithub.com%2Fforntoh%2Fandroid-livedata-validation)

DataBinding LiveData validation library for Android. For use with **TextView** **EditText** **AppCompatEditText** **TextInputEditText** **TextInputLayout** and **CheckBox**

# 🔽 Getting started

## Download

Gradle:

```gradle
implementation 'com.forntoh:android-livedata-validation:1.1.0'
```

Maven:

```maven
<dependency>
  <groupId>com.forntoh</groupId>
  <artifactId>android-livedata-validation</artifactId>
  <version>1.1.0</version>
  <type>pom</type>
</dependency>
```

## Usage

### **1. Make your ViewModel to extend `ValidatorViewModel**

```kotlin
class MainViewModel : ValidatorViewModel() {
  ...
  val firstName = MutableLiveData("")
  val lastName = MutableLiveData("")
  ...
```

### **2. Initialize the validator in your Fragment/Activity**

```kotlin
LiveDataValidator(requireContext()).observe {
    lifecycleOwner(viewLifecycleOwner)
    viewModel(viewModel)
    attachTo(binding.root)
}
```

### **3. Override the validate function**

Add rules to the validator

```kotlin
/****
  ** Add LiveData and Rules to widget
  *
  * @param data    LiveData holding the info to be validated
  * @param viewId  Widget ID on which validation will be applied
  * @param rule    Rules to applied on Widget data
  */
fun <T> addField(data: LiveData<T>, @IdRes viewId: Int, vararg rule: BaseRule)
```

example

```kotlin
override fun validate() {
    validator
        .addField(firstName, R.id.firstNameEt, NotEmptyRule("First name required"))
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
        .addField(haveAcceptTerms, R.id.termsOfUseCB, CheckedRule("Accept terms of use"))
}
```

### **4. Bind fields to layout**

```xml
android:text="@={viewmodel.firstName}"
```

You can also bind the state of the validation to a button by doing the following

```
android:enabled="@{viewmodel.validator.isDataValid}"
```

# Available Rules📏:

## ✔ CheckedRule _(Used with CheckBox only)_

```kotlin
// haveAcceptTerms: MutalbeLiveData<Boolean>
addField(haveAcceptTerms, R.id.termsOfUseCB, CheckedRule("Accept terms of use"))
```

## 📧 EmailRule

```kotlin
// email: MutalbeLiveData<String>
addField(email, R.id.emailEtLyt, EmailRule("Please enter valid Email"))
```

## ⏸ EqualRule

```kotlin
// confirmPassword,password: MutalbeLiveData<String>
addField(confirmPassword, R.id.confirmPasswordEtLyt, EqualRule(password, "Password and Confirm password must match"))
```

## 🔢 NumberRule

```kotlin
// phoneNumber: MutalbeLiveData<String>
addField(phoneNumber, R.id.phoneNumberEt, NumberRule("Must be numbers"))
```

## 🚮 NonEmptyRule

```kotlin
// firstName: MutalbeLiveData<String>
addField(firstName, R.id.firstNameEt, NotEmptyRule("First name required"))
```

## 🔑 PasswordRule

```kotlin
// Password can have alphanumeric and symbol characters
addField(password, R.id.passwordEtLyt, PasswordRule("Please provide strong Password"))
```

```kotlin
// Password should be alphanumeric only eg abc123
addField(password, R.id.passwordEtLyt, PasswordRule(PasswordPattern.ALPHA_NUMERIC, "Please provide strong Password"))
```

## #️⃣ RegexRule

```kotlin
addField(usernameEditText, RegexRule(RegexRule.USERNAME_PATTERN, "Please enter valid Username"))
```

```kotlin
addField(usernameEditText, RegexRule("^[a-zA-Z0-9_-]{3,16}",  "Please enter valid Username"))
```

### Let us know!

Please kindly send us links to your projects which make use of this library. Just send an email to **thomasforntoh@gmail.com** And let us know if you have any questions or suggestion regarding the library.

## License

    Copyright 2020, Forntoh Thomas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
