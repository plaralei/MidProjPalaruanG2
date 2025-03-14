import java.util.Scanner;

public class AccountsMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Arrays to store different accounts
        BankAccounts[] bankAccounts = new BankAccounts[5];
        InvestmentAccount[] investmentAccounts = new InvestmentAccount[5];
        CheckingAccount[] checkingAccounts = new CheckingAccount[5];
        CreditCardAccount[] creditCardAccounts = new CreditCardAccount[5];

        // Menu options (implement logic for each)
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Create Bank Account");
            System.out.println("2. Balance Inquiry");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Close Account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            // Add logic here for each menu option
            switch (choice) {
                case 1:
                    // Create accounts
                    break;
                case 2:
                    // Balance inquiry
                    break;
                case 3:
                    // Deposit
                    break;
                case 4:
                    // Withdraw
                    break;
                case 5:
                    // Transfer money
                    break;
                case 6:
                    // Close account
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
