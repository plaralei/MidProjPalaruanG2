public class CheckingAccount extends BankAccounts {
    private double minimumBalance;

    // Constructors
    public CheckingAccount() {
        super();
    }

    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    // Get minimum balance
    public double getMinimumBalance() {
        return minimumBalance;
    }

    // Encash check method (withdraw equivalent)
    public void encashCheck(double amount) {
        if (inquireBalance() >= amount) {
            withdraw(amount);
        } else {
            System.out.println("Insufficient balance for encashing check.");
        }
    }
}
