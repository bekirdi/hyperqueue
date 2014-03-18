package mate.hq.validation;

import java.util.Set;

public interface ValidationResult {

    void addIssue(
        ValidationIssue issueToAdd);

    void addIssues(
        Set<ValidationIssue> issuesToAdd);

    Set<ValidationIssue> getIssues();

    boolean hasIssues();
}
