package BankingApplication.service;

import BankingApplication.dto.request.SampleRequest;
import BankingApplication.dto.response.SampleResponse;
import BankingApplication.enums.Status;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class BankingApplicationService {

    private final Logger logger = LoggerFactory.getLogger(BankingApplicationService.class);

    @Inject
    private BankingApplicationService () {}

    public SampleResponse getSampleResponse (SampleRequest sampleRequest) {
        SampleResponse sampleResponse = new SampleResponse();
        if (sampleRequest != null && StringUtils.isNotBlank(sampleRequest.getSampleField()))
            sampleResponse.setSampleStatus(Status.SUCCESS);
        else
            sampleResponse.setSampleStatus(Status.FAILURE);
        return sampleResponse;
    }
}
