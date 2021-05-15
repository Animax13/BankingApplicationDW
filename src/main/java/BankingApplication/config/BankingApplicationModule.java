package BankingApplication.config;

import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.net.URL;
import java.util.Properties;

public class BankingApplicationModule extends AbstractModule {

    private final String propertyFileName;

    public BankingApplicationModule(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    protected void configure() {
        Properties properties = new Properties();
        readPropertyFile(properties, propertyFileName+".properties");
        Names.bindProperties(binder(), properties);
    }

    protected void readPropertyFile(Properties properties, String fileName) {
        try {
            URL resource = Resources.getResource(fileName);
            properties.load(resource.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
