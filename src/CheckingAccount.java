/*
2 members
 */

public class CheckingAccount extends BankAccounts {
    private double minimumBalance;
    
    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        this.minimumBalance = minimumBalance;
    }
    
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
// need to implement getMinimumBalance()
// implement CheckingAccount()
// add implementation to other files