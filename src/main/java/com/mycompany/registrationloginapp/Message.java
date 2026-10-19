/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginapp;

/**
 *
 * @author Student
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;          // num messages sent (auto-increment)
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;
    private static ArrayList<String> sentMessages = new ArrayList<>();

    private static final Random random = new Random();

    // ---------- Constructor ----------
    public Message(int messageNumber) {
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    // ---------- Required methods ----------

    /** Generates a random 10-digit Message ID */
    private String generateMessageID() {
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    /** Boolean: checkMessageID() – ensures ID is not more than 10 characters */
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    /** String: checkRecipientCell() – max 10 chars and starts with international code */
 public String checkRecipientCell(String cell) {
    if (cell == null || cell.trim().isEmpty()) {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    String cleaned = cell.trim();

    // Only accept numbers that start with + and are a reasonable length
    if (cleaned.startsWith("+")
            && cleaned.length() >= 10 && cleaned.length() <= 13
            && cleaned.matches("^\\+[0-9]+$")) {

        this.recipient = cleaned;
        return "Cell phone number successfully captured.";
    }

    return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}

    /** String: createMessageHash() */
    public String createMessageHash() {
    if (messageText == null || messageText.trim().isEmpty() || messageID == null) {
        return "";
    }

    // Remove punctuation so "tonight?" becomes "tonight"
    String cleanText = messageText.replaceAll("[^a-zA-Z0-9\\s]", "");
    String[] words = cleanText.trim().split("\\s+");

    String firstWord = words[0].toUpperCase();
    String lastWord  = words[words.length - 1].toUpperCase();

    String idPart = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;

    this.messageHash = (idPart + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    return this.messageHash;
}

    /** String: SentMessage() – lets user choose Send / Disregard / Store */
    public String SentMessage(Scanner sc) {
        System.out.println("\nChoose an option:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Enter choice: ");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                totalMessagesSent++;
                String details = "MessageID: " + messageID +
                        "\nMessage Hash: " + messageHash +
                        "\nRecipient: " + recipient +
                        "\nMessage: " + messageText;
                sentMessages.add(details);
                return "Message successfully sent.";
            case "2":
                return "Press 0 to delete the message.";
            case "3":
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    /** String: printMessages() – returns all messages sent during the run */
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : sentMessages) {
            sb.append(msg).append("\n-------------------\n");
        }
        return sb.toString();
    }

    /** Int: returnTotalMessages() */
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    /** Your own storeMessage() – stores in a JSON file */
    public void storeMessage() {
        JSONObject msgObj = new JSONObject();
        msgObj.put("MessageID", messageID);
        msgObj.put("MessageHash", messageHash);
        msgObj.put("Recipient", recipient);
        msgObj.put("Message", messageText);
        msgObj.put("MessageNumber", messageNumber);

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(msgObj.toJSONString() + "\n");
            file.flush();
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // ---------- Helper methods used by the main app ----------
    public void setMessageText(String text) {
        this.messageText = text;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public static void resetCounters() {          // useful for unit tests
        totalMessagesSent = 0;
        sentMessages.clear();
    }

    public void setMessageIDForTesting(String id) {
    this.messageID = id;
}
}