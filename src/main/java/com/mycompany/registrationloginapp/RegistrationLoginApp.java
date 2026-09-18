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

        sc.close();
    }
}