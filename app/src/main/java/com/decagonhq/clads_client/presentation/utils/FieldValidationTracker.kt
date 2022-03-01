package com.decagonhq.clads_client.presentation.utils

import androidx.lifecycle.MutableLiveData
import java.util.EnumMap

object FieldValidationTracker {
    val isFieldsValidated: MutableLiveData<EnumMap<FieldType, Boolean>> = MutableLiveData()

    val fieldTypeMap: EnumMap<FieldType, Boolean> = EnumMap(FieldType::class.java)

    init {
        populateFieldTypeMap()
    }

    private fun populateFieldTypeMap() {
        fieldTypeMap[FieldType.FIRSTNAME] = false
        fieldTypeMap[FieldType.LASTNAME] = false
        fieldTypeMap[FieldType.OTHER_NAME] = false
        fieldTypeMap[FieldType.EMAIL] = false
        fieldTypeMap[FieldType.PASSWORD] = false
        fieldTypeMap[FieldType.CONFIRM_PASSWORD] = false
    }

    enum class FieldType {
        FIRSTNAME, LASTNAME, OTHER_NAME, EMAIL, PASSWORD, CONFIRM_PASSWORD, PHONE_NUMBER
    }
}
