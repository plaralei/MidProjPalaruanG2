/*
2 members
 */

/**
 * The CheckingAccount class holds the methods to get the account's minimum balance or to encash a check
 *This class allows the user the options on what to do with a checking account
 *
 *
 * @version 1.0
 */

public class CheckingAccount extends BankAccounts {
    public double minimumBalance;

    /**
     * Checks the minimum balance of the account
     *
     * @param accountNo Ensures that the account number aligns with that of a created account
     * @param accountName Ensures that the account name aligns with that of a created account
     * @param minimumBalance Ensures that the account number aligns with that of a created account
     *
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     *
     *
     * @param amount The amount on the check
     * @throws InsufficientBalanceException if the balance falls below the minimum balance
     */
    public void encashCheck(double amount) throws InsufficientBalanceException {
        if (inquireBalance() - amount < minimumBalance) {
            throw new InsufficientBalanceException("Cannot encash check: Balance would fall below minimum requirement");
        }
        super.withdraw(amount);
    }
    
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Direct withdrawals are not allowed for checking accounts.");
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}