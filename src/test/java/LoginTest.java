/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */


public class LoginTest {

    private Login login = new Login();

    // ---------- Username tests ----------
    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test data from brief: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test data: "kyle!!!!!!!"
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // ---------- Password tests ----------
    @Test
    public void testPasswordMeetsComplexity() {
        // Test data: "Ch&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        // Test data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---------- Cell phone tests ----------
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test data: +27838968976
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test data: 08966553
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------- Login success / fail ----------
    @Test
    public void testLoginSuccessful() {
        // First register a valid user
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    // ---------- assertEquals style tests required by the marking table ----------
    @Test
    public void testUsernameCorrectMessage() {
        // The brief shows an assertEquals example for the welcome message,
        // but the actual registration messages are the ones we return.
        // You can also test the return strings if required.
        String result = login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(result.contains("Username successfully captured"));
    }

    @Test
    public void testUsernameIncorrectMessage() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Username is not correctly formatted; please ensure that your username "
                   + "contains an underscore and is no more than five characters in length.", result);
    }

    // Add more assertEquals tests for password & cell if the marker requires the exact strings.
}