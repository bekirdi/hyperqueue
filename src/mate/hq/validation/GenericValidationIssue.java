package mate.hq.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.KeyValue;

public final class GenericValidationIssue
    extends AbstractValidationIssue {

    private final String errorCode;

    private final List<KeyValue> parameters;

    private GenericValidationIssue(
        final String pDebugMessage,
        final String pErrorCode,
        final ValidationIssueSeverity pValidationIssueSeverity,
        final List<KeyValue> pParameters) {

        super(pDebugMessage, pValidationIssueSeverity);

        this.errorCode = pErrorCode;
        this.parameters = pParameters;
    }

    public static ValidationIssue create(
        final ValidationIssueBusinessRules validationIssueBusinessRules) {

        return GenericValidationIssue.create(validationIssueBusinessRules, new ArrayList<KeyValue>());
    }

    public static ValidationIssue create(
        final ValidationIssueBusinessRules validationIssueBusinessRules,
        final List<KeyValue> parameters) {

        String debugMessage = validationIssueBusinessRules.getDebugMessage();
        if (parameters != null) {
            // we try to replace binded parameters in message
            for (final KeyValue parameter : parameters) {
                debugMessage = debugMessage.replace("?" + parameter.getKey(), parameter.getValue().toString());
            }
        }
        return new GenericValidationIssue(
            debugMessage,
            validationIssueBusinessRules.getErrorCode(),
            validationIssueBusinessRules.getValidationIssueSeverity(),
            parameters);
    }

    @Override
    public String getCode() {

        return this.errorCode;
    }

    @Override
    public List<KeyValue> getContextElements() {

        return this.parameters;
    }

    @Override
    public String toString() {

        return getDebugMessage();
    }
}
