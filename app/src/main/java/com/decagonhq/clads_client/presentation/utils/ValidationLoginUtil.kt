package com.decagonhq.clads_client.presentation.utils

object ValidationLoginUtil {

     fun validateEmail(email: String) : Boolean{
        if(email.isBlank()){
            return false
        }
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)"
        return emailRegex.toRegex().matches(email);
    }


      fun validatePassword(password: String): Boolean{
        if (password.isBlank()){
            return false
        }
        return true
    }


    fun validateInputs(email: String, password: String) : Boolean{
        return validateEmail(email) && validatePassword(password)
    }
}