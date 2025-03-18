/**
 * The CheckingAccount class holds the methods to get the account's minimum balance
 * or to encash a check. This class allows the user to manage checking accounts.
 *
 * @version 1.0
 */
public class CheckingAccount extends BankAccounts {
    private double minimumBalance;

    /**
     * Constructor to initialize a CheckingAccount with the given details.
     *
     * @param accountNo      9-digit number that is connected to an account
     * @param accountName    name that is connected to the account
     * @param minimumBalance the assigned minimum balance of the given account
     * @throws IllegalArgumentException if minimum balance is negative
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        this.minimumBalance = minimumBalance;
    }

    /**
     * Getter method to retrieve the minimum balance of the account.
     *
     * @return the minimum balance of the account
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Method to encash a check, withdrawing the amount specified.
     * The balance after encashment must not fall below the minimum balance.
     *
     * @param amount the amount on the check to be encashed
     * @throws InsufficientBalanceException if the balance falls below the minimum balance
     * @throws IllegalArgumentException if the amount is negative or zero
     */
    public void encashCheck(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to encash must be positive");
        }

        double availableBalance = inquireBalance();  // Get the current account balance
        if (availableBalance - amount < minimumBalance) {
            throw new InsufficientBalanceException("Cannot encash check: Balance would fall below minimum requirement");
        }
        super.withdraw(amount);  // Proceed with the withdrawal from the balance
        System.out.println("Check encashed successfully. Amount: " + amount);
    }

    /**
     * Overrides the withdraw method from BankAccounts.
     * Direct withdrawals are not allowed for checking accounts.
     *
     * @param amount the amount to be withdrawn
     * @throws UnsupportedOperationException since direct withdrawals are not allowed
     */
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Direct withdrawals are not allowed for checking accounts.");
    }
}

/**
 * Custom exception class for insufficient balance in CheckingAccount.
 */
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
