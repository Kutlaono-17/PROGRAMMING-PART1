/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {
    // Store the registered credentials (simple in-memory for this task)
    private String storedUsername;
    private String storedPassword;
    private String storedCell;
    private String firstName;
    private String lastName;

    // ========== Validation methods required by the brief ==========

    /**
     * Username must contain an underscore AND be no more than 5 characters long.
     */
    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Password must be:
     * - at least 8 characters
     * - contain a capital letter
     * - contain a number
     * - contain a special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /**
     * Cell phone must contain the international country code (+27 for SA)
     * and the whole number must be no more than 10 characters long
     * (as per the table in the brief).
     * Example that passes: +27838968976  (exactly 12? Wait – brief says "no more than ten characters long")
     * Looking carefully at the screenshots:
     * "the number, which is no more than ten characters long"
     * and test data: +27838968976  → this is 12 characters.
     * The message says "no more than ten characters long".
     * We will implement a practical SA mobile regex that accepts +27 followed by 9 digits
     * (standard) and also respect the spirit of the brief. 
     * Many students use a regex that checks for +27 and total length around 12.
     * The exact test data that must pass is: +27838968976
     */
    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        // Regex that accepts +27 followed by 9 digits (standard SA mobile)
        // This matches the provided test data +27838968976
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cell);
        return matcher.matches();
    }

    // ========== Registration & Login methods ==========

    /**
     * Registers the user if all validations pass.
     * Returns the appropriate message.
     */
    public String registerUser(String username, String password, String cell,
                               String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username "
                 + "contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password "
                 + "contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // All good – store the details
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCell = cell;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Username successfully captured.\n"
             + "Password successfully captured.\n"
             + "Cell phone number successfully added.";
    }

    /**
     * Checks if the supplied username + password match the registered ones.
     */
    public boolean loginUser(String username, String password) {
        return storedUsername != null
                && storedUsername.equals(username)
                && storedPassword.equals(password);
    }

    /**
     * Returns the login status message.
     */
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

