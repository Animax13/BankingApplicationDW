package BankingApplication;

import BankingApplication.config.BankingApplicationModule;
import BankingApplication.health.BankingApplicationHealthCheck;
import BankingApplication.resources.BankingApplicationResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BankingApplication extends Application<BankingApplicationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BankingApplication().run(args);
    }

    @Override
    public String getName() {
        return "BankingApplication";
    }

    @Override
    public void initialize(final Bootstrap<BankingApplicationConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(final BankingApplicationConfiguration configuration,
                    final Environment environment) {
        Injector injector = Guice.createInjector(new BankingApplicationModule(configuration.getPropertyFileName()));
        environment.jersey().register(injector.getInstance(BankingApplicationResource.class));
        environment.healthChecks().register("BankingApplicationHealthCheck", new BankingApplicationHealthCheck());
    }
}
