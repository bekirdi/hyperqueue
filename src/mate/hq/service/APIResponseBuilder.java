package mate.hq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mate.hq.messaging.ControlIssueDefinition;
import mate.hq.messaging.ControlSectionDefinition;
import mate.hq.validation.ValidationIssue;
import mate.hq.validation.util.HQValidationBusinessRules;

public final class APIResponseBuilder {

    private APIResponseBuilder() {

        super();
    }

    /**
     * This method returns a JSON response containing the appropriate error info.
     *
     * @author Badr El Kirdi
     *
     * @return A JSON response containing the appropriate error info
     * @param validationResult - a string representing the result of input parameter (start date, end date) validation
     * */
    public static ControlSectionDefinition prepareErrorResponse(
        final Set<ValidationIssue> validationIssues) {

        final List<ControlIssueDefinition> issues = new ArrayList<ControlIssueDefinition>();

        for (final ValidationIssue validationIssue : validationIssues) {
            issues.add(APIResponseBuilder.buildValidationErrorMessageElement(validationIssue));
        }

        return ControlSectionDefinition.create("failed", issues);
    }

    public static ControlSectionDefinition prepareGeneralExceptionErrorResponse(
        final String generalExceptionMessage) {

        final List<ControlIssueDefinition> issues = new ArrayList<ControlIssueDefinition>();

        issues.add(APIResponseBuilder.errorResponseGeneral(generalExceptionMessage));

        return ControlSectionDefinition.create("failed", issues);
    }

    private static ControlIssueDefinition errorResponseGeneral(
        final String generalExceptionMessage) {

        return ControlIssueDefinition.create(HQValidationBusinessRules.INTERNAL_SERVER_ERROR
            .getValidationIssueSeverity().toString(), HQValidationBusinessRules.INTERNAL_SERVER_ERROR
            .getErrorCode(), HQValidationBusinessRules.INTERNAL_SERVER_ERROR.getDebugMessage()
            + generalExceptionMessage);
    }

    private static ControlIssueDefinition buildValidationErrorMessageElement(
        final ValidationIssue validationIssue) {

        return ControlIssueDefinition.create(validationIssue.getSeverity().toString(), validationIssue.getCode(),
            validationIssue.getDebugMessage());
    }

    /**
     * This method returns a JSON control section containing the appropriate success info.
     *
     * @author Badr El Kirdi
     *
     * @return A JSON response containing the appropriate success info
     * */
    public static ControlSectionDefinition buildSuccessControlResponse() {

        return ControlSectionDefinition.create("success", null);
    }

}
