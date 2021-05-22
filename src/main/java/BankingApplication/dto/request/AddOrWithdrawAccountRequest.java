package BankingApplication.dto.request;

import java.io.Serializable;

public class AddOrWithdrawAccountRequest extends AccountRequest implements Serializable {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
