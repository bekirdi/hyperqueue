package mate.hq.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ValidationResultImpl
    implements ValidationResult {

    private final Set<ValidationIssue> validationIssues;

    private ValidationResultImpl() {

        super();
        this.validationIssues = new HashSet<ValidationIssue>();
    }

    public static ValidationResult create() {

        return new ValidationResultImpl();
    }

    @Override
    public void addIssue(
        final ValidationIssue issueToAdd) {

        this.validationIssues.add(issueToAdd);
    }

    @Override
    public void addIssues(
        final Set<ValidationIssue> issuesToAdd) {

        this.validationIssues.addAll(issuesToAdd);
    }

    @Override
    public boolean equals(
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

        final ValidationResult castOther = (ValidationResult) other;

        return new EqualsBuilder().append(this.validationIssues, castOther.getIssues()).isEquals();
    }

    @Override
    public Set<ValidationIssue> getIssues() {

        return Collections.unmodifiableSet(this.validationIssues);
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(this.validationIssues).toHashCode();
    }

    @Override
    public boolean hasIssues() {

        return !this.validationIssues.isEmpty();
    }
}
