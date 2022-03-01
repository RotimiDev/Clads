package com.decagonhq.clads_client

import com.decagonhq.clads_client.presentation.utils.validation.FieldsValidation
import org.junit.Assert
import org.junit.Test

// Unit test cases for testing possible input scenarios from the user.
class FieldsValidationTest {

    @Test
    fun `incorrect email address pattern`() {
        Assert.assertFalse(FieldsValidation.verifyEmail(".hdd@jj.com"))
    }

    @Test
    fun `correct email address pattern`() {
        Assert.assertTrue(FieldsValidation.verifyEmail("johnsonred@gmail.com"))
    }

    @Test
    fun `no email address`() {
        Assert.assertFalse(FieldsValidation.verifyEmail(""))
    }

    @Test
    fun `incorrect phone number pattern`() {
        Assert.assertFalse(FieldsValidation.verifyPhoneNumber("123457299"))
    }

    @Test
    fun `correct phone number pattern for starts 234`() {
        Assert.assertTrue(FieldsValidation.verifyPhoneNumber("2348131474849"))
    }

    @Test
    fun `correct phone number pattern for with 0`() {
        Assert.assertTrue(FieldsValidation.verifyPhoneNumber("07019119948"))
    }

    @Test
    fun `no phone number`() {
        Assert.assertFalse(FieldsValidation.verifyPhoneNumber(""))
    }

    @Test
    fun `correct name provided`() {
        Assert.assertTrue(FieldsValidation.verifyName("Joe"))
    }

    @Test
    fun `incorrect name provided`() {
        Assert.assertFalse(FieldsValidation.verifyName("#yemi"))
    }

    @Test
    fun `no name`() {
        Assert.assertFalse(FieldsValidation.verifyName(""))
    }

    @Test
    fun `correct account type category for tailor`() {
        Assert.assertTrue(FieldsValidation.verifyAccountCategory("Tailor"))
    }

    @Test
    fun `correct account type category for weaver`() {
        Assert.assertTrue(FieldsValidation.verifyAccountCategory("Weaver"))
    }

    @Test
    fun `correct account type category for client`() {
        Assert.assertTrue(FieldsValidation.verifyAccountCategory("Client"))
    }
}
