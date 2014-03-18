package mate.hq.validation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mate.hq.commons.Constant;

public abstract class AbstractValidationIssue
    implements ValidationIssue {

    private final String message;

    private final ValidationIssueSeverity validationIssueSeverity;

    protected AbstractValidationIssue(final String pMessage, final ValidationIssueSeverity pValidationIssueSeverity) {

        super();
        this.message = pMessage;
        this.validationIssueSeverity = pValidationIssueSeverity;
    }

    @Override
    public final boolean equals(
        final Object other) {

        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other.getClass() != getClass()) {
            return false;
        }

        final ValidationIssue castOther = (ValidationIssue) other;

        return new EqualsBuilder().append(getContextElements(), castOther.getContextElements())
            .append(getSeverity(), castOther.getSeverity()).append(getCode(), castOther.getCode())
            .append(getDebugMessage(), castOther.getDebugMessage()).isEquals();
    }

    @Override
    public final String getDebugMessage() {

        return this.message;
    }

    @Override
    public final ValidationIssueSeverity getSeverity() {

        return this.validationIssueSeverity;
    }

    @Override
    public final int hashCode() {

        return new HashCodeBuilder(Constant.INITIAL_NON_ZERO_ODD_NUMBER, Constant.MULTIPLIER_NON_ZERO_ODD_NUMBER)
            .append(getContextElements()).append(getSeverity()).append(getCode()).append(getDebugMessage())
            .toHashCode();
    }
}
