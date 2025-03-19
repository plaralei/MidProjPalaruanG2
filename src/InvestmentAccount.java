/**
 * The InvestmentAccount class represents a specialized bank account with a fixed minimum balance
 * and an interest rate set by the bank. This account does not allow withdrawals or money transfers.
 * Instead, it allows users to add investments and inquire about their investment value.
 *
 */

public class InvestmentAccount extends BankAccounts {
    private static final double minimumBalance = 1000.00;
    private static final double interest = 0.05;

    /**
     * Creates an InvestmentAccount with a default minimum balance.
     *
     * @param accountNo   The unique account number
     * @param accountName The name of the account holder
     */

    public InvestmentAccount(int accountNo, String accountName) {
        super(accountNo, accountName);
        this.balance = minimumBalance;
    }

    /**
     * Gets the minimum balance required for the investment account.
     *
     * @return The fixed minimum balance
     */

    public double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Gets the interest rate applied to the investment account.
     *
     * @return The fixed interest rate
     */

    public double getInterest() {
        return interest;
    }

    /**
     * Adds an investment amount to the account balance.
     *
     * @param amount The amount to invest (must be positive)
     * @throws IllegalArgumentException if the amount is zero or negative
     */
    public void addInvestment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Investment amount must be positive.");
        }
        this.balance += amount;
        System.out.println("Successfully invested: " + amount);
    }

    /**
     * Inquires about the current investment value, which includes interest.
     *
     * @return The total investment value, calculated as balance * (1 + interest rate)
     */
    public double inquireInvestmentValue() {
        return balance * (1 + interest);
    }
    /**
     * Closes the investment account by withdrawing all funds, including interest.
     * The status is then set to "Closed".
     */
    @Override
    public void closeAccount() {
        double totalBalance = inquireInvestmentValue();
        System.out.println("Withdrawing Final Balance (with interest): " + totalBalance);
        if (balance > 0) {
            balance = 0;
            super.closeAccount();
        } else {
            System.out.println("Account already empty.");
        }
    }

    /**
     * Prevents withdrawals from an investment account.
     *
     * @param amount The withdrawal amount
     * @throws UnsupportedOperationException Always thrown since withdrawals are not allowed
     */
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed from an investment account.");
    }

    /**
     * Prevents transfers from an investment account.
     *
     * @param target The target account
     * @param amount The transfer amount
     * @throws UnsupportedOperationException Always thrown since transfers are not allowed
     */
    @Override
    public void transferMoney(BankAccounts target, double amount) {
        throw new UnsupportedOperationException("Transfers are not allowed from an investment account.");
    }
}
