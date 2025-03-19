import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountsMain {

    static HashMap<String, String> userAccounts = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static boolean accountCreated = false; // Track if an account has been created
    static String generatedAccountNumber = ""; // Store generated account number

    public static void main(String[] args) {
        boolean isLoggedIn = false;

        // Menu for user to see general info, log in, create an account, or exit
        while (!isLoggedIn) {
            System.out.println("1. " + (accountCreated ? "Create Another Account" : "Create Account"));
            System.out.println("2. General Info");
            System.out.println("3. Log In");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayGeneralInformation();
                    break;
                case 3:
                    isLoggedIn = login();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        // Once logged in, show the account number and continue with other actions
        System.out.println("Your account number is: " + generatedAccountNumber);

        // You can continue with other bank operations here...
    }

    // Method to display general information for first-time users
    public static void displayGeneralInformation() {
        System.out.println("Welcome to the Bank Account System!");
        System.out.println("Here are the key points you need to know as a first-time user:");
        System.out.println("1. Bank Accounts: You can create different types of accounts such as Checking, Credit Card, or Investment.");
        System.out.println("2. Interest Rates: Investment accounts allow you to earn interest on your balance.");
        System.out.println("3. Deposits and Withdrawals: Manage your finances by depositing or withdrawing funds from your account.");
        System.out.println("4. Transfer Money: You can transfer funds between different bank accounts.");
        System.out.println("5. Checking Account: Suitable for daily transactions with minimal fees.");
        System.out.println("6. Credit Card: Use it for purchases, but be aware of your credit limit and interest rates.");
        System.out.println("7. Investment: Grow your money by earning interest over time.");
        System.out.println("Now, let's begin!");
        System.out.println();
    }

    // Method to create a new user account
    public static void createAccount() {
        System.out.print("Enter Full Name (FirstName MI LastName): ");
        String fullName = sc.nextLine();
        System.out.print("Create a username: ");
        String username = sc.nextLine();
        System.out.print("Create a password: ");
        String password = sc.nextLine();

        // Add the user credentials to the map
        userAccounts.put(username, password);

        // Generate a 9-digit account number
        Random rand = new Random();
        generatedAccountNumber = String.format("%09d", rand.nextInt(1000000000));

        accountCreated = true; // Mark that an account has been created
        System.out.println("Account successfully created! Your 9-digit account number is: " + generatedAccountNumber);
        System.out.println("You can now log in.");
    }

    // Method to log in the user
    public static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Check if the user exists and the password matches
        if (userAccounts.containsKey(username) && userAccounts.get(username).equals(password)) {
            System.out.println("Login successful! Welcome back, " + username + ".");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }
}
