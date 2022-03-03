package com.decagonhq.clads_client

import com.decagonhq.clads_client.presentation.utils.validation.FieldValidations
import org.junit.Assert
import org.junit.Test

// Unit test cases for testing possible input scenarios from the user.
class FieldsValidationTest {

    @Test
    fun `incorrect email address pattern`() {
        Assert.assertFalse(FieldValidations.verifyEmail(".hdd@jj.com"))
    }

    @Test
    fun `correct email address pattern`() {
        Assert.assertTrue(FieldValidations.verifyEmail("johnsonred@gmail.com"))
    }

    @Test
    fun `no email address`() {
        Assert.assertFalse(FieldValidations.verifyEmail(""))
    }

    @Test
    fun `incorrect phone number pattern`() {
        Assert.assertFalse(FieldValidations.verifyPhoneNumber("123457299"))
    }

    @Test
    fun `correct phone number pattern for starts 234`() {
        Assert.assertTrue(FieldValidations.verifyPhoneNumber("2348131474849"))
    }

    @Test
    fun `correct phone number pattern for with 0`() {
        Assert.assertTrue(FieldValidations.verifyPhoneNumber("07019119948"))
    }

    @Test
    fun `no phone number`() {
        Assert.assertFalse(FieldValidations.verifyPhoneNumber(""))
    }

    @Test
    fun `correct name provided`() {
        Assert.assertTrue(FieldValidations.verifyName("Joe"))
    }

    @Test
    fun `incorrect name provided`() {
        Assert.assertFalse(FieldValidations.verifyName("#yemi"))
    }

    @Test
    fun `no name`() {
        Assert.assertFalse(FieldValidations.verifyName(""))
    }

    @Test
    fun `correct account type category for tailor`() {
        Assert.assertTrue(FieldValidations.verifyAccountCategory("Tailor"))
    }

    @Test
    fun `correct account type category for weaver`() {
        Assert.assertTrue(FieldValidations.verifyAccountCategory("Weaver"))
    }

    @Test
    fun `correct account type category for client`() {
        Assert.assertTrue(FieldValidations.verifyAccountCategory("Client"))
    }
}
