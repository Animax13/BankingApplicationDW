package BankingApplication.dto.response;

import BankingApplication.enums.Status;

import java.io.Serializable;

public class ServiceResponse  implements Serializable {

    private Status status;
    private String message;

    public ServiceResponse() {
        //Default Constructor : Expected to be empty
    }

    public ServiceResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
