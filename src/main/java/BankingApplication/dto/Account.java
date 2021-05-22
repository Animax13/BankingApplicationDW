package BankingApplication.dto;

public class Account {

    public Account() {
        // Default Constructor
    }

    public Account(String accountNumber, double balance, boolean isActive, User user) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }

    private String accountNumber;
    private double balance;
    private boolean isActive;
    private User user;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance (double balance) {
        this.balance = balance;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive (boolean isActive) {
        this.isActive = isActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
