package BankingApplication;

import BankingApplication.dto.Account;
import BankingApplication.dto.request.AccountRequest;
import BankingApplication.dto.response.ServiceResponse;
import BankingApplication.enums.Status;
import BankingApplication.exceptions.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static BankingApplication.constant.ExceptionMessageConstant.*;
import static BankingApplication.constant.ServiceResponseConstant.FAILURE_RESPONSE_MESSAGE;

public class BankingApplicationHelper {

    protected static final Logger logger = LoggerFactory.getLogger(BankingApplicationHelper.class);

    private static boolean isValidRequest (Account account, String mobileNumber) {
        boolean isValid = false;
        try {
            isValid = StringUtils.equalsIgnoreCase(account.getUser().getMobile(), mobileNumber);
        } catch (Exception e) {
            logger.error("BankingApplicationHelper: Exception in isValidRequest: ", e);
        }
        return isValid;
    }

    public static void validateAccount (AccountRequest accountRequest, Account account) throws ApplicationException {
        if (account == null)
            throw new ApplicationException(ACCOUNT_NOT_EXISTS_ERROR_MESSAGE);
        if (!account.getIsActive())
            throw new ApplicationException(ACCOUNT_ALREADY_DEACTIVATED_ERROR_MESSAGE);
        if (!isValidRequest(account, accountRequest.getMobile()))
            throw new ApplicationException(ACCOUNT_AUTHENTICATION_FAILED_ERROR_MESSAGE);
    }

    public static ServiceResponse createUnknownErrorServiceResponse () {
        return new ServiceResponse(Status.FAILURE, FAILURE_RESPONSE_MESSAGE);
    }
}
