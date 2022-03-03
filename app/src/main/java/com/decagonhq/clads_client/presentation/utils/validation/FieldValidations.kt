package com.decagonhq.clads_client.presentation.utils.validation

// Validation object for Sign-Up
object FieldValidations {

    // Function to verify the name of the intended user
    fun verifyName(name: String): Boolean {
        val regex = Regex("^[a-zA-Z]*\$")
        return name.isNotBlank() && name.matches(regex) && name.length >= 2
    }

    // Function to verify the e-mail of the intended user
    fun verifyEmail(email: String): Boolean {
        val regex = Regex("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
        return email.isNotBlank() && email.matches(regex)
    }

    // Function to verify the account category of the intended user
    fun verifyAccountCategory(accountCategory: String): Boolean {
        return accountCategory.isNotBlank()
    }

    // Function to verify the phone-number of the intended user
    fun verifyPhoneNumber(number: String): Boolean {
        val regex = Regex("^(234|0)(8([01])|(9([01]))|([7])([0]))\\d{8}$")
        return number.isNotBlank() && number.matches(regex)
    }

    // Function to verify the country name of the intended user
    fun verifyCountryName(countryName: String): Boolean {
        return countryName.isNotBlank()
    }

    // Function to verify the password of the intended user
    fun verifyPassword(password: String): Boolean {
        val regex = Regex(
            "^" +
                "(?=.*[@#$%^&+=])" + // at least 1 special character
                "(?=\\S+$)" + // no white spaces
                ".{6,}" + // at least 6 characters
                "$"
        )
        return password.isNotBlank() && password.matches(regex)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && password.isNotBlank()
    }
}
