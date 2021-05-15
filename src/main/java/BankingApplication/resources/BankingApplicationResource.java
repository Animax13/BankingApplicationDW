package BankingApplication.resources;

import BankingApplication.dto.request.SampleRequest;
import BankingApplication.service.BankingApplicationService;
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
    private BankingApplicationService bankingApplicationService;

    @Path("/test")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSampleResponse (@Valid SampleRequest request) {
        return Response.status(Response.Status.OK).entity(bankingApplicationService.getSampleResponse(request)).build();
    }

}
