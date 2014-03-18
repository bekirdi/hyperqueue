package mate.hq.validation;

import java.util.List;

import org.apache.commons.collections.KeyValue;

public interface ValidationIssue {

    String getCode();

    List<KeyValue> getContextElements();

    String getDebugMessage();

    ValidationIssueSeverity getSeverity();

}
