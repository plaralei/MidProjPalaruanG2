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
     * Instance variables are assigned to their respective constructors
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
     *Encashes a check/exchanges a written check for cash
     * UML doesn't list balance so balance cannot be added to/used when encashing a check
     *
     * @param amount The amount on the check
     * @throws InvalidAmountException doesn't accept the check if the amount is zero
     */
    public void encashCheck(double amount) throws InvalidAmountException {
        if ( amount==0) {
            throw new InvalidAmountException ("Cannot encash a blank check");
        }
        if (amount <0){
            throw new InvalidAmountException ("Cannot encash a negative check");
        }
        System.out.println("Please see a teller to encash check.");;
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

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
