/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationloginapp;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class RegistrationLoginApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration ===");
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter cell phone (+27xxxxxxxxx): ");
        String cell = sc.nextLine();

        String regResult = login.registerUser(username, password, cell, firstName, lastName);
        System.out.println(regResult);

        // Only proceed to login if registration succeeded
        if (regResult.contains("successfully")) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String loginUser = sc.nextLine();
            System.out.print("Enter password: ");
            String loginPass = sc.nextLine();

            System.out.println(login.returnLoginStatus(loginUser, loginPass));
        }
// ========== PART 1 – Login ==========
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUser = sc.nextLine();
        System.out.print("Enter password: ");
        String loginPass = sc.nextLine();

        String status = login.returnLoginStatus(loginUser, loginPass);
        System.out.println(status);

        if (!status.startsWith("Welcome")) {
            System.out.println("Login failed. You cannot send messages.");
            sc.close();
            return;
        }

        // ========== PART 2 – Sending Messages ==========
        System.out.println("\nWelcome to QuickChat.");

        // Ask how many messages the user wants to send
        System.out.print("How many messages do you wish to enter? ");
        int maxMessages;
        try {
            maxMessages = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Exiting.");
            sc.close();
            return;
        }

        int messagesCreated = 0;

        // Main menu loop – runs until user chooses Quit
        while (true) {
            System.out.println("\n=== QuickChat Menu ===");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter option: ");
            String option = sc.nextLine().trim();

            if (option.equals("3")) {
                System.out.println("Goodbye!");
                break;
            }

            if (option.equals("2")) {
                System.out.println("Coming Soon.");
                continue;
            }

            if (option.equals("1")) {
                if (messagesCreated >= maxMessages) {
                    System.out.println("You have already entered the maximum number of messages (" + maxMessages + ").");
                    continue;
                }

                messagesCreated++;
                Message msg = new Message(messagesCreated);   // messageNumber starts at 1

                // Recipient
                System.out.print("Enter recipient cell number: ");
                String recipient = sc.nextLine();
                String cellCheck = msg.checkRecipientCell(recipient);
                System.out.println(cellCheck);
                if (!cellCheck.contains("successfully")) {
                    messagesCreated--;   // don’t count this attempt
                    continue;
                }

                // Message text
                System.out.print("Enter message (max 250 characters): ");
                String text = sc.nextLine();
                if (text.length() > 250) {
                    System.out.println("Please enter a message of less than 250 characters.");
                    messagesCreated--;
                    continue;
                }
                msg.setMessageText(text);

                // Create hash
                String hash = msg.createMessageHash();
                System.out.println("Message Hash: " + hash);

                // Let user decide what to do with the message
                String sendResult = msg.SentMessage(sc);
                System.out.println(sendResult);

                // If sent, show full details
                if (sendResult.contains("successfully sent")) {
                    System.out.println("\n----- Message Details -----");
                    System.out.println("Message ID: " + msg.getMessageID());
                    System.out.println("Message Hash: " + msg.getMessageHash());
                    System.out.println("Recipient: " + msg.getRecipient());
                    System.out.println("Message: " + msg.getMessageText());
                    System.out.println("---------------------------");
                }
            } else {
                System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        // Final total
        Message dummy = new Message(0);   // just to call the static method
        System.out.println("\nTotal messages sent: " + dummy.returnTotalMessages());
        System.out.println("\nAll sent messages:\n" + dummy.printMessages());
        sc.close();
    }
}