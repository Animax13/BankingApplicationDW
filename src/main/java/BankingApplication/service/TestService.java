package BankingApplication.service;

import BankingApplication.dto.request.TestRequest;
import BankingApplication.dto.response.TestResponse;
import BankingApplication.enums.Status;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;

@Singleton
public class TestService {

    @Inject
    private TestService() {}

    public TestResponse getTestResponse(TestRequest testRequest) {
        TestResponse testResponse = new TestResponse();
        if (testRequest != null && StringUtils.isNotBlank(testRequest.getTestField()))
            testResponse.setTestStatus(Status.SUCCESS);
        else
            testResponse.setTestStatus(Status.FAILURE);
        return testResponse;
    }
}
