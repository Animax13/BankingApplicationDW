package BankingApplication.resources;

import BankingApplication.dto.request.AccountRequest;
import BankingApplication.dto.request.AddOrWithdrawAccountRequest;
import BankingApplication.dto.request.CreateAccountRequest;
import BankingApplication.dto.request.TestRequest;
import BankingApplication.service.AccountService;
import BankingApplication.service.TestService;
import BankingApplication.service.TransactionService;
import com.google.inject.Inject;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/banking")
@Produces(MediaType.APPLICATION_JSON)
public class BankingApplicationResource {

    @Inject
    private TestService testService;

    @Inject
    private AccountService accountService;

    @Inject
    private TransactionService transactionService;

    @Path("/test")
    @POST
    public Response getSampleResponse (@Valid TestRequest request) {
        return Response.status(Response.Status.OK).entity(testService.getTestResponse(request)).build();
    }

    @Path("/createAccount")
    @POST
    public Response createAccount(@Valid CreateAccountRequest request) {
        return Response.status(Response.Status.OK).entity(accountService.createAccount(request)).build();
    }

    @Path("/deactivateAccount")
    @POST
    public Response deactivateAccount(@Valid AccountRequest request) {
        return Response.status(Response.Status.OK).entity(accountService.deactivateAccount(request)).build();
    }

    @Path("/addFund")
    @POST
    public Response addFund(@Valid AddOrWithdrawAccountRequest request) {
        return Response.status(Response.Status.OK).entity(transactionService.addFund(request)).build();
    }

    @Path("/withdrawFund")
    @POST
    public Response withdrawFund(@Valid AddOrWithdrawAccountRequest request) {
        return Response.status(Response.Status.OK).entity(transactionService.withDrawFund(request)).build();
    }

    @Path("/checkAccountBalance")
    @POST
    public Response checkAccountBalance(@Valid AccountRequest request) {
        return Response.status(Response.Status.OK).entity(accountService.checkBalance(request)).build();
    }
}
