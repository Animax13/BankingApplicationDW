package BankingApplication.model;

import BankingApplication.enums.TransactionType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "transaction", catalog = "banking")
public class TransactionModel {

    private UUID id;
    private AccountModel sourceAccount;
    private AccountModel destinationAccount;
    private TransactionType type;
    private double amount;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sourceAccountNumber")
    public AccountModel getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountModel sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "destinationAccountNumber")
    public AccountModel getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountModel destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Enumerated(EnumType.STRING)
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
