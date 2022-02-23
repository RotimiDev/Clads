package com.decagonhq.clads_client.presentation.utils

import androidx.core.widget.doAfterTextChanged
import com.decagonhq.clads_client.presentation.utils.FieldValidationTracker.FieldType
import com.decagonhq.clads_client.presentation.utils.FieldValidationTracker.fieldTypeMap
import com.google.android.material.textfield.TextInputLayout

inline fun TextInputLayout.validateField(
    errorMessage: String,
    fieldType: FieldType,
    crossinline action: (String) -> Boolean
) {
    this.editText?.doAfterTextChanged {
        if (!action.invoke(it.toString().trim())) {
            this.error = errorMessage
            fieldTypeMap[fieldType] = false
            FieldValidationTracker.isFieldsValidated.value = fieldTypeMap
        } else {
            this.error = null
            fieldTypeMap[fieldType] = true
            FieldValidationTracker.isFieldsValidated.value = fieldTypeMap
        }
    }
}

fun TextInputLayout.validateConfirmPassword(
    passwordInputLayout: TextInputLayout,
    fieldType: FieldType,
    errorMessage: String,
) {
    this.editText?.doAfterTextChanged {
        if (!RegistrationUtil.validateConfirmPassword(
                it.toString().trim(),
                passwordInputLayout.editText?.text.toString().trim()
            )
        ) {
            this.error = errorMessage
            fieldTypeMap[fieldType] = false
            FieldValidationTracker.isFieldsValidated.value = fieldTypeMap
        } else {
            this.error = null
            fieldTypeMap[fieldType] = true
            FieldValidationTracker.isFieldsValidated.value = fieldTypeMap
        }
    }
}
