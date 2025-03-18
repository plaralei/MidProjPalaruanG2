/*
2 members
 */

/**
 * The CheckingAccount class holds the methods to get the account's minimum balance or to encash a check
 * This class allows the user the options on what to do with a checking account
 *
 *
 * @version 1.0
 */

public class CheckingAccount extends BankAccounts {
    public double minimumBalance;

    /**
     * Instance variables are assigned to their repective constructors
     *
     * @param accountNo 9-digit number that is connected to an account
     * @param accountName name that is connected to the account
     * @param minimumBalance the assigned minimum balance of the given account
     *
     */
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        this.minimumBalance = minimumBalance;
    }

    /**
     * Shows the minimum balance of an accessed account
     *
     * @return minimumBalance
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     *Encashes a check/exchanges a written check for cash
     *
     * @param amount The amount on the check
     * @throws InsufficientBalanceException if the balance falls below the minimum balance after encashment
     */
    public void encashCheck(double amount) throws InsufficientBalanceException {
        if (inquireBalance() - amount < minimumBalance) {
            throw new InsufficientBalanceException("Cannot encash check: Balance would fall below minimum requirement");
        }
        super.withdraw(amount);
    }

    /**
     * Overrides withdraw
     * Withdrawal not allowed
     * @param amount the amount on the check/ to be encashed
     *
     */
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