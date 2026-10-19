/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationloginapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message msg;

    @BeforeEach
    public void setUp() {
        Message.resetCounters();
        msg = new Message(0);
    }



    @Test
    public void testMessageLengthSuccess() {
        msg.setMessageText("Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.getMessageText().length() <= 250);
        // You can also test a helper method if you add one that returns the success string
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(251);
        msg.setMessageText(longText);
        assertTrue(msg.getMessageText().length() > 250);
    }

   @Test
public void testRecipientCorrect() {
    String result = msg.checkRecipientCell("+27718693002");
    assertEquals("Cell phone number successfully captured.", result);
}

@Test
public void testRecipientIncorrect() {
    String result = msg.checkRecipientCell("08575975889");
    assertTrue(result.contains("incorrectly formatted") 
            || result.contains("international code"));
}

@Test
public void testCreateMessageHash() {
    msg = new Message(0);
    msg.setMessageIDForTesting("0012345678");
    msg.setMessageText("Hi Mike, can you join us for dinner tonight?");

    String hash = msg.createMessageHash();
    assertEquals("00:0:HITONIGHT", hash);
}

    @Test
    public void testMessageIDCreated() {
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }
}