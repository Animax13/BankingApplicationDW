package BankingApplication.exceptions;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
