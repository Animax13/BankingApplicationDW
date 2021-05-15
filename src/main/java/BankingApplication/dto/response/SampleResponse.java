package BankingApplication.dto.response;

import BankingApplication.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SampleResponse implements Serializable {

    @JsonProperty("sample_status")
    private Status sampleStatus;

    public Status getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(Status sampleStatus) {
        this.sampleStatus = sampleStatus;
    }
}
