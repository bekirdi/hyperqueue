package mate.hq.validation.util;

import mate.hq.validation.ValidationIssueBusinessRules;
import mate.hq.validation.ValidationIssueSeverity;

public enum HQValidationBusinessRules implements ValidationIssueBusinessRules {

        MESSAGE_EMPTY(ValidationIssueSeverity.ERROR, "0x00100001", "Message cannot be empty."),

        INTERNAL_SERVER_ERROR(
            ValidationIssueSeverity.FATAL,
            "0x00100002",
            "Request failed due to problems on the server. "),
        INVALID_MESSAGE_FORMAT(ValidationIssueSeverity.ERROR, "0x00100003", "Message is not an Integer.");

    private String debugMessage;

    private String errorCode;

    private ValidationIssueSeverity validationIssueSeverity;

    private HQValidationBusinessRules(
        final ValidationIssueSeverity pValidationIssueSeverity,
        final String pErrorCode,
        final String pDebugMessage) {

        this.validationIssueSeverity = pValidationIssueSeverity;
        this.errorCode = pErrorCode;
        this.debugMessage = pDebugMessage;
    }

    @Override
    public String getDebugMessage() {

        return this.debugMessage;
    }

    @Override
    public String getErrorCode() {

        return this.errorCode;
    }

    @Override
    public ValidationIssueSeverity getValidationIssueSeverity() {

        return this.validationIssueSeverity;
    }

}
