package mate.hq.validation;

import java.util.Set;

public interface Validator<T> {

    Set<ValidationIssue> validate(
        T objectToValidate);
}
