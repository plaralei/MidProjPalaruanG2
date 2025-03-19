public class CreditCardAccount extends BankAccounts {

    private double creditLimit;  // Credit limit for the card
    private double charges;      // Amount currently charged to the card
    private double interestRate = 0.10; // Interest rate (e.g., 0.12 for 12% per year)
    private double interestLimit = charges * 0.5; // Interest limit is 50% of current charges
    private int timeCounter = 0; // Tracks how many times interest has been applied

    public CreditCardAccount() {
        super();
        this.creditLimit = 0.0;
        this.charges = 0.0;
    }

    // Constructor to initialize the credit card account with given details
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        this.creditLimit = creditLimit;
    }

    // Getter method for the credit limit
    public double getCreditLimit() {
        return creditLimit;
    }

    // Getter method for the current charges on the card
    public double getCharges() {
        return charges;
    }

    // Apply interest after a set number of calls (simulating time passing)
    public void applyInterest() {
        timeCounter++; // Increment counter whenever method is called

        if (timeCounter >= 12) { // Assuming interest is applied after 12 calls or "months"
            double interest = charges * interestRate;
            if (interest > interestLimit) {
                interest = interestLimit; // Cap interest at limit
            }
            charges += interest;
            timeCounter = 0; // Reset counter after applying interest
            System.out.printf("Interest applied: " + interest + "\nNew charges: " + charges);
        } else {
            System.out.println("Interest not applied yet. # of calls until next increase: " + (12 - timeCounter));
        }
    }

    // Method to make a payment towards the card, reducing the charges
    public void payCard(double amount) {
        if (amount > 0) {  // Payment amount must be positive
            if (amount <= charges) {
                charges -= amount;  // Deduct the payment from the outstanding charges
                System.out.println("Payment successful. Remaining charges: " + charges);
            } else {
                charges = 0;  // If payment exceeds charges, clear the charges
                System.out.println("Payment exceeded the charges. Charges cleared.");
            }
        } else {
            System.out.println("Invalid payment amount. Payment should be positive.");
        }
    }

    // Method to inquire about the available credit (credit limit - charges)
    public void inquireAvailableCredit() {
        double availableCredit = creditLimit - charges;  // Available credit is the credit limit minus current charges
        System.out.println("Available Credit: " + availableCredit);
    }

    // Method to charge an amount to the card, if there's enough available credit
    public void chargeToCard(double amount) {
        double availableCredit = creditLimit - charges;  // Calculate available credit

        if (availableCredit >= amount) {
            charges += amount;  // Add the amount to charges if there's enough available credit
            System.out.println("Charge successful. New charges: " + charges);
        } else {
            System.out.println("Insufficient available credit to charge this amount."); // Notify if insufficient credit
        }
    }

    // Method to get a cash advance, but it must be less than or equal to 50% of the available credit
    public void getCashAdvance(double amount) {
        double availableCredit = creditLimit - charges;  // Calculate available credit

        if (amount <= (availableCredit * 0.5)) {  // Check if cash advance is within 50% of available credit

            charges += amount;  // Add the cash advance to charges
            System.out.println("Cash advance successful. New charges: " + charges);

        } else {
            System.out.println("Cash advance exceeds 50% of available credit. Transaction declined.");  // Reject if too large
        }
    }
}
