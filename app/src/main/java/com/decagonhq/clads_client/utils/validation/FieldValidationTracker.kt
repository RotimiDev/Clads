package com.decagonhq.clads_client.utils.validation

import androidx.lifecycle.MutableLiveData
import java.util.EnumMap

object FieldValidationTracker {
    val isFieldsValidated: MutableLiveData<EnumMap<FieldType, Boolean>> = MutableLiveData()

    val fieldTypeMap: EnumMap<FieldType, Boolean> = EnumMap(FieldType::class.java)

    fun populateFieldTypeMap(fieldTypes: List<FieldType>) {
        clearFieldTypeMap()
        for (fieldType in fieldTypes) {
            fieldTypeMap[fieldType] = false
        }
    }

    private fun clearFieldTypeMap() {
        fieldTypeMap.clear()
    }

    enum class FieldType {
        FIRSTNAME, LASTNAME, OTHER_NAME, EMAIL, PASSWORD, CONFIRM_PASSWORD, PHONE_NUMBER
    }
}
