package BankingApplication.model;

import javax.persistence.*;

@Entity
@Table(name = "account", catalog = "banking")
public class AccountModel {

    private String accountNumber;
    private double balance;
    private boolean isActive;
    private UserModel user;

    @Id
    @Column(name = "accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name = "isActive")
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customerMobileNumber")
    public UserModel getCustomerMobileNumber() {
        return user;
    }

    public void setCustomerMobileNumber(UserModel customerMobileNumber) {
        this.user = customerMobileNumber;
    }
}
