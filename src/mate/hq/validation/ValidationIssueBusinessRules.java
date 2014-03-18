package mate.hq.validation;

public interface ValidationIssueBusinessRules {

    String getDebugMessage();

    String getErrorCode();

    ValidationIssueSeverity getValidationIssueSeverity();
}
