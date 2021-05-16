package BankingApplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TestRequest implements Serializable {

    @JsonProperty("test_field")
    private String testField;

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }
}
