package com.decagonhq.clads_client

import com.decagonhq.clads_client.presentation.ui.utils.RegistrationUtil
import org.junit.Assert
import org.junit.Test

// Unit test cases for testing possible input scenarios from the user.
class RegistrationUtilTest {

    @Test
    fun `incorrect email address pattern`() {
        Assert.assertFalse(RegistrationUtil.verifyEmail(".hdd@jj.com"))
    }

    @Test
    fun `correct email address pattern`() {
        Assert.assertTrue(RegistrationUtil.verifyEmail("johnsonred@gmail.com"))
    }

    @Test
    fun `no email address`() {
        Assert.assertFalse(RegistrationUtil.verifyEmail(""))
    }

    @Test
    fun `incorrect phone number pattern`() {
        Assert.assertFalse(RegistrationUtil.verifyPhoneNumber("123457299"))
    }

    @Test
    fun `correct phone number pattern for starts 234`() {
        Assert.assertTrue(RegistrationUtil.verifyPhoneNumber("2348131474849"))
    }

    @Test
    fun `correct phone number pattern for with 0`() {
        Assert.assertTrue(RegistrationUtil.verifyPhoneNumber("07019119948"))
    }

    @Test
    fun `no phone number`() {
        Assert.assertFalse(RegistrationUtil.verifyPhoneNumber(""))
    }

    @Test
    fun `correct name provided`() {
        Assert.assertTrue(RegistrationUtil.verifyName("Joe"))
    }

    @Test
    fun `incorrect name provided`() {
        Assert.assertFalse(RegistrationUtil.verifyName("#yemi"))
    }

    @Test
    fun `no name`() {
        Assert.assertFalse(RegistrationUtil.verifyName(""))
    }

    @Test
    fun `correct account type category for tailor`() {
        Assert.assertTrue(RegistrationUtil.verifyAccountCategory("Tailor"))
    }

    @Test
    fun `correct account type category for weaver`() {
        Assert.assertTrue(RegistrationUtil.verifyAccountCategory("Weaver"))
    }

    @Test
    fun `correct account type category for client`() {
        Assert.assertTrue(RegistrationUtil.verifyAccountCategory("Client"))
    }
}
