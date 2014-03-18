package mate.hq.messaging;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "control_section")
@XmlType(propOrder = { "result", "issues"})
public final class ControlSectionDefinition {

    @XmlElement(name = "result", required = true)
    private final String result;

    @XmlElement(name = "issues", required = false)
    private final List<ControlIssueDefinition> issues;

    /**
     * Need this for JSON serialisation
     */
    private ControlSectionDefinition() {

        this(null, null);
    }

    private ControlSectionDefinition(final String pResult, final List<ControlIssueDefinition> pIssues) {

        super();
        this.result = pResult;
        this.issues = pIssues;
    }

    public static ControlSectionDefinition create(
        final String result,
        final List<ControlIssueDefinition> issues,
        final ResponseBodyDefinition data) {

        return new ControlSectionDefinition(result, issues);
    }

    public static ControlSectionDefinition create(
        final String result,
        final List<ControlIssueDefinition> issues) {

        return new ControlSectionDefinition(result, issues);
    }
}
