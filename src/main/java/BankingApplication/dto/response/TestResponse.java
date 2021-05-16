package BankingApplication.dto.response;

import BankingApplication.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TestResponse implements Serializable {

    @JsonProperty("test_status")
    private Status testStatus;

    public Status getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Status testStatus) {
        this.testStatus = testStatus;
    }
}
