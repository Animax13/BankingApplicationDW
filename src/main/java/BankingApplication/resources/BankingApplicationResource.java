package BankingApplication.resources;

import BankingApplication.dto.request.TestRequest;
import BankingApplication.service.TestService;
import com.google.inject.Inject;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/banking")
public class BankingApplicationResource {

    @Inject
    private TestService testService;

    @Path("/test")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSampleResponse (@Valid TestRequest request) {
        return Response.status(Response.Status.OK).entity(testService.getTestResponse(request)).build();
    }

}
