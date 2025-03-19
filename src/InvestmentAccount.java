public class InvestmentAccount extends BankAccounts {
    private double minimumBalance;
    private double interest;

    // Constructors
    public InvestmentAccount() {
        super();
        this.minimumBalance = 0;
        this.interest = 0.10;
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
    }

    // Getters
    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    // Add investment (acts like a deposit)


    // Inquire investment value (balance + interest)
    public double inquireInvestmentValue() {
        if (balance > 0) {
            return balance * (1 + interest);
        } else {
            System.out.println("No balance in account.");
            return 0;
        }
    }

    // Override close account (withdraw full amount with interest)
    @Override
    public void closeAccount() {
        if (balance > 0) {
            double totalBalance = inquireInvestmentValue();
            balance = 0;
            System.out.println("Account closed. Final balance (with interest): " + totalBalance); //withdraw all balanace before closing
            super.closeAccount();
        } else {
            System.out.println("Account already empty.");
        }
    }
}
