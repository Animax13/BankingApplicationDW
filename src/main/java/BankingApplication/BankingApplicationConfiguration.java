package BankingApplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class BankingApplicationConfiguration extends Configuration {

    @JsonProperty
    private String propertyFileName;

    public String getPropertyFileName() {
        return propertyFileName;
    }
}
