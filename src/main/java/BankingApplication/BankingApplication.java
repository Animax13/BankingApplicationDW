package BankingApplication;

import BankingApplication.config.BankingApplicationModule;
import BankingApplication.health.BankingApplicationHealthCheck;
import BankingApplication.resources.BankingApplicationResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BankingApplication extends Application<BankingApplicationConfiguration> {

    protected HibernateBundle<BankingApplicationConfiguration> hibernate;

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
        hibernate = new ScanningHibernateBundle<BankingApplicationConfiguration>("BankingApplication.model") {
            @Override
            public DataSourceFactory getDataSourceFactory(BankingApplicationConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final BankingApplicationConfiguration configuration,
                    final Environment environment) {
        Injector injector = Guice.createInjector(new BankingApplicationModule(configuration.getPropertyFileName(), hibernate.getSessionFactory()));
        environment.jersey().register(injector.getInstance(BankingApplicationResource.class));
        environment.healthChecks().register("BankingApplicationHealthCheck", new BankingApplicationHealthCheck());
    }
}
