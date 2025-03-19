public class InvestmentAccount extends BankAccounts {
    private static final double MINIMUM_BALANCE = 1000.00; // Bank-defined minimum balance
    private static final double INTEREST_RATE = 0.05; // Bank-defined interest rate (5%)

    // Constructor
    public InvestmentAccount(int accountNo, String accountName) {
        super(accountNo, accountName);
        this.balance = MINIMUM_BALANCE; // Set initial balance to the required minimum
    }

    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    public double getInterest() {
        return INTEREST_RATE;
    }

    // Add investment (deposit)
    public void addInvestment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Investment amount must be positive.");
        }
        this.balance += amount;
        System.out.println("Successfully invested: " + amount);
    }

    // Inquire Investment Value (balance * (1 + interest))
    public double inquireInvestmentValue() {
        return balance * (1 + INTEREST_RATE);
    }

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

    // Preventing withdrawals and transfers
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed from an investment account.");
    }

    @Override
    public void transferMoney(BankAccounts target, double amount) {
        throw new UnsupportedOperationException("Transfers are not allowed from an investment account.");
    }
}
