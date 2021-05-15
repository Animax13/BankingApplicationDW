package BankingApplication.config;

import com.google.inject.AbstractModule;

public class BankingApplicationModule extends AbstractModule {

    private final String propertyFileName;

    public BankingApplicationModule(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    protected void configure() {

    }
}
