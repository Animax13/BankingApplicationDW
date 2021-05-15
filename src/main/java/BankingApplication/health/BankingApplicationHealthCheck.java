package BankingApplication.health;

import com.codahale.metrics.health.HealthCheck;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BankingApplicationHealthCheck extends HealthCheck {

    private final Client client;

    public BankingApplicationHealthCheck() {
        super();
        this.client = new JerseyClientBuilder().build();
    }

    @Override
    protected Result check() {
        String URL = "http://localhost:8080/banking/test";
        WebTarget webTarget = client.target(URL);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()) {
            return Result.healthy();
        }
        String UNHEALTHY_SERVER_MESSAGE = "Banking Application is not running, Please check logs";
        return Result.unhealthy(UNHEALTHY_SERVER_MESSAGE);
    }
}
